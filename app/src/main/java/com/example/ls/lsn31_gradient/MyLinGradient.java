package com.example.ls.lsn31_gradient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 作者:王飞
 * 邮箱:1276998208@qq.com
 * create on 2018/5/23 16:15
 */
@SuppressLint("AppCompatCustomView")
public class MyLinGradient extends TextView {


    private float x0 = 0;
    private float y0 = 0;
    private float x1;
    private float y1;
    private Paint paint;
    private float textWidth;
    private LinearGradient linearGradient;
    private float textHight;
    private float translateX;
    private float detelx;
    private Matrix matrix;

    public MyLinGradient(Context context) {
        super(context);
    }

    public MyLinGradient(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //当视图出现时候会调用将一些放在这里可以减轻负担
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint=getPaint();
        textWidth = paint.measureText(getText().toString());
        detelx = textWidth / getText().toString().length();
        textHight = paint.measureText(getText().toString());
        int GradientSize =(int) (3*textWidth/getText().length());
        //LinearGradient(float x0, float y0, float x1, float y1, @NonNull @ColorInt int colors[],@Nullable float positions[], @NonNull TileMode tile)
        paint = getPaint();
        //这里的x0是渐变x轴渐变开始的地方。x1是x轴渐变结束的地方。我们这里是从屏幕左边看不见的地方3个字符地方开始进入。以x轴水平三个字符开始运动。
        linearGradient = new LinearGradient(-GradientSize, y0, 0, 0, new int[]{0x22ffffff, 0xffffffff, 0x22ffffff}, new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);

        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        translateX +=detelx;
        if(translateX>textWidth+1||translateX<1){
          detelx=-detelx;
        }
        matrix.setTranslate(translateX, 0);
        linearGradient.setLocalMatrix(matrix);
        postInvalidateDelayed(200);


    }
}
