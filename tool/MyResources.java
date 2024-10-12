package com.hsl.library.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;

public class MyResources {
    private static Context context = Constant.context;

    /**
     * 读取资源图片
     * @param id 图片资源id (R.drawable.xxx 或 R.drawable.vector_name)
     * @return
     */
    public static Bitmap getBitmap(int id) {
        try {
            // 尝试获取位图
            return BitmapFactory.decodeResource(context.getResources(), id);
        } catch (Exception e) {
            // 如果不是位图，则尝试获取矢量图并转换为位图
            VectorDrawable vectorDrawable = (VectorDrawable) context.getDrawable(id);
            if (vectorDrawable != null) {
                Bitmap bitmap = Bitmap.createBitmap(
                        vectorDrawable.getIntrinsicWidth(),
                        vectorDrawable.getIntrinsicHeight(),
                        Bitmap.Config.ARGB_8888
                );
                Canvas canvas = new Canvas(bitmap);
                vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                vectorDrawable.draw(canvas);
                return bitmap;
            } else {
                Log.e("MyResources", "Resource not found: " + id);
                return null;
            }
        }
    }

    /**
     * 获取颜色资源
     * @param id 颜色资源id (R.color.xxx)
     * @return
     */
    public static int getColor(int id) {
        return context.getResources().getColor(id);
    }
}
