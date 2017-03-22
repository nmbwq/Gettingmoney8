package money.com.gettingmoney.bai.main.base;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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


public abstract class BaseFragment extends Fragment {

    public static final int NET_ERROR = 1001;
    public static final int DATA_EMPTY = 1002;
    public static final int SERVER_EXCEPTION = 1003;

    protected AlertDialog dialog;
    protected MyOccupying occupying;
    protected MyToolBar toolBar;
    protected View viewLayout;
    private LinearLayout vLayout;

    public void recycleBaseDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
    }

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

    /**
     * 改变占位图
     *
     * @param type 占位图类型 NET_ERROR，DATA_EMPTY
     */
    public void changePlaceHolderLayoutByType(int type, int imageResourceId, String tips) {
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
                changeSimpleLayout(0);
                break;
            case SERVER_EXCEPTION:
                if (imageResourceId != -1)
                    occupying.img.setImageResource(imageResourceId);
                if (tips != null && tips.length() > 0)
                    occupying.text.setText(tips);
                changeSimpleLayout(0);
                break;
        }
    }

    public void changePlaceHolderLayoutNetError(int type) {
        changePlaceHolderLayoutByType(NET_ERROR, -1, "");
    }

    public  void requestData(){
        boolean networkConnected = ZhUtils.isNetworkConnected(getActivity());
        if (!networkConnected)
            ToastUtils.MyToast(getActivity(),R.string.net_error);
    }

    @Override
    public void onDestroyView() {
        recycleBaseDialog();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        recycleBaseDialog();
        super.onDestroy();
    }

    /**
     * @param inflater
     * @param layoutId
     * @param type     1代表无需占位图，0代表需要占位图
     * @return
     */
    public LinearLayout requestView(LayoutInflater inflater, int layoutId, int type) {
        occupying = new MyOccupying(getActivity(),
                R.mipmap.ic_launcher, "", "重新连接");
        occupying.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestInit();
            }
        });
        if (vLayout == null) {
            vLayout = new LinearLayout(getActivity().getBaseContext());
            vLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            vLayout.setOrientation(LinearLayout.VERTICAL);
        }
        initWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            local LayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
        vLayout.removeAllViews();
        viewLayout = LayoutInflater.from(getActivity().getBaseContext())
                .inflate(layoutId, vLayout, false);
        if (type == 0) {
            if (occupying != null) {
                vLayout.addView(occupying);
            }
        }
        if (toolBar != null) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ZhUtils.getStatusBarHeight(getActivity())));
            linearLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.statusBarColor));
            vLayout.addView(toolBar, 0);
//            这里是不添加toobar上面的高度
            vLayout.addView(linearLayout, 0);
        }
        vLayout.addView(viewLayout);
        if (ZhUtils.isNetworkConnected(getActivity()
                .getBaseContext())) {
            changeSimpleLayout(1);
        } else {
            changeSimpleLayout(0);
        }

        return vLayout;

    }

    public LinearLayout requestView(LayoutInflater inflater, int layoutId) {
        return requestView(inflater, layoutId, 0);
    }

    @TargetApi(19)
    public void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        //为状态栏着色
        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.statusBarColor);
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    public void serverError() {
        occupying.commit.setVisibility(View.VISIBLE);
        occupying.img.setImageResource(R.mipmap.ic_launcher);
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


    public void netError() {
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

    public abstract void requestInit();
}
