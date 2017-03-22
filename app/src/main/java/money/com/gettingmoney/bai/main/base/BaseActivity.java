package money.com.gettingmoney.bai.main.base;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.utils.SystemBarTintManager;
import money.com.gettingmoney.bai.main.utils.ToastUtils;
import money.com.gettingmoney.bai.main.utils.ZhUtils;
import money.com.gettingmoney.util.MyAppApiConfig;


/**
 * 基础BaseActivity
 *
 * @author Administrator
 */
public abstract class BaseActivity extends FragmentActivity {

    public static final int NET_ERROR = 1001;
    public static final int DATA_EMPTY = 1002;
    public static final int SERVER_EXCEPTION = 1003;

    LinearLayout vLayout;
    protected AlertDialog dialog;
    protected View viewLayout;
    protected MyToolBar toolBar;
    protected MyOccupying occupying;

    /**
     * 改变占位图和视图的切换
     *
     * @param type 0代表展示占位图，1代表展示视图
     */
    public void changeSimpleLayout(int type) {
        switch (type) {
            case 0:
                viewLayout.setVisibility(View.GONE);
                if (this.occupying != null) {
                    this.occupying.setVisibility(View.VISIBLE);
                } else {
                    viewLayout.setVisibility(View.VISIBLE);
                }
                break;
            case 1:
                viewLayout.setVisibility(View.VISIBLE);
                if (this.occupying != null) {
                    this.occupying.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void changePlaceHolderLayoutByType(int type, int imageResourceId, String tips){
        changePlaceHolderLayoutByType(type,imageResourceId,tips,"");
    }

    /**
     * 改变占位图
     *
     * @param type 占位图类型 NET_ERROR，DATA_EMPTY
     */
    public void changePlaceHolderLayoutByType(int type, int imageResourceId, String tips, String button) {
        switch (type) {
            case NET_ERROR:
                occupying.commit.setVisibility(View.VISIBLE);
                occupying.img.setImageResource(R.mipmap.ic_launcher);
                occupying.text.setText("网络不给力哦~");
                occupying.commit.setText("重新连接");
                changeSimpleLayout(0);
                occupying.SetCommitListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        requestInit();
                    }
                });
                //没有数据占位图
            case DATA_EMPTY:
                if (imageResourceId != -1)
                    occupying.img.setImageResource(imageResourceId);
                if (tips != null && tips.length() > 0)
                    occupying.text.setText(tips);
                if (button!=null&&button.length()>0){
                    occupying.commit.setVisibility(View.VISIBLE);
                    occupying.commit.setText(button);
                    occupying.commit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            requestInit();
                        }
                    });
                }

                changeSimpleLayout(0);
                break;
            case SERVER_EXCEPTION:
                if (imageResourceId != -1)
                    occupying.img.setImageResource(imageResourceId);
                if (tips != null && tips.length() > 0)
                    occupying.text.setText(tips);
                occupying.commit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestInit();
                    }
                });
                changeSimpleLayout(0);
                break;
        }
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        //为状态栏着色
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.statusBarColor);
    }

    public void requestData() {
        boolean networkConnected = ZhUtils.isNetworkConnected(this);
        if (!networkConnected)
            ToastUtils.MyToast(this, R.string.net_error);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


//    public void showBlackDialog() {
//        if (hud != null) {
//            hud.show();
//        }
//    }
//
//    public void dismissBlackDialog() {
//        if (hud != null&&hud.isShowing()) {
//            hud.dismiss();
//        }
//    }

    public void showSwipeRefresh(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    /**
     * 刷新多少秒后关闭
     *
     * @param swipeRefreshLayout
     * @param time
     */
    public void dismissSwipeRefresh(final SwipeRefreshLayout swipeRefreshLayout, long time) {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, time);
    }

    public void dismissSwipeRefresh(final SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null)
            dismissSwipeRefresh(swipeRefreshLayout, 1000);
    }



    /**
   *  1代表无占位图，0代表有展位图
     *
     */
