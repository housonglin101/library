package com.hsl.library.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.hsl.library.entity.District;

import java.util.ArrayList;

public class MyBitmap {
    public static Bitmap getBitmap(int id) {
        return new MyResources().getBitmap(id);
    }

    public static Bitmap flipHorizontal(Bitmap bmp) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1); // 水平翻转
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
    }

    public static Bitmap flipVertical(Bitmap bmp) {
        Matrix matrix = new Matrix();
        matrix.postScale(1, -1); // 垂直翻转
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
    }

    public static Bitmap rotate(Bitmap bmp, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
    }

    public static Bitmap scaled(Bitmap bmp, int width, int height) {
        Matrix matrix = new Matrix();
        float scaleX = (float) width / bmp.getWidth();
        float scaleY = (float) height / bmp.getHeight();
        matrix.postScale(scaleX, scaleY);
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
    }

    public static Bitmap addBitmap(Bitmap bmp0, Bitmap bmp1,int angle, int left, int top) {
        if (bmp0 == null || bmp1 == null) return null;
        // 创建一个新的 Bitmap 来存储结果
        Bitmap result = Bitmap.createBitmap(bmp0.getWidth(), bmp0.getHeight(), bmp0.getConfig());
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        canvas.drawBitmap(bmp0, 0, 0, paint);// 绘制背景图
        canvas.drawBitmap(rotate(bmp1, angle), left, top, paint);// 绘制旋转后的图
        return result;
    } //将图旋转角度后画在另一张图的指定区域

    public static Bitmap tint(Bitmap bmp, ArrayList<District> districtsArrayList) {
        Bitmap result = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        for (District d : districtsArrayList) {
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setColor(d.color);
            canvas.drawRect(d.left, d.top, d.right, d.bottom, paint);
        }
        return result;
    }


}
