package com.nhd.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhd on 2017/3/30.
 */

public class PathView extends View {
    private int count = 6;                //数据个数
    private float angle = (float) (Math.PI * 2 / count);
    private Paint paint, paint2, paint3;
    private Path path;
    private float radius;                   //网格最大半径
    private int centerX;                  //中心X
    private int centerY;
    private float maxValue = 100;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private List<Double> mDatas; //各维度分值

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        paint2 = new Paint();
        paint2.setColor(0xFF0000FF);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint3 = new Paint();
        paint3.setColor(0xFF000000);
        paint3.setTextSize(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mDatas) {
            return;
        }
        canvas.translate(centerX, centerY);
        //绘制蛛网
        drawPolygon(canvas);
        //绘制对角线
        drawLines(canvas);
        //绘制绘画区域
        drawArea(canvas);
        //绘制文字
        drawText(canvas);
    }

    private void drawArea(Canvas canvas) {
        Path path = new Path();
        paint2.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = mDatas.get(i) / maxValue;
            float x = (float) (Math.cos(angle * i) * radius * percent);
            float y = (float) (Math.sin(angle * i) * radius * percent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 5, paint2);
        }
        paint2.setAlpha(127);
        canvas.drawPath(path, paint2);
    }

    private void drawPolygon(Canvas canvas) {
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int i = 1; i < count; i++) {//中心点不用绘制
            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(curR, 0);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (curR * Math.cos(angle * j));
                    float y = (float) (curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, paint);
        }
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(0, 0);
            float x = (float) (Math.cos(angle * i) * radius);
            float y = (float) (Math.sin(angle * i) * radius);
            path.lineTo(x, y);
            canvas.drawPath(path, paint);
        }
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = paint3.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) ((radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) ((radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限
                canvas.drawText(titles[i], x, y, paint3);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第3象限
                canvas.drawText(titles[i], x, y, paint3);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第2象限
                float dis = paint3.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, paint3);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第1象限
                float dis = paint3.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, paint3);
            }
        }
    }

    public void setData(List<Double> datas) {
        mDatas = new ArrayList<>(datas);
        invalidate();
    }
}
