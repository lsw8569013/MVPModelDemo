package utils;
import android.app.Activity;

import java.util.Stack;

/**
 *一键退出
 */

public class MyActivityManage {
    private volatile  static MyActivityManage instance;
    private Stack<Activity> activityStack;//activity栈
    private MyActivityManage() {
    }
    //单例模式
    public static MyActivityManage getInstance() {
        if (instance == null) {
            synchronized (MyActivityManage.class){
                if(instance == null){
                    instance = new MyActivityManage();
                }
            }
        }
        return instance;
    }
    //把一个activity压入栈中
    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(actvity);
//    Log.d("MyActivityManager ", "size = " + activityStack.size());
    }
    //获取栈顶的activity，先进后出原则
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }
    //移除一个activity
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }
    //退出所有activity
    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null) break;
                popOneActivity(activity);
            }
        }
    }}
 