//    public LinearLayout requestView(int layoutId, int type) {
//        return requestView(layoutId, toolBar, type);
//    }

    public LinearLayout requestView(int layoutId) {
        return requestView(layoutId, toolBar, 0);
    }

    public LinearLayout requestView(int layoutId,int stateColor) {
        return requestView(layoutId, toolBar, 0,stateColor);
    }


    public LinearLayout requestView(int layoutId, MyToolBar toolBar, int type) {
        //属于间接的加进去一个activity
        MyAppApiConfig.addActivity(this);
        Log.d("Debug","没有进行传过来参数");
        occupying = new MyOccupying(getBaseContext(),
                R.mipmap.ic_launcher, "", "重新连接");

        occupying.commit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestInit();
            }
        });
        if (vLayout == null) {
            vLayout = new LinearLayout(getBaseContext());
            vLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            vLayout.setOrientation(LinearLayout.VERTICAL);
        }
        vLayout.removeAllViews();
        viewLayout = LayoutInflater.from(getBaseContext()).inflate(layoutId,
                vLayout, false);
        if (toolBar != null) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ZhUtils.getStatusBarHeight(this)));
            linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
            Log.d("Debug","代码执行过啦");
            vLayout.addView(toolBar, 0);
//            不添加statebar的高度
            vLayout.addView(linearLayout, 0);
            toolBar.setLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        if (type == 0) {
            if (occupying != null) {
                vLayout.addView(this.occupying);
            }
        } else if (type == 1) {

        }
        vLayout.addView(viewLayout);
        if (ZhUtils.isNetworkConnected(getBaseContext())) {
            changeSimpleLayout(1);
        } else {
            changeSimpleLayout(0);
        }
        return vLayout;
    }

//如果statebar需要变化  调用这个
    public LinearLayout requestView(int layoutId, MyToolBar toolBar, int type,int stateColor) {
        //属于间接的加进去一个activity
        MyAppApiConfig.addActivity(this);
        Log.d("Debug","传过来的颜色的id是"+stateColor);
        occupying = new MyOccupying(getBaseContext(),
                R.mipmap.ic_launcher, "", "重新连接");

        occupying.commit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestInit();
            }
        });
        if (vLayout == null) {
            vLayout = new LinearLayout(getBaseContext());
            vLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            vLayout.setOrientation(LinearLayout.VERTICAL);
        }
        vLayout.removeAllViews();
        viewLayout = LayoutInflater.from(getBaseContext()).inflate(layoutId,
                vLayout, false);
        if (toolBar != null) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ZhUtils.getStatusBarHeight(this)));
//            linearLayout.setBackgroundColor(stateColor);
            linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            Log.d("Debug","daoda");
            vLayout.addView(toolBar, 0);
//            不添加statebar的高度
            vLayout.addView(linearLayout, 0);
            toolBar.setLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        if (type == 0) {
            if (occupying != null) {
                vLayout.addView(this.occupying);
            }
        } else if (type == 1) {

        }
        vLayout.addView(viewLayout);
        if (ZhUtils.isNetworkConnected(getBaseContext())) {
            changeSimpleLayout(1);
        } else {
            changeSimpleLayout(0);
        }
        return vLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        initWindow();
//        在所有的Activity 的onCreate 方法或在应用的BaseActivity的onCreate方法中添加：
//        MyAppApiConfig.mPushAgent.getInstance(this).onAppStart();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycle();
    }


    private void recycle() {
        recycleBaseDialog(dialog);
        toolBar = null;
        occupying = null;
    }


    public void recycleBaseDialog(AlertDialog dialog1) {
        if (dialog1 != null) {
            if (dialog1.isShowing()) {
                dialog1.dismiss();
            }
            dialog1 = null;
        }

    }

    public abstract void requestInit();

    /**
     * 服务器异常
     */
    public void fuwuqi() {
        occupying.commit.setVisibility(View.VISIBLE);
//        occupying.img.setImageResource(R.drawable.zhang_logo);
        occupying.text.setText("服务器异常哦~");
        changeSimpleLayout(0);
        occupying.commit.setText("重新连接");
        occupying.SetCommitListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestInit();
            }
        });
    }

    /**
     * 网络异常
     */
    public void wangluochaoshi() {
        occupying.commit.setVisibility(View.VISIBLE);
        occupying.img.setImageResource(R.mipmap.ic_launcher);
        occupying.text.setText("网络不给力哦~");
        occupying.commit.setText("重新连接");
        changeSimpleLayout(0);
        occupying.SetCommitListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestInit();
            }
        });
        occupying.commit.setText("重新连接");
    }

    protected void closeRefresh(final SwipeRefreshLayout layout1, long time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layout1.setRefreshing(false);
            }
        }, time);
    }

}
