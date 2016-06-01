package com.example.administrator.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/5/29.
 */

public class FigureImageView extends ImageView {

    protected int mViewWidth,mViewHeight;
    protected Paint mPaint = new Paint();
    protected Rect rect = new Rect();
    protected float length;

    private RectF rectF = new RectF();
    private Path mPath = new Path();
    private Path mPath1 = new Path();
    private Path mPath2 = new Path();
    private Matrix matrix = new Matrix();
    private Bitmap b;

    private int modeFlag = 0x00;
    private static final int CIRCLE = 0x00;
    private static final int ROUNDRECT = 0x01;
    private static final int SECTOR = 0x02;
    private static final int RING = 0x03;
    private int radius = 50;
    private float angle = -120;

    public FigureImageView(Context context) {
        this(context,null);
    }

    public FigureImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FigureImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FigureImageView, defStyleAttr,0);
        int n = array.getIndexCount();
        for (int i=0; i<n; i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.FigureImageView_mode:
                    modeFlag = array.getInt(attr,modeFlag);
                    break;
                case R.styleable.FigureImageView_radius:
                    radius = array.getDimensionPixelSize(attr,radius);
                    break;
                case R.styleable.FigureImageView_angle:
                    angle = array.getFloat(attr,angle);
                    break;
            }
        }
        array.recycle();

        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        size();
        scaleBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.translate(mViewWidth/2,mViewHeight/2);
        canvas.clipPath(pathFigure(), Region.Op.INTERSECT);
        mPath.reset();
        canvas.drawBitmap(b,rect,rect,mPaint);

    }


    private void scaleBitmap(){

        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        if (!(drawable instanceof BitmapDrawable)) {
            return;
        }
        b = ((BitmapDrawable) drawable).getBitmap();
        if (null == b) {
            return;
        }

        float scaleWidth = length/b.getWidth();
        float scaleHeight = length/b.getHeight();
        matrix.postScale(scaleWidth,scaleHeight);
        b=Bitmap.createBitmap(b,0,0,b.getWidth(),b.getHeight(),matrix,true);
    }

    protected void size(){
        length = Math.min(mViewWidth,mViewHeight)/2;
        rect = new Rect(-(int) length, -(int) length, (int) length, (int) length);
        rectF = new RectF(-length, -length, length, length);
    }

    protected Path pathFigure(){
        switch (modeFlag){
            case CIRCLE:
                mPath.addCircle(0,0,length, Path.Direction.CW);
                break;
            case ROUNDRECT:
                rectF.left = -length;
                rectF.top = -length;
                rectF.right = length;
                rectF.bottom = length;
                mPath.addRoundRect(rectF,radius,radius, Path.Direction.CW);
                break;
            case SECTOR:
                rectF.left = -length*2;
                rectF.top = -length;
                rectF.right = length*2;
                rectF.bottom = length*3;
                mPath.moveTo(0,length);
                mPath.arcTo(rectF,angle,-angle*2-180);
                break;
            case RING:
                rectF.left = -length*2;
                rectF.top = -length;
                rectF.right = length*2;
                rectF.bottom = length*3;
                mPath1.moveTo(0,length);
                mPath1.arcTo(rectF,angle,-angle*2-180);

                rectF.left = -length/2;
                rectF.top = length/2;
                rectF.right = length/2;
                rectF.bottom = length*3/2;
                mPath2.moveTo(0,length);
                mPath2.arcTo(rectF,angle,-angle*2-180);
                mPath.op(mPath1,mPath2, Path.Op.XOR);
        }
        return mPath;
    }

    public void setModeFlag(int modeFlag) {
        this.modeFlag = modeFlag;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
