package com.nhd.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nhd on 2017/3/29.
 */

public class CanvasOperateView extends View {
    private Paint paint;
    private int width, height;

    public CanvasOperateView(Context context) {
        this(context, null);
    }

    public CanvasOperateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将坐标原点位移到控件的正中心
        canvas.drawLines(new float[]{
                width / 2, 0, width / 2, height
                , 0, height / 2, width, height / 2
        }, paint);
        canvas.translate(width / 2, height / 2);
        RectF rectF = new RectF(0, 0, 100, 100);
        canvas.drawRect(rectF, paint);
        //错切
        // X = x + sx * y 100+1*100 = 200
        // Y = sy * x + y 0*100+100 = 100
        canvas.skew(0, 0);
        canvas.drawRect(rectF, paint);
    }
}
