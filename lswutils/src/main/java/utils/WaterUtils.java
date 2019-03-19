package utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;

import hh.sfqj.com.lswutils.R;


/**
 * @author zslz-001
 * @date 2017/5/15.
 */
public class WaterUtils {


    public static Bitmap createBitmapT(Bitmap src, String uname, String time, String addr, String srcpath, Resources resources) {
        if (src == null) {
            return src;
        }
        // 获取原始图片与水印图片的宽与高
        int w = src.getWidth();
        int h = src.getHeight();
//        Log.e("lsw","new ---- waterUtils");
        Bitmap usrBitmap = resizeBitmap(((BitmapDrawable) resources.getDrawable(R.mipmap.icon_img_user)).getBitmap(), 28, 28);
        Bitmap timeBitmap = resizeBitmap(((BitmapDrawable) resources.getDrawable(R.mipmap.icon_img_time)).getBitmap(), 28, 28);
        Bitmap addrBitmap = resizeBitmap(((BitmapDrawable) resources.getDrawable(R.mipmap.icon_img_addr)).getBitmap(), 22, 30);

        int ww = usrBitmap.getWidth();
        int wh = usrBitmap.getHeight();

        int www = timeBitmap.getWidth();
        int wwh = timeBitmap.getHeight();

        int wwww = addrBitmap.getWidth();
        int wwwh = addrBitmap.getHeight();

        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas mCanvas = new Canvas(newBitmap);
        // 往位图中开始画入src原始图片
        mCanvas.drawBitmap(src, 0, 0, null);
        // 在src的右下角添加水印
        Paint paint = new Paint();
        paint.setAlpha(255);
        mCanvas.drawBitmap(src, 0, 0, paint);
        // 开始加入文字
        if (null != uname) {
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.WHITE);
            if (srcpath != null && (!"".equals(srcpath)) && (!"null".equals(srcpath))) {
                textPaint.setTextSize(18);
            } else {
                textPaint.setTextSize(18);
            }

            String familyName = "宋体";
            Typeface typeface = Typeface.create(familyName,
                    Typeface.BOLD_ITALIC);
            textPaint.setTypeface(typeface);
            textPaint.setTextAlign(Paint.Align.LEFT);

            //这里是自动换行的
            /*StaticLayout layout = new StaticLayout(title, textPaint,w, Layout.Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
            layout.draw(mCanvas);*/

           /* mCanvas.drawText("人员："+uname, 20, h-80, textPaint);
            mCanvas.drawText("时间："+time, 20, h-50, textPaint);
            mCanvas.drawText("地点："+addr, 20, h-20, textPaint);*/

            if(usrBitmap == null){
                usrBitmap = resizeBitmap(((BitmapDrawable) resources.getDrawable(R.mipmap.icon_img_user)).getBitmap(), 28, 28);
            }
            if(timeBitmap == null){
                timeBitmap = resizeBitmap(((BitmapDrawable) resources.getDrawable(R.mipmap.icon_img_time)).getBitmap(), 28, 28);
            }
            if(addrBitmap == null){
                addrBitmap = resizeBitmap(((BitmapDrawable) resources.getDrawable(R.mipmap.icon_img_addr)).getBitmap(), 22, 30);
            }

            mCanvas.drawBitmap(usrBitmap, 20, h - wwwh - wwh - wh - 30, paint);
            mCanvas.drawBitmap(timeBitmap, 20, h - wwwh - wwh - 20, paint);
            mCanvas.drawBitmap(addrBitmap, 20, h - wwwh - 10, paint);

            /*mCanvas.drawText(uname, ww + 30, h - wwwh - wwh - wh-10 , textPaint);
            mCanvas.drawText(time, ww + 30, h - wwwh - wwh , textPaint);
            mCanvas.drawText(addr, ww + 30, h - wwwh+10 , textPaint);*/
            if (uname != null && (!"".equals(uname))) {
                mCanvas.drawText(uname, ww + 30, h - wwwh - wwh - wh - 10, textPaint);
            } else {
                mCanvas.drawText("", ww + 30, h - wwwh - wwh - wh - 10, textPaint);
            }
            if (time != null && (!"".equals(time))) {
                mCanvas.drawText(time, ww + 30, h - wwwh - wwh, textPaint);
            } else {
                mCanvas.drawText("", ww + 30, h - wwwh - wwh, textPaint);
            }
            if (addr != null && (!"".equals(addr))) {
                mCanvas.drawText(addr, ww + 30, h - wwwh + 10, textPaint);
            } else {
                mCanvas.drawText("", ww + 30, h - wwwh + 10, textPaint);
            }

        }
        mCanvas.save();
        mCanvas.restore();

