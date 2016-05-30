# FigureImageView
<br>
## Introduction
&nbsp;&nbsp;&nbsp;&nbsp;本类继承自ImageView，可以用于设置图片的形状，现有圆角、圆形、扇形、环形，四种可选方式。
可以通过set或者xml来设置圆角的半径，以及扇形、环形样式的角度。
<br>
## Version
&nbsp;&nbsp;&nbsp;&nbsp;SdkVersion >= 19
## Usage
```Xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:tools="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              android:paddingBottom="@dimen/activity_vertical_margin"
              android:paddingLeft="@dimen/activity_horizontal_margin"
              android:paddingRight="@dimen/activity_horizontal_margin"
              android:paddingTop="@dimen/activity_vertical_margin"
              tools:context="com.example.administrator.customview.MainActivity"
              android:orientation="vertical"
              android:layout_gravity="center"
              android:gravity="center"
    >
    <com.example.administrator.customview.FigureImageView
        android:src="@mipmap/image1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        app:mode="circle"
        />
    <com.example.administrator.customview.FigureImageView
        android:src="@mipmap/image1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        app:mode="round_rect"
        app:radius="30dp"/>
    <com.example.administrator.customview.FigureImageView
        android:src="@mipmap/image1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        app:mode="sector"
        app:angle="-120"/>
    <com.example.administrator.customview.FigureImageView
        android:src="@mipmap/image1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1"
        app:mode="ring"
        app:angle="-120"/>
</LinearLayout>
```
<br>
<img src="https://github.com/Idtk/FigureImageView/blob/master/image/FigureImageView.png" alt="FigureImageView" title="FigureImageView"/>
<br>
## About
**博客:www.idtkm.com**<br>
**GitHub:https://github.com/Idtk**<br>
**邮箱:IdtkMa@gmail.com**<br>
