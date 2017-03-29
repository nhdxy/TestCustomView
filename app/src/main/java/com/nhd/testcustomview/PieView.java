package com.nhd.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼状图
 * Created by nhd on 2017/3/29.
 */

public class PieView extends View {
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private Paint paint;
    private int width, height;
    private RectF rectF;
    private List<PieBean> mDatas;
    private int startAngle = 0;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        float radius = (float) (Math.min(width, height) / 2 * 0.8);
        rectF = new RectF(-radius, -radius, radius, radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mDatas) {
            return;
        }
        canvas.translate(width / 2, height / 2);
        for (PieBean pieBean : mDatas) {
            paint.setColor(pieBean.color);
            canvas.drawArc(rectF, startAngle, pieBean.angle, true, paint);
            startAngle += pieBean.angle;
        }
    }

    public void setData(List<PieBean> datas) {
        mDatas = new ArrayList<>(datas);
        float sumValue = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.get(i).color = mColors[i];
            sumValue += mDatas.get(i).value;
        }
        for (PieBean pieBean : mDatas) {
            pieBean.angle = pieBean.value / sumValue * 360;
        }
        invalidate();
    }
}
