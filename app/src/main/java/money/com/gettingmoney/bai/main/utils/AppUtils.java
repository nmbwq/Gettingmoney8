package money.com.gettingmoney.bai.main.utils;

import android.content.Context;
import android.content.Intent;

import money.com.gettingmoney.bai.main.SimpleUserInfo;
import money.com.gettingmoney.util.MyAppApiConfig;


/**
 * Created by Administrator on 2016/8/17.
 * 项目的工具类
 */
public class AppUtils {

    /**
     * 跳到登录界面
     *
     * @param context 上下文
     */
    public static void startLoginActivity(Context context) {
        SimpleUserInfo.userId = 0;
        MyAppApiConfig.killAllActivity();
//        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
