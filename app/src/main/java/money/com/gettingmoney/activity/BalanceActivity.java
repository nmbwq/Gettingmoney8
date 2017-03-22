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
import money.com.gettingmoney.adapter.BalanceAdapter;
import money.com.gettingmoney.bean.Balance;
import money.com.gettingmoney.util.JsonUitl;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.wallet.WalletUtil;
import money.com.gettingmoney.weiget.LoadingDialog;

public class BalanceActivity extends BaseActivity {

    /**
     * 收支明细
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private PullToRefreshListView balance_listview;
    private ArrayList<Balance> balances;
    private BalanceAdapter adapter;
    private int page=1;
    private int xiala=0;
    private LoadingDialog dialog;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(xiala==0){
                        adapter = new BalanceAdapter(BalanceActivity.this,balances);
                        balance_listview.setAdapter(adapter);
                    }else{
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initview();
    }

    private void initview() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        balance_listview = (PullToRefreshListView) this.findViewById(R.id.balance_listview);

        head_title.setText("收支明细");
        head_right.setVisibility(View.GONE);
        initListview();
        dialog = new LoadingDialog(this,"正在加载");
        dialog.show();
        balances = new ArrayList<>();
        new Thread(getbalane).run();
    }


    private void initListview() {
        // 上拉、下拉设定
        balance_listview.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        balance_listview.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        balance_listview.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        balance_listview
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


            balance_listview.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    Runnable getbalane = new Runnable() {
        @Override
        public void run() {
            WalletUtil util = new WalletUtil();
            util.findWalletDetail(ShareUtil.getInstance().getUserNumber(BalanceActivity.this), page, 10, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        ArrayList<Balance> balances1 = (ArrayList<Balance>) JsonUitl.stringToList(object.getJSONArray("result").toString(),Balance.class);

                        if(xiala==0){
                            balances = balances1;
                        }else{
                            for (int i=0;i<balances1.size();i++){
                                balances.add(balances1.get(i));
                            }
                        }
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
