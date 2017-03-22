package money.com.gettingmoney.bai.freagment;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniCheModel;


/**
 * Created by Administrator on 2016/8/16.
 * 首页
 */
public class MoniCheFragment extends BaseFragment /*implements OnActionListener*/ {


    @InjectView(R.id.mLvShopMore)
    ListView mLvShopMore;
    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.tv_qubie)
    TextView tvQubie;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;


    //适配器
    private CommonAdapter<MoniCheModel> mAdapter;
    private List<MoniCheModel> mList;


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

    int type;

    public static MoniCheFragment getInstance() {
        MoniCheFragment fragment = new MoniCheFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = requestView(inflater, R.layout.bai_moni_che);
        ButterKnife.inject(this, view);
        initWindow();
        initEvent();
        initListview();
        return view;
    }

    private void initListview() {
        progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();
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
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MoniCheModel MoniCheModel = new MoniCheModel();
            mList.add(MoniCheModel);
        }
        //列表
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);

        mAdapter = new CommonAdapter<MoniCheModel>(getActivity(), mList, R.layout.bai_moni_che_item) {
            @Override
            public void convert(ViewHolder helper, MoniCheModel item) {

            }
        };
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
}
