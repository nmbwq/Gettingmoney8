package money.com.gettingmoney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.User;
import money.com.gettingmoney.util.ShareUtil;

public class WelcomeActivity extends BaseActivity {

    /**
     * 启动页
     * @param savedInstanceState
     */
    private static final int GO_SPLASH = 1000;
    private static final int GO_HOME = 1001;
    // 延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 1000;
    private User user;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_SPLASH:
                    goSplash();
                    break;
                case GO_HOME:
                    goHome();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }



    private void init() {
        // 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面

        int isfirst = ShareUtil.getInstance().getisfirst(this);
        if (isfirst==0) {

            mHandler.sendEmptyMessageDelayed(GO_SPLASH, SPLASH_DELAY_MILLIS);
        } else {

            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        }

    }


    private void goHome() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }



    private void goSplash(){
        Intent intent = new Intent(WelcomeActivity.this, SplashActivity.class);
        WelcomeActivity.this.startActivity(intent);
        WelcomeActivity.this.finish();
    }
}
