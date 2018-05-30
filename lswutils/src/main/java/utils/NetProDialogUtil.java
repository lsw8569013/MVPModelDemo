package utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import hh.sfqj.com.lswutils.R;


public class NetProDialogUtil {

    public static Dialog createDialog(Context context, int layoutId, int themeId) {
        Dialog ret;
        ret = new Dialog(context, themeId);
        ret.setContentView(layoutId);
        ret.setCancelable(true);
        return ret;
    }

    public static Dialog loadingProgress(Context context) {
        final Dialog dialog = createDialog(context, R.layout.common_loading_dialog, R.style.CustomDialog);
        dialog.setCancelable(false);
        return dialog;
    }

    public static void setDialogParams(Activity context, Dialog dialog, int id){
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        int margin = (int) context.getResources().getDimension(id);
        lp.width = (int) (display.getWidth()) - margin; //设置宽度
        dialog.getWindow().setAttributes(lp);
    }

    public static void setDialogParams(Activity context, Dialog dialog){
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        int margin = (int) context.getResources().getDimension(R.dimen.dialog_width_margin);
        lp.width = (int) (display.getWidth()) - margin; //设置宽度
        dialog.getWindow().setAttributes(lp);

    }

}
