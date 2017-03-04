package money.com.gettingmoney.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import money.com.gettingmoney.R;
import money.com.gettingmoney.activity.AboutActivity;
import money.com.gettingmoney.activity.CollectActivity;
import money.com.gettingmoney.activity.HelpcenterActivity;
import money.com.gettingmoney.activity.MywalletActivity;
import money.com.gettingmoney.activity.SetActivity;
import money.com.gettingmoney.app.MoneyApplication;
import money.com.gettingmoney.bai.main.utils.ZhUtils;
import money.com.gettingmoney.util.ActivityJump;

public class MyFragment extends Fragment implements View.OnClickListener {


    /**
     * 我的界面
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private RelativeLayout mywallet_btn,myshare_btn,mycollect_btn,myabout_btn,myhelpcenter_btn,myset_btn;
    private View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my,null);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        //说白了 （就是手动加载一个状态栏的高度的布局）
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ZhUtils.getStatusBarHeight(getActivity())));
        linearLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.themeColor));
        layout.addView(view);
        layout.addView(linearLayout, 0);
        context = MoneyApplication.getContext();
        initView();
        return layout;
    }

    private void initView() {
        mywallet_btn = (RelativeLayout) view.findViewById(R.id.mywallet_btn);
        myshare_btn = (RelativeLayout) view.findViewById(R.id.myshare_btn);
        mycollect_btn = (RelativeLayout) view.findViewById(R.id.mycollect_btn);
        myabout_btn = (RelativeLayout) view.findViewById(R.id.myabout_btn);
        myhelpcenter_btn = (RelativeLayout) view.findViewById(R.id.myhelpcenter_btn);
        myset_btn = (RelativeLayout) view.findViewById(R.id.myset_btn);

        mywallet_btn.setOnClickListener(this);
        myshare_btn.setOnClickListener(this);
        mycollect_btn.setOnClickListener(this);
        myabout_btn.setOnClickListener(this);
        myhelpcenter_btn.setOnClickListener(this);
        myset_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mywallet_btn:
                ActivityJump.jumpActivity(getActivity(), MywalletActivity.class);
                break;
            case R.id.myshare_btn:

                break;
            case R.id.mycollect_btn:
                ActivityJump.jumpActivity(getActivity(), CollectActivity.class);
                break;
            case R.id.myabout_btn:
                ActivityJump.jumpActivity(getActivity(), AboutActivity.class);
                break;
            case R.id.myhelpcenter_btn:
                ActivityJump.jumpActivity(getActivity(), HelpcenterActivity.class);
                break;
            case R.id.myset_btn:
                ActivityJump.jumpActivity(getActivity(), SetActivity.class);
                break;
        }
    }
}
