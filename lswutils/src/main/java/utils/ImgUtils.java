package utils;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Created by lsw
 * @date: 2018/8/31 12:25
 * @description:
 */
public class ImgUtils {
    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            return null;
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }
    //得到图片旋转角度
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("Tag", "旋转角度：" + degree);
        return degree;
    }

    /**
     *
     *
     * @param resources
     */
    public static File compressImageT(Bitmap bitmap, Resources resources, String pathName, String uname, String addr, String time) {

        bitmap = WaterUtils.createBitmapT(bitmap, uname, time, addr ,pathName, resources);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        }

        int options = 100;
        Log.e("Tag", "质量压缩前：" + baos.toByteArray().length / 1024 + "+++"  );
        while (baos.toByteArray().length / 1024 > 150&&options>20) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 20;//每次都减少20
            Log.e("Tag", "压缩：--" + options);
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }

        FileOutputStream b = null;
        File file = new File(pathName);

        try {
            b = new FileOutputStream(pathName);
            b.write(baos.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("Tag", "文件夹");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Tag", "IO流");
        } finally {
            try {
                if (b != null) {
                    b.flush();
                    b.close();
                }
                bitmap.recycle();


                // img.recycle();
                Log.e("Tag", "finally：" + bitmap.isRecycled());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Tag", "IO流++");
            }
        }
        return file;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackgroundView(ImageView image_, String path) {
        //把历史的ImageView 图片对象（image_)释放

//        BitmapDrawable bitmapDrawable = (BitmapDrawable) image_.getBackground();
//
//        if (bitmapDrawable != null) {
//
//            Bitmap hisBitmap = bitmapDrawable.getBitmap();
//
//            if (hisBitmap.isRecycled() == false) {
//                hisBitmap.recycle();
//            }
//        }


        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inPreferredConfig = Bitmap.Config.ALPHA_8;

        options.inSampleSize = 2;

        options.inPurgeable = true; // bitmap can be purged to disk

        options.inInputShareable = true;
        addBitmapToCache(BitmapFactory.decodeFile(path, options), path);
        Drawable bd = new BitmapDrawable(getBitmapByResid(path));
        image_.setBackground(bd);

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setImgView(ImageView image_, String path) {
        //把历史的ImageView 图片对象（image_)释放

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inPreferredConfig = Bitmap.Config.ALPHA_8;

        options.inSampleSize = 2;

        options.inPurgeable = true; // bitmap can be purged to disk

        options.inInputShareable = true;
        addBitmapToCache(BitmapFactory.decodeFile(path, options), path);
        Drawable bd = new BitmapDrawable(getBitmapByResid(path));
        image_.setImageDrawable(bd);

    }

    private static void addBitmapToCache(Bitmap bm, String path) {
        // 强引用的Bitmap对象
//        Bitmap bm = BitmapFactory.decodeStream(context.getResources().openRawResource(resid));
        // 软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = new SoftReference(bm);
        // 添加该对象到Map中使其缓存
        imageCache.put(path, softBitmap);
    }

    private static Bitmap getBitmapByResid(String path) {
        // 从缓存中取软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = imageCache.get(path);
        // 判断是否存在软引用
        if (softBitmap == null) {
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        Bitmap bitmap = softBitmap.get();
        return bitmap;
    }
    private static Map<String, SoftReference<Bitmap>> imageCache = new HashMap();



    /**
     * Save image to the SD card
     *
     * @param photoBitmap
     * @param photoName
     * @param path
     */
    public static String savePhoto(Bitmap photoBitmap, String path,
                                   String photoName) {
        String localPath = null;
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File photoFile = new File(path, photoName);
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                            fileOutputStream)) { // 转换完成
                        localPath = photoFile.getPath();
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return localPath;
    }

    /**
     * 转换图片成圆形
     *
     * @param bitmap
     *            传入Bitmap对象
     * @param tempUri
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap, Uri tempUri) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            left = 0;
            top = 0;
            right = width;
            bottom = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);// 设置画笔无锯齿

        canvas.drawARGB(0, 0, 0, 0); // 填充整个Canvas
        paint.setColor(color);

        // 以下有两种方法画圆,drawRounRect和drawCircle
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);//
        // 画圆角矩形，第一个参数为图形显示区域，第二个参数和第三个参数分别是水平圆角半径和垂直圆角半径。
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));// 设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452
        canvas.drawBitmap(bitmap, src, dst, paint); // 以Mode.SRC_IN模式合并bitmap和已经draw了的Circle

        return output;
    }
}
