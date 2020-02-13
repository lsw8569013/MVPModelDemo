package utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;



/**
 * Toast统一管理类
 */
public class ToastUtil {

    public static boolean isShow = true;
    private static Context context;


    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void setContext(Context context) {
        ToastUtil.context = context;
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow){
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     */
    public static void show(Context context, CharSequence message) {
        if(context == null){
            Log.e("TAG",message.toString());
            return;
        }
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static void show( CharSequence message) {
        if (isShow && context!= null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 控制弹出次数，用户体验不好
     *
     *
     */
    protected static Toast toast   = null;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showToast(Context context, String s){
        if(toast==null){
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime= System.currentTimeMillis();
        }else{
            twoTime= System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime> Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }

    public static void showToast(Context context, int resId){
        showToast(context, context.getString(resId));
    }
}