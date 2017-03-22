package money.com.gettingmoney.fragment;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.adapter.IndexAdapter;
import money.com.gettingmoney.bai.main.base.BaseFragment;

public class IndexFragment extends BaseFragment implements View.OnClickListener {

    /**
     * 指数
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    private View view;
    private PullToRefreshListView indexlistview;
    private int page=1;
    private int xiala=0;
    private ArrayList<String> strings;
    private IndexAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index,null);
        indexlistview = (PullToRefreshListView) view.findViewById(R.id.indexlistview);
        initListview();
        strings = new ArrayList<>();
        strings.add("国内指数");
        strings.add("股指期货");
        strings.add("富实A50指数期货");
        strings.add("其它指数");
        adapter = new IndexAdapter(getActivity(),strings);
        indexlistview.setAdapter(adapter);
        initWindow();
        return view;
    }


    private void initListview() {
        // 上拉、下拉设定
        indexlistview.setMode(PullToRefreshBase.Mode.BOTH);
        // 下拉刷新 业务代码
        indexlistview.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        indexlistview.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        indexlistview
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void requestInit() {

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


            indexlistview.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

}
