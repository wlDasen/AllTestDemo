package net.george.alltestdemo.view;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import net.george.alltestdemo.R;

/**
 * @author George
 * @date 2018/1/10
 * @email georgejiapeidi@gmail.com
 * @describe 自定义View for CustomViewActivity
 */
public class CustomView extends View {
    private static final String TAG = "jpd-CustomView";
    private Paint mPaint;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "CustomView: 2");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");

        initPain();
        test7(canvas);
    }

    /**
     * Init Global Paint
     */
    private void initPain() {
        mPaint = new Paint();
    }

    /**
     * 颜色测试接口
     * @param canvas
     */
    private void test7(Canvas canvas) {
        /*
        mPaint.setColor(Color.parseColor("#009688"));
        canvas.drawRect(30, 30, 230, 180, mPaint);

        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.parseColor("#FF9800"));
        canvas.drawLine(300, 30, 450, 180, mPaint);

        mPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawText("HenCoder", 500, 100, mPaint);
        */
        // 3种模式：CLAMP、REPEAT、MIRROR
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
//        mPaint.setShader(shader);
        // Paint设置Shader之后，setColor/setARGB就不起作用了
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(100, 100, 800, 800, mPaint);
        Shader radialShader = new RadialGradient(600, 800, 200, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        mPaint.setShader(radialShader);
//        canvas.drawCircle(600, 800, 600, mPaint);
        Shader sweepGradient = new SweepGradient(300, 300, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"));
//        mPaint.setShader(sweepGradient);
//        canvas.drawCircle(300, 300, 200, mPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
//        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        Shader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
        mPaint.setShader(bitmapShader);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    /**
     * Path Test
     * @param canvas
     */
    @TargetApi(21)
    private void test6(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
//        path.addCircle(300, 300, 200, Path.Direction.CW);
//
//        path.lineTo(100, 100);
//        path.rLineTo(100, 0);
//
//        path.moveTo(200, 200);
//        path.lineTo(200, 0);

//        path.lineTo(100, 100);
//        path.arcTo(100, 100, 300, 300, -90, 90, false);

        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(150, 150);
        path.close();
        canvas.drawPath(path, mPaint);
    }
    /**
     * Line RoundRect Arc Test
     * @param canvas
     */
    @TargetApi(21)
    private void test5(Canvas canvas) {
//        canvas.drawLine(200, 200, 800, 500, mPaint);
//        canvas.drawRoundRect(100, 100, 500, 300, 50, 50, mPaint);
        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint);
        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint);
    }
    /**
     * Oval Test
     * @param canvas
     */
    @TargetApi(21)
    private void test4(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(50, 50, 350, 200, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawOval(50, 50, 350, 200, mPaint);
    }
    /**
     * Point Test
     * @param canvas
     */
    private void test3(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(50, 50, paint);
//
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawPoint(100, 100, paint);
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50};
        canvas.drawPoints(points, 2, 8, paint);
    }
    /**
     * Rect Test
     * @param canvas
     */
    private void test2(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(100, 100, 500, 500, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(600, 100, 1000, 500, paint);
    }
    /**
     * Circle Test
     */
    private void test1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);

        canvas.drawCircle(300, 300, 200, paint);
        paint.setAntiAlias(false);
        canvas.drawCircle(800, 300, 200, paint);
//        canvas.drawColor(Color.parseColor("#88880000"));
    }
}
