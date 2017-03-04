package money.com.gettingmoney.bai.freagment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.activity.CJorWTActivity;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.view.ProgressLayout;


/**
 * Created by Administrator on 2016/8/16.
 * 首页
 */
public class MoniSelectFragment extends BaseFragment /*implements OnActionListener*/ {


    int type;
    @InjectView(R.id.pl_message)
    ProgressLayout plMessage;
    private boolean isHasData = false;//是否有数据
    private boolean isLoading;//是否刷新中

    private LinearLayout mLlFooter;
    private TextView mTxtFooter;

    /**
     * 加载中的脚
     */
    private View footer;

    /**
     * 页数角标，从0开始。
     */
    private int page = 0;
    /**
     * 每页显示数量
     */
    private int num = 10;
    private boolean isFirst = true;//是否是第一次请求，控制footer只创建一次。
    private Map<TextView, CountDownTimer> leftTimeMap = new HashMap<>();
    private int pos;

    public static MoniSelectFragment getInstance() {
        MoniSelectFragment fragment = new MoniSelectFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requestView(inflater, R.layout.bai_moni_select);
        ButterKnife.inject(this, view);
        initWindow();
        initEvent();
        return view;
    }



    private void initEvent() {

    }

    @Override
    public void requestInit() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.rl_cehngjiao, R.id.rl_weituo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_cehngjiao:
                startActivity(new Intent(getActivity(), CJorWTActivity.class).putExtra(CJorWTActivity.DISTINGUISH,1));
                break;
            case R.id.rl_weituo:
                startActivity(new Intent(getActivity(), CJorWTActivity.class).putExtra(CJorWTActivity.DISTINGUISH,2));
                break;
        }
    }
}
