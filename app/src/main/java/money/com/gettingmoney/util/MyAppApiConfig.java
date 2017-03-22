package money.com.gettingmoney.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.activity.MainActivity;


public class MyAppApiConfig extends Application{
    private static List<Activity> activities;
    public static Application application;
    //外网
//    public static final String HOST_URL = "http://101.201.147.52:8071/";
//    public static final String IMGHOST_URL = "http://101.201.147.52:8090";
    //内网



//  向
//    public static final String HOST_URL = "http://192.168.0.134:8090/";
//    潘
    public static final String HOST_URL = "http://192.168.0.132:8077/";
//    李
//  public static final String HOST_URL = "http://192.168.0.125:8076/";

    public static final String IMGHOST_URL = "http://192.168.0.132:8071";

    public static MainActivity MAIN_ACTIVITY = null;




    /**
     * 把Activity放入集合当中，统一管理。
     *
     * @param activity 要添加的Activity对象
     */
    public static void addActivity(Activity activity) {
        if (activities == null)
            activities = new ArrayList<>();
        activities.add(activity);
    }

    /**
     * 关闭集合中所有的activity界面
     */
    public static void killAllActivity() {
        if (activities != null && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
    }

    /**
     * 关闭应用，关闭所有的界面，并结束进程
     */
    public static void closeApplication() {
        killAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
//拨打电话
    public static void call(Context context,String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
