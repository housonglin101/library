package com.hsl.library.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.hsl.library.data.Conveyor;


public class MyView extends View {
    private static final String TAG = "调试信息";
    private Paint paint;//画笔
    private int plaidSize;
    private int plaidNum;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        plaidNum = 10;
        // 在尺寸改变后重新计算plaidSize
        plaidSize = Math.min(w, h) / plaidNum;
       // Log.d(TAG, "尺寸更改--plaidSize: " + plaidSize);
    }

    private void init() {
        isGridLines = false;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isGridLines) drawGridLines(canvas);//绘制网格线
        if (isConveyors) drawConveyors(canvas);
    }

    private void drawConveyors(Canvas canvas) {
        //  plaidNum  格子数量
        //  plaidSize  格子大小
        Log.i(TAG, "输送带: ON");
        int xPos, yPos;
        Bitmap bmp;
        Conveyor conveyor = new Conveyor(plaidSize);
        bmp = conveyor.getBitmap(1,0,2,0);
        int cs=0;
        for (int x = 0; x <= plaidNum; x++) {
            xPos = x * plaidSize;
            for (int y = 0; y <= plaidNum; y++) {

                Log.i(TAG, "格子大小: "+plaidSize);
                Log.i(TAG, "绘制输送带次数: "+cs);
                cs++;
                yPos = y * plaidSize;
                canvas.drawBitmap(bmp, xPos, yPos, paint);
            }
        }
    }
    public boolean isGridLines() {
        return isGridLines;
    }

    public void setGridLines(boolean gridLines) {
        this.isGridLines = gridLines;
        postInvalidate();
    }

    private boolean isGridLines;

    public boolean isConveyors() {
        return isConveyors;
    }

    public void setConveyors(boolean conveyors) {
        isConveyors = conveyors;
        postInvalidate();
    }

    private boolean isConveyors;
    private void drawGridLines(Canvas canvas) {
        Log.i(TAG, "网格线: ON");
        paint = new Paint();
        paint.setColor(0xffff0000);//画笔颜色
        paint.setStrokeWidth(10);//画笔宽度

        int width = getWidth();
        int height = getHeight();

        // 绘制垂直线条
        for (int x = 0; x <= width; x += plaidSize) {
            canvas.drawLine(x, 0, x, height, paint);
        }

        // 绘制水平线条
        for (int y = 0; y <= height; y += plaidSize) {
            canvas.drawLine(0, y, width, y, paint);
        }
    }


}
