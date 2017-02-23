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
import money.com.gettingmoney.adapter.BalanceAdapter;
import money.com.gettingmoney.bean.Balance;

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
        balances = new ArrayList<>();
        Balance balance = new Balance();
        balance.setBalanceName("有财豆");
        balances.add(balance);
        balances.add(balance);
        balances.add(balance);

        adapter = new BalanceAdapter(this,balances);
        balance_listview.setAdapter(adapter);

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
}