        usrBitmap.recycle();
        timeBitmap.recycle();
        addrBitmap.recycle();

        return newBitmap;
    }

    /**
     * 进行添加水印图片和文字
     *
     * @param src
     * @return
     */
    public static Bitmap createBitmap(Bitmap src, String uname, String time, String addr, String srcpath, Bitmap imgUser, Bitmap imgTime, Bitmap imgAddr) {
        if (src == null) {
            return src;
        }
        // 获取原始图片与水印图片的宽与高
        int w = src.getWidth();
        int h = src.getHeight();

        Bitmap usrBitmap = resizeBitmap(imgUser, 28, 28);
        Bitmap timeBitmap = resizeBitmap(imgTime, 28, 28);
        Bitmap addrBitmap = resizeBitmap(imgAddr, 22, 30);

        int ww = usrBitmap.getWidth();
        int wh = usrBitmap.getHeight();

        int www = timeBitmap.getWidth();
        int wwh = timeBitmap.getHeight();

        int wwww = addrBitmap.getWidth();
        int wwwh = addrBitmap.getHeight();

        //   int ww = waterMak.getWidth();
        //   int wh = waterMak.getHeight();
        //    Log.i("jiangqq", "w = " + w + ",h = " + h + ",ww = " + ww + ",wh = "
        //            + wh);
        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas mCanvas = new Canvas(newBitmap);
        // 往位图中开始画入src原始图片
        mCanvas.drawBitmap(src, 0, 0, null);
        // 在src的右下角添加水印
        Paint paint = new Paint();
        paint.setAlpha(255);
        mCanvas.drawBitmap(src, 0, 0, paint);
        // 开始加入文字
        if (null != uname) {
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.WHITE);
            if (srcpath != null && (!"".equals(srcpath)) && (!"null".equals(srcpath))) {
                textPaint.setTextSize(18);
            } else {
                textPaint.setTextSize(18);
            }

            String familyName = "宋体";
            Typeface typeface = Typeface.create(familyName,
                    Typeface.BOLD_ITALIC);
            textPaint.setTypeface(typeface);
            textPaint.setTextAlign(Paint.Align.LEFT);

            //这里是自动换行的
            /*StaticLayout layout = new StaticLayout(title, textPaint,w, Layout.Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
            layout.draw(mCanvas);*/

           /* mCanvas.drawText("人员："+uname, 20, h-80, textPaint);
            mCanvas.drawText("时间："+time, 20, h-50, textPaint);
            mCanvas.drawText("地点："+addr, 20, h-20, textPaint);*/

            mCanvas.drawBitmap(usrBitmap, 20, h - wwwh - wwh - wh - 30, paint);
            mCanvas.drawBitmap(timeBitmap, 20, h - wwwh - wwh - 20, paint);
            mCanvas.drawBitmap(addrBitmap, 20, h - wwwh - 10, paint);

            /*mCanvas.drawText(uname, ww + 30, h - wwwh - wwh - wh-10 , textPaint);
            mCanvas.drawText(time, ww + 30, h - wwwh - wwh , textPaint);
            mCanvas.drawText(addr, ww + 30, h - wwwh+10 , textPaint);*/
            if (uname != null && (!"".equals(uname))) {
                mCanvas.drawText(uname, ww + 30, h - wwwh - wwh - wh - 10, textPaint);
            } else {
                mCanvas.drawText("", ww + 30, h - wwwh - wwh - wh - 10, textPaint);
            }
            if (time != null && (!"".equals(time))) {
                mCanvas.drawText(time, ww + 30, h - wwwh - wwh, textPaint);
            } else {
                mCanvas.drawText("", ww + 30, h - wwwh - wwh, textPaint);
            }
            if (addr != null && (!"".equals(addr))) {
                mCanvas.drawText(addr, ww + 30, h - wwwh + 10, textPaint);
            } else {
                mCanvas.drawText("", ww + 30, h - wwwh + 10, textPaint);
            }

        }
        mCanvas.save();
        mCanvas.restore();
        return newBitmap;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
        if (bitmap != null) {
//            Log.e("Tag", "原宽：" + bitmap.getWidth() + "，原高：" + bitmap.getHeight());
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newWidth = w;
            int newHeight = h;
            float scaleWight = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
//            Log.e("Tag", "压缩比：" + scaleWight + "+++" + scaleHeight);
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWight, scaleHeight);
            Bitmap res = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

//            Log.e("Tag", "现宽：" + res.getWidth() + ",现高：" + res.getHeight());
            return res;

        } else {
            return null;
        }
    }

}
