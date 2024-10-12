package com.hsl.library.tool.check;

import android.graphics.Path;
import android.util.Log;

import com.hsl.library.tool.exception.MyException;


public class MyCheck {
    public static Path checkPath(float[]... point) {
        Path path = new Path();
        int num = 0;

        if (point == null) {
            throw new MyException("E001", "point 不能为空.");
        }
        for (float[] p1 : point) {
            if (p1 != null && p1.length >= 2) {
                if (num==0) { // 没找到第一个点
                    path.moveTo(p1[0], p1[1]);
                } else {
                    path.lineTo(p1[0], p1[1]);
                }
                num++;
                //Log.i("调试信息", "有效值: " + num);
            }else{

                Log.i("调试信息", "无效值+1");
            }
        }
        if (num < 2) {
            throw new MyException("E002", "point 至少需要两个有效点.");
        }
        path.close();
        return path;
    }



}
