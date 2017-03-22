package money.com.gettingmoney.bai.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import money.com.gettingmoney.bai.model.homeNews;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.news.NewsUtil;


public class BusinessNewsActivity extends BaseActivity /*implements OnActionListener */ {

    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.mLvShopMore)
    PullToRefreshListView mLvShopMore;
    @InjectView(R.id.tv_no_data)
    TextView tvNoData;


    //
//适配器
    private CommonAdapter<homeNews> mAdapter;
    private List<homeNews> mList = new ArrayList<>();

    /**
     * 页数角标，从1开始。
     */
    private int page = 1;
    /**
     * 每页显示数量
     */
    private int num = 5;
// 0是下拉刷新的  1上拉加载
    private int xiala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, "", "新闻列表", "");
        setContentView(requestView(R.layout.bai_news_list));
//        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        ButterKnife.inject(this);
        initEvent();
        initListview();
    }

    @Override
    public void requestInit() {

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
                        page = 1;
                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                        page++;
                        xiala = 1;
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
                Log.d("Debug", "开始请求数据");
                new Thread(newsList).run();
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

    Runnable newsList = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.newslist(1, page, num, "2ca7d86a476d80859ee32265752ed19f", new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        Log.d("Debug", "请求数据成功");
                        JSONObject object = new JSONObject(result);
                        JSONArray newsList = object.getJSONArray("newsList");
                        mList = JSON.parseArray(newsList.toString(), homeNews.class);
                        Log.d("Debug", "每次返回的数据的长度是" + mList.size());
                        if (mList.size() != num) {
                            tvNoData.setVisibility(View.VISIBLE);
                        } else {
                            tvNoData.setVisibility(View.GONE);
                        }
                        //0 下拉刷新 1下拉加载
                        if (xiala == 0) {
                            mAdapter.setmDatas(mList);
                            Log.d("Debug", "下拉刷新  只有首页的数据");
                        } else {
                            mAdapter.addmDatas(mList);
                            Log.d("Debug", "上拉加载  多页的数据");
                        }
                        Log.d("Debug", "返回的json数据" + object);
                        progressLayout.showContent();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressLayout.showContent();
                    }
                }
            });
        }
    };

    private void initEvent() {
        //进来时候的加载的转转 代替
        progressLayout.showProgress();
        new DataTask().execute();

        mAdapter = new CommonAdapter<homeNews>(BusinessNewsActivity.this, null, R.layout.bai_homenews_list_items) {
            @Override
            public void convert(ViewHolder baseViewHolder, final homeNews item) {

                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) baseViewHolder.getView(R.id.image);
                simpleDraweeView.setImageURI(item.getPic() + "");
                baseViewHolder.setText(R.id.title, item.getTitle());
                baseViewHolder.setText(R.id.time, "12:30");
                baseViewHolder.setText(R.id.info, item.getContent());
                baseViewHolder.setText(R.id.tv_comment_number, "10 "+"条数据");
                baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(BusinessNewsActivity.this, NewsDetailActivity.class).putExtra(NewsDetailActivity.KEY_ID,item.getId()));

                    }
                });
            }


        };
        mLvShopMore.setAdapter(mAdapter);

    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


}
