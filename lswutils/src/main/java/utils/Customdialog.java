package utils;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import hh.sfqj.com.lswutils.R;


/**
 * 自定义对话框
 */
public class Customdialog extends Dialog implements DialogInterface{
    Context context;

    public Customdialog(Context context) {
        super(context);
        this.context = context;
    }

    public Customdialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public Customdialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        this(context, 0);

        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public  class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private boolean onclick=true;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private boolean click=true;
        private boolean close=true;
        private int size=0;

        public Builder(Context context) {
            this.context = context;
        }
        public Builder setMessageSize(int size) {
            this.size = size;
            return this;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setCancelable(boolean onclick) {
            this.onclick = onclick;
            return this;
        }
        public Builder setClose(boolean close) {
            this.close = close;
            return this;
        }
        /**
         * Set the Dialog message from resource
         *
         * @param
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }



        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener, boolean click) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            this.click=click;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener, boolean click) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            this.click=click;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        @SuppressLint("NewApi")
        public Customdialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            // instantiate the dialog with the custom Theme
            Customdialog dialog = null;
//            final Customdialog dialog = new Customdialog(context, R.style.Dialog);
//            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
//            dialog.addContentView(layout, new LayoutParams(
//                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
//            // set the dialog title
//            if (title != null) {
//                ((TextView) layout.findViewById(R.id.title)).setText(title);
//            } else {
//                layout.findViewById(R.id.title_li).setVisibility(View.GONE);
//            }
//            if(close){
//                layout.findViewById(R.id.close).setVisibility(View.VISIBLE);
//                layout.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                       // dialog.cancel();
//                        if(negativeButtonClickListener!=null){
//                            negativeButtonClickListener.onClick(dialog,
//                                    DialogInterface.BUTTON_NEGATIVE);
//                        }else {
//                            if(positiveButtonClickListener != null){
//                                positiveButtonClickListener.onClick(dialog,
//                                        DialogInterface.BUTTON_NEGATIVE);
//                            }
//                        }
//
//                    }
//                });
//            }else {
//                layout.findViewById(R.id.close).setVisibility(View.GONE);
//            }
//            // set the confirm button
//            if (positiveButtonText != null) {
//                ((Button) layout.findViewById(R.id.positiveButton))
//                        .setText(positiveButtonText);
//                if (positiveButtonClickListener != null) {
//                    ((Button) layout.findViewById(R.id.positiveButton))
//                            .setOnClickListener(new View.OnClickListener() {
//                                public void onClick(View v) {
//                                    positiveButtonClickListener.onClick(dialog,
//                                            DialogInterface.BUTTON_POSITIVE);
//                                }
//                            });
//                }
//            } else {
//                // if no confirm button just set the visibility to GONE
//                layout.findViewById(R.id.positiveButton).setVisibility(
//                        View.GONE);
//            }
//            // set the cancel button
//            if (negativeButtonText != null) {
//                ((Button) layout.findViewById(R.id.negativeButton))
//                        .setText(negativeButtonText);
//                btn= (Button) layout.findViewById(R.id.positiveButton);
//                if(click){
//                    btn.setText(positiveButtonText);
//                }else {
////                    timer = new Timer();
////                    myTask = new MyTask();
////                    timer.schedule(myTask, 0, 1000);
//
//                    handler.sendEmptyMessage(1);
//                }
//
//                if (negativeButtonClickListener != null) {
//                    ((Button) layout.findViewById(R.id.negativeButton))
//                            .setOnClickListener(new View.OnClickListener() {
//                                public void onClick(View v) {
//                                    negativeButtonClickListener.onClick(dialog,
//                                            DialogInterface.BUTTON_NEGATIVE);
//                                }
//                            });
//                }
//            } else {
//                // if no confirm button just set the visibility to GONE
//                layout.findViewById(R.id.negativeButton).setVisibility(
//                        View.GONE);
//                ((Button) layout.findViewById(R.id.positiveButton)).setBackgroundResource(R.drawable.shape_dialog_search_ok);
//            }
//            // set the content message
//            if (message != null) {
//                if(size>0){
//                    ((TextView) layout.findViewById(R.id.message)).setTextSize(size);
//                }
//                ((TextView) layout.findViewById(R.id.message)).setText(message);
//            } else if (contentView != null) {
//                // if no message set
//                // add the contentView to the dialog body
//                ((LinearLayout) layout.findViewById(R.id.content))
//                        .removeAllViews();
//                ((LinearLayout) layout.findViewById(R.id.content))
//                        .addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//            }
//            if (null==positiveButtonText&&null==positiveButtonText){
//                layout.findViewById(R.id.button_li).setVisibility(View.GONE);
//            }
//            dialog.setContentView(layout);
//            dialog.setCancelable(onclick);
            return dialog;
        }
        private int i = 0;
        //        private Timer timer;
//        MyTask myTask;
        private Button btn;


        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (i > 0) {
//                                    if(negativeButtonText!=null){
//                                        btn.setBackgroundResource(R.drawable.shape_dialog_search_gray_ok1);
//                                    }else {
//                                        btn.setBackgroundResource(R.drawable.shape_dialog_search_gray_ok);
//                                    }
                                    btn  .setText((i--) + "s ");
                                    btn.setEnabled(false);

                                    // dialog.setO.setText();
                                    handler.sendEmptyMessageDelayed(1,1000);
                                } else {
                                    btn.setEnabled(true);

                                    btn.setText(positiveButtonText);
//                                    if(negativeButtonText!=null){
//                                        btn.setBackgroundResource(R.drawable.shape_dialog_search_ok1);
//                                    }else {
//                                        btn.setBackgroundResource(R.drawable.shape_dialog_search_ok);
//                                    }
                                    // q去掉time 没有必要 2018 5-5
//                                    if (myTask != null) {
//                                        myTask.cancel();
//                                        myTask = null;
//                                    }
//                                    if (timer != null) {
//                                        timer.purge();
//                                        timer.cancel();
//                                        timer = null;
//                                    }
                                    i = 5;
                                }
                            }
                        });

                        break;


                }
            }
        };
//        class MyTask extends TimerTask {
//            @Override
//            public void run() {
//
//            }
//        }
    }

    @Override
    public void show() {
        if(context instanceof Activity){
            if(((Activity) context).isDestroyed()){
                return;
            }
        }
        super.show();
    }

    @Override
    public void onDetachedFromWindow() {
        context = null;
        super.onDetachedFromWindow();
    }
}

