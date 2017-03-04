package money.com.gettingmoney.bai.activity;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniDetailModel;


public class MoniDetailActivity extends BaseActivity /*implements OnActionListener */ {

    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.mLvShopMore)
    PullToRefreshListView mLvShopMore;


    //
//适配器
    private CommonAdapter<MoniDetailModel> mAdapter;
    private List<MoniDetailModel> mList = new ArrayList<>();

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, "", "鑫哥投资 (600069)", "");
        setContentView(requestView(R.layout.bai_news_list));
        ButterKnife.inject(this);
        initEvent();
        initListview();
    }

    private void initListview() {
        progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();
        // 上拉、下拉设定
        mLvShopMore.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        mLvShopMore.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        mLvShopMore.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        mLvShopMore
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                        page = 1;
//                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                        page++;
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

            mLvShopMore.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    @Override
    public void requestInit() {

    }

    private void initEvent() {
        //进来时候的加载的转转 代替
//        showSwipeRefresh(mSwipeRefreshLayout);//显示加载
//        requestData();
        for (int i = 0; i < 5; i++) {
            MoniDetailModel MoniDetailModel = new MoniDetailModel();
            mList.add(MoniDetailModel);
        }
        //列表
        footer = LayoutInflater.from(this).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);

        mAdapter = new CommonAdapter<MoniDetailModel>(MoniDetailActivity.this, mList, R.layout.bai_moni_mingxi_items) {
            @Override
            public void convert(ViewHolder baseViewHolder, MoniDetailModel item) {

            }


        };
        mLvShopMore.setAdapter(mAdapter);
    }

    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


}
