package money.com.gettingmoney.activity;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.adapter.NewsAdapter;
import money.com.gettingmoney.bean.Collection;
import money.com.gettingmoney.util.JsonUitl;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.collect.CollectUtil;
import money.com.gettingmoney.weiget.LoadingDialog;

public class CollectActivity extends BaseActivity {

    /**
     * 收藏
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private PullToRefreshListView collect_listview;
    private int page=1;
    private int xiala=0;
    private NewsAdapter adapter;
    private LoadingDialog dialog;
    private ArrayList<Collection> collectionList;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(xiala==0){
                        adapter = new NewsAdapter(CollectActivity.this,collectionList,1);
                        collect_listview.setAdapter(adapter);
                    }else{
//                        collect_listview.requestLayout();
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
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
        dialog = new LoadingDialog(this,"正在加载");
        dialog.show();
        collectionList = new ArrayList<>();
        new Thread(getnews).run();
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
                new Thread(getnews).run();
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

    Runnable getnews = new Runnable() {
        @Override
        public void run() {
            CollectUtil util = new CollectUtil();
            util.collectionList(dialog, page, 8, ShareUtil.getInstance().getUserNumber(CollectActivity.this), new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        collectionList = (ArrayList<Collection>) JsonUitl.stringToList(object.getJSONArray("collectionList").toString(),Collection.class);
                        Message message = new Message();
                        message.what = 100;
                        handler.sendMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}
