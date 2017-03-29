package com.nhd.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nhd on 2017/3/29.
 */

public class CustomView extends View {
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        initPaint(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制直线(起始坐标和终点坐标)
        canvas.drawLine(0, 0, 200, 200, paint);
        //绘制矩形(左上角坐标以及右下角坐标)
        canvas.drawRect(200, 200, 400, 400, paint);
        //绘制圆角矩形(左上角坐标、右下角坐标、x方向的弧度、y方向的弧度)
        RectF rectF = new RectF(400f, 400f, 600f, 600f);
        canvas.drawRoundRect(rectF, 30, 30, paint);
        //绘制椭圆(跟绘制矩形类似)椭圆为矩形的内切圆 如果长宽相等则为一个园
        canvas.drawOval(rectF, paint);
        //绘制圆 圆心坐标以及半径
        canvas.drawCircle(200, 200, 100, paint);
        //绘制圆弧
        //第一个参数相当于绘制了一个椭圆
        //第二个和第三个参数是从绘制的椭圆上面截取从起始角度到终止角度的那段圆弧
        //第四个参数表示是否与圆心连线
        //需要注意的是第三个参数表示的是扫过的角度也就是说 实际停止的角度=起始角度+扫过的角度
        RectF f = new RectF(100, 400, 400, 700);
        canvas.drawArc(f, 30, 90, false, paint);
    }

    private void initPaint(Context context) {
        paint = new Paint();
        //设置画笔颜色
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        //设置画笔格式 FILL为填充 STROKE为实线边框
        paint.setStyle(Paint.Style.STROKE);
        //画笔的宽度(边框线的宽度)
        paint.setStrokeWidth(10f);
    }
}
