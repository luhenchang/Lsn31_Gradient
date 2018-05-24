package com.example.ls.lsn31_gradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者:王飞
 * 邮箱:1276998208@qq.com
 * create on 2018/5/23 14:37
 */
public class MyGradienView extends View {

    private int width, height;
    private Paint paint;
    private Bitmap bitmap;
    private float scale;

    public MyGradienView(Context context) {
        super(context);
        paint = new Paint();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bitmap = ((BitmapDrawable) (getResources().getDrawable(R.drawable.avatar3, null))).getBitmap();
        } else {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar3);
        }
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    public MyGradienView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        //canvas.drawBitmap(bitmap,0,0,paint);
        /*
         * TileMode.MIRROR:拉伸最后一个像素去铺满剩下的地方
         * TileMode.REPEAT:重复图片铺平整个画面(电脑设置纸币)
         * TileMode.CLAMP:拉伸最后一个像素去铺满剩下的地方
         * */
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        //    public void drawRect(float left, float top, float right, float bottom, @NonNull Paint paint) {
        //canvas.drawRect(new RectF(0,0,1800,1800),paint);
        /*paint.setShader(bitmapShader);
        paint.setAntiAlias(true);*/

        //通过ShapeDrawable也可以实现
        ShapeDrawable shapeDrawable=new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(0,0,width,width);
        shapeDrawable.draw(canvas);


        // canvas.drawCircle(height / 2, height / 2, height / 2, paint);//这样的结果如果图片高度比宽度长很多。水平宽度看起来被拉长了很多。如果是人的头像那么太丑了吧。不行的。
        //解决方法:我们可以利用矩阵这里让宽度和高度整体放大。不让其变形。让圆形的直径刚好在矩形内部那么就满足了。
       /* Matrix matrix = new Matrix();//矩阵缩放，位移，选择，透视
        scale = Math.max(height, width)*1.0f / Math.min(height, width);
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);
        if(height>width){
            canvas.drawCircle(scale*Math.min(height, width)/ 2f, scale*Math.max(height, width)/ 2f, Math.max(height, width) / 2f, paint);

        }else{
            canvas.drawCircle(scale*Math.max(height, width)/ 2f, scale*Math.min(height, width)/ 2f, Math.max(height, width) / 2f, paint);

        }*/

       // canvas.drawOval(new RectF(0,0,width,height),paint);
       //  canvas.drawOval(new RectF(0,0,width,width),paint);

    }
}
