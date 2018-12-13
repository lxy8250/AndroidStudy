package com.lxy.androidstudy.ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lxy.androidstudy.R;

/**
 * Created by lxy on 2018/12/6.
 */

public class HorizontalRuler extends View {

    private Paint textPaint;
    private Paint textUnitPaint;

    public HorizontalRuler(Context context) {
        this(context,null);
    }

    public HorizontalRuler(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(100);
        textPaint.setColor(getResources().getColor(R.color.green));
        textUnitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textUnitPaint.setTextSize(50);
        textUnitPaint.setColor(getResources().getColor(R.color.green));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("45",350,500,textPaint);
        // 文字的基线以文字的下方为准的
        canvas.drawText("kg",350 + textPaint.measureText("45"),500 - textPaint.measureText("45") / 2 ,textUnitPaint);
        canvas.drawText("kg",350 + textPaint.measureText("45"),500 ,textUnitPaint);
    }
}
