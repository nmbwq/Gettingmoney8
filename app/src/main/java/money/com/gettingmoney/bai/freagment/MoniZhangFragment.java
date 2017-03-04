package money.com.gettingmoney.bai.freagment;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniStockHomeModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;


/**
 * Created by Administrator on 2016/8/16.
 * 首页
 */
public class MoniZhangFragment extends BaseFragment /*implements OnActionListener*/ {


    int type;
    @InjectView(R.id.mLvShopMore)
    ListViewForScrollView mLvShopMore;
    //    @InjectView(R.id.srl_message)
//    SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.tv_number)
    TextView tvNumber;
    @InjectView(R.id.quancang)
    TextView quancang;
    @InjectView(R.id.twocang)
    TextView twocang;
    @InjectView(R.id.threecang)
    TextView threecang;
    @InjectView(R.id.ll_cang)
    LinearLayout llCang;
    @InjectView(R.id.pl_message)
    ProgressLayout plMessage;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;
    //改变背景颜色 不改变背景的形状

    //适配器
    private CommonAdapter<MoniStockHomeModel> mAdapter;
    private List<MoniStockHomeModel> mList;

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
    //买股票的数量
    int number = 0;
    // 0 全仓 1 1/2仓 2 1/3仓
    int cang = -1;

    public static MoniZhangFragment getInstance() {
        MoniZhangFragment fragment = new MoniZhangFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requestView(inflater, R.layout.bai_moni_zhang);
        ButterKnife.inject(this, view);
        initWindow();
        initEvent();
        initListview();
        return view;
    }

    private void initListview() {
        // 上拉、下拉设定
        pullToRefreshScrollVie.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        pullToRefreshScrollVie
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        page = 1;
//                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        page++;
//                        xiala = 1;
                        new DataTask().execute();
                    }
                });

    }

    private class DataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {

            pullToRefreshScrollVie.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    private void initEvent() {
        //scrollview嵌套listview时候让最上面控件获取焦点
        progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MoniStockHomeModel MoniStockHomeModel = new MoniStockHomeModel();
            mList.add(MoniStockHomeModel);
        }
        //列表
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);

        mAdapter = new CommonAdapter<MoniStockHomeModel>(getActivity(), mList, R.layout.bai_moni_stock_home_items) {
            @Override
            public void convert(ViewHolder baseViewHolder, final MoniStockHomeModel item) {
                final LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(R.id.ll_ishiden);
                final ImageView imageView = (ImageView) baseViewHolder.getView(R.id.im_image);
                final TextView textView = (TextView) baseViewHolder.getView(R.id.tv_xianshi);
                // 形状不变  改变背景颜色
                final GradientDrawable p = (GradientDrawable) textView.getBackground();

                if (item.isFlag()) {
                    linearLayout.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.GONE);
                    p.setColor(getResources().getColor(R.color._069043));
                } else {
                    linearLayout.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    p.setColor(getResources().getColor(R.color.text_red));
                }
                baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.isFlag()) {
                            item.setFlag(false);
                            linearLayout.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
//                            p.setColor(getResources().getColor(R.color.text_red));
                        } else {
                            item.setFlag(true);
                            linearLayout.setVisibility(View.VISIBLE);
                            imageView.setVisibility(View.GONE);
//                            p.setColor(getResources().getColor(R.color._069043));
                        }
                    }
                });

            }


        };
//        mLvShopMore.setAdapter(mAdapter);
        mLvShopMore.setAdapter(mAdapter);

    }

    @Override
    public void requestInit() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.im_jian, R.id.im_jia, R.id.quancang, R.id.twocang, R.id.threecang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im_jian:
                number = Integer.parseInt(tvNumber.getText().toString().trim());
                number = number - 1;
                tvNumber.setText(number + "");
                break;
            case R.id.im_jia:
                number = Integer.parseInt(tvNumber.getText().toString());
                number = number + 1;
                tvNumber.setText(number + "");
                break;
            case R.id.quancang:
                quancang.setBackgroundColor(getResources().getColor(R.color._e93030));
                quancang.setTextColor(getResources().getColor(R.color.white));
                twocang.setBackgroundColor(getResources().getColor(R.color.white));
                twocang.setTextColor(getResources().getColor(R.color._e93030));
                threecang.setBackgroundColor(getResources().getColor(R.color.white));
                threecang.setTextColor(getResources().getColor(R.color._e93030));
//                llCang.setBackground(getResources().getDrawable(R.drawable.bai_chongzhi_shape1));
                cang = 0;
                break;
            case R.id.twocang:
                twocang.setBackgroundColor(getResources().getColor(R.color._e93030));
                twocang.setTextColor(getResources().getColor(R.color.white));
                quancang.setBackgroundColor(getResources().getColor(R.color.white));
                quancang.setTextColor(getResources().getColor(R.color._e93030));
                threecang.setBackgroundColor(getResources().getColor(R.color.white));
                threecang.setTextColor(getResources().getColor(R.color._e93030));
//                llCang.setBackground(getResources().getDrawable(R.drawable.bai_chongzhi_shape1));
                cang = 1;
                break;
            case R.id.threecang:
                threecang.setBackgroundColor(getResources().getColor(R.color._e93030));
                threecang.setTextColor(getResources().getColor(R.color.white));
                twocang.setBackgroundColor(getResources().getColor(R.color.white));
                twocang.setTextColor(getResources().getColor(R.color._e93030));
                quancang.setBackgroundColor(getResources().getColor(R.color.white));
                quancang.setTextColor(getResources().getColor(R.color._e93030));
//                llCang.setBackground(getResources().getDrawable(R.drawable.bai_chongzhi_shape1));
                cang = 2;
                break;
        }
    }


}
