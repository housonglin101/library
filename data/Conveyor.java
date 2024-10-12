package com.hsl.library.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.hsl.library.R;
import com.hsl.library.tool.MyResources;
import com.hsl.library.tool.check.MyCheck;
import com.hsl.library.tool.exception.MyException;


public class Conveyor {
    private MyResources mr = new MyResources();

    private int COLOR_LAYER_1 = mr.getColor(R.color.棕色);
    private int COLOR_LAYER_2 = mr.getColor(R.color.橘色);
    private int COLOR_LAYER_3 = mr.getColor(R.color.灰色);
    private int COLOR_LAYER_4 = mr.getColor(R.color.黄色);


    private Bitmap bmp;
    private Canvas canvas;
    private Paint paint;

    private int size =100;
    private  int outSize=size;
    private int l1 = size / 25, l3 = size / 10;
    private int b25 = size / 4, b75 = size * 3 / 4;
    private int b28 = b25 + l1, b34 = b25 + l3, b66 = b75 - l3, b72 = b75 - l1;
    private int b10 = size / 10, b40 = size * 4 / 10, b45 = size * 45 / 100, b80 = size * 8 / 10;
    public Conveyor() {
        bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bmp);
        paint = new Paint();
    }
    public Conveyor(int outSize) {
        this();
        this.outSize = outSize;

    }
public void setSize(int size){
        this.outSize = size;
}
    private void drawLetFloor(String trend) {
        // 绘制接口
        switch (trend) {
            case "left":
                drawPath(COLOR_LAYER_1, 0, b25, b25, b75);
                drawPath(COLOR_LAYER_2, 0, b28, b28, b72);
                drawPath(COLOR_LAYER_3, 0, b34, b34, b66);
                break;
            case "top":
                drawPath(COLOR_LAYER_1, b25, 0, b75, b25);
                drawPath(COLOR_LAYER_2, b28, 0, b72, b28);
                drawPath(COLOR_LAYER_3, b34, 0, b66, b34);
                break;
            case "right":
                drawPath(COLOR_LAYER_1, b75, b25, size, b75);
                drawPath(COLOR_LAYER_2, b66, b28, size, b72);
                drawPath(COLOR_LAYER_3, b66, b34, size, b66);
                break;
            default: // bottom or other
                drawPath(COLOR_LAYER_1, b25, b75, b75, size);
                drawPath(COLOR_LAYER_2, b28, b72, b72, size);
                drawPath(COLOR_LAYER_3, b34, b66, b66, size);
        }
    }

    private void drawPath(int color, float left, float top, float right, float bottom) {
        paint.setColor(color);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    private void drawPath(int color, float[]... point) {
        paint.setColor(color);
        canvas.drawPath(MyCheck.checkPath(point), paint);
    }

    private void drawArrow(String trend, int left, int top) {
        int width = size / 10;
        int height = size / 10 * 2;
        float[][] points = new float[6][];
        switch (trend) {
            case "left":
                points[0] = new float[]{left, (float) (height * 0.55 + top)};
                points[1] = new float[]{left, (float) (height * 0.45 + top)};
                points[2] = new float[]{(float) (width * 0.9 + left), top};
                points[3] = new float[]{left + width, top};
                points[4] = new float[]{left + width, top + height};
                points[5] = new float[]{(float) (width * 0.9 + left), top + height};
                break;
            case "top":
                points[0] = new float[]{(float) (left + (height * 0.45)), top};
                points[1] = new float[]{(float) (left + (height * 0.55)), top};
                points[2] = new float[]{left + height, (float) (top + (width * 0.9))};
                points[3] = new float[]{left + height, top + width};
                points[4] = new float[]{left, top + width};
                points[5] = new float[]{left, (float) (top + (width * 0.9))};
                break;
            case "right":
                points[0] = new float[]{left + width, (float) (height * 0.45 + top)};
                points[1] = new float[]{left + width, (float) (height * 0.55 + top)};
                points[2] = new float[]{(float) (width * 0.1 + left), top + height};
                points[3] = new float[]{left, top + height};
                points[4] = new float[]{left, top};
                points[5] = new float[]{(float) (width * 0.1 + left), top};
                break;
            default: // bottom or other
                points[0] = new float[]{(float) (height * 0.55 + left), top + width};
                points[1] = new float[]{(float) (height * 0.45 + left), top + width};
                points[2] = new float[]{left, (float) (width * 0.1 + top)};
                points[3] = new float[]{left, top};
                points[4] = new float[]{left + height, top};
                points[5] = new float[]{left + height, (float) (width * 0.1 + top)};
        }
        drawPath(COLOR_LAYER_4, points);
    }

    public Bitmap getBitmap(int left, int top, int right, int bottom) {
        Log.i("调试信息", "outSize: " + outSize);
        // 中心区域
        drawPath(COLOR_LAYER_1, b25, b25, b75, b75);
        drawPath(COLOR_LAYER_2, b28, b28, b72, b72);
        drawPath(COLOR_LAYER_3, b34, b34, b66, b66);

        boolean outlet = true;
        // 接口和箭头
        if (left == 1 && outlet) {
            drawLetFloor("left");
            drawArrow("left", b10, b40);
            drawArrow("left", b45, b40);
            outlet = false;
        } else if (left == 2) {
            drawLetFloor("left");
            drawArrow("right", b10, b40);
        }

        if (top == 1 && outlet) {
            drawLetFloor("top");
            drawArrow("top", b40, b10);
            drawArrow("top", b40, b45);
            outlet = false;
        } else if (top == 2) {
            drawLetFloor("top");
            drawArrow("bottom", b40, b10);
        }

        if (right == 1 && outlet) {
            drawLetFloor("right");
            drawArrow("right", b80, b40);
            drawArrow("right", b45, b40);
            outlet = false;
        } else if (right == 2) {
            drawLetFloor("right");
            drawArrow("left", b80, b40);
        }

        if (bottom == 1 && outlet) {
            drawLetFloor("bottom");
            drawArrow("bottom", b40, b80);
            drawArrow("bottom", b40, b45);
            outlet = false;
        } else if (bottom == 2) {
            drawLetFloor("bottom");
            drawArrow("top", b40, b80);
        }

        if (outlet) {
            throw new MyException("没有出口");
        }
        //将图片缩放到  outSize * outSize 的尺寸
        bmp = Bitmap.createScaledBitmap(bmp, outSize, outSize, true);
        return bmp;
    }
}
