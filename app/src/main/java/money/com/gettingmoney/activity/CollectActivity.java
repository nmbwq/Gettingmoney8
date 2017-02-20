package money.com.gettingmoney.activity;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.adapter.NewsAdapter;
import money.com.gettingmoney.bean.News;

public class CollectActivity extends BaseActivity {

    /**
     * 收藏
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private PullToRefreshListView collect_listview;
    private int page=1;
    private int xiala=0;
    private ArrayList<News> newses;
    private NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("我的收藏");
        head_right.setVisibility(View.GONE);
        initView();
    }

    private void initView() {
        collect_listview = (PullToRefreshListView) this.findViewById(R.id.collect_listview);
        initListview();
        newses = new ArrayList<>();
        News news = new News();
        news.setNewsName("财经新闻");
        newses.add(news);
        newses.add(news);
        newses.add(news);
        adapter = new NewsAdapter(this,newses,1);
        collect_listview.setAdapter(adapter);
    }

    private void initListview() {
        // 上拉、下拉设定
        collect_listview.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        collect_listview.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        collect_listview.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        collect_listview
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
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {


            collect_listview.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
}
