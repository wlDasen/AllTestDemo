package net.george.alltestdemo.view;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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
        test6(canvas);
    }

    /**
     * Init Global Paint
     */
    private void initPain() {
        mPaint = new Paint();
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
