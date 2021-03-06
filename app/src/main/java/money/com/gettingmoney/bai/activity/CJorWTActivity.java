package money.com.gettingmoney.bai.activity;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

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
import money.com.gettingmoney.bai.model.CJorWTModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;


public class CJorWTActivity extends BaseActivity /*implements OnActionListener */ {
    public static final String DISTINGUISH = "DISTINGUISH";


    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.tv_qubie)
    TextView tvQubie;
    @InjectView(R.id.mLvShopMore)
    ListViewForScrollView mLvShopMore;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;


    //
//适配器
    private CommonAdapter<CJorWTModel> mAdapter;
    private List<CJorWTModel> mList = new ArrayList<>();

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
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra(DISTINGUISH, 0);
        if (type == 1) {
            toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟交易当日成交", "");
        } else {
            toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟交易当日委托", "");
        }

        setContentView(requestView(R.layout.bai_moni_che));
        ButterKnife.inject(this);
        initEvent();
        initListview();

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
        //进来时候的加载的转转 代替
//        showSwipeRefresh(mSwipeRefreshLayout);//显示加载
//        requeata();
        if (type == 1) {
            tvQubie.setText("方向/成交额");
        } else {
            tvQubie.setText("交易状态");
        }

        for (int i = 0; i < 5; i++) {
            CJorWTModel CJorWTModel = new CJorWTModel();
            mList.add(CJorWTModel);
        }
        //列表
        footer = LayoutInflater.from(this).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);

        mAdapter = new CommonAdapter<CJorWTModel>(CJorWTActivity.this, mList, R.layout.bai_moni_che_item) {
            @Override
            public void convert(ViewHolder baseViewHolder, CJorWTModel item) {
                TextView view = (TextView) baseViewHolder.getView(R.id.tv_time);
                if (type == 1) {
                    view.setText("125.0");
                } else {
                    view.setText("完成");
                }
            }


        };
        mLvShopMore.setAdapter(mAdapter);
//
//        //下拉刷新
//        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.themeColor));
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                isHasData = false;
//                isLoading = true;
//                page = 0;//还原第一页
//                requestData();
//            }
//        });
//        //上拉加载
//        mLvShopMore.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (firstVisibleItem + visibleItemCount == totalItemCount) {
//                    if (!isLoading && isHasData) {//不是在加载中和有数据才能上拉加载
//                        isLoading = true;
//                        loadData();
//                    }
//                }
//            }
//        });


    }


    /**
     * 加载中和加载结束界面切换
     *
     * @param isLoading 是否显示加载中的布局
     */
    private void setOnLoading(boolean isLoading) {
        if (isLoading) {
            mLlFooter.setVisibility(View.VISIBLE);
            mTxtFooter.setVisibility(View.GONE);
        } else {
            mLlFooter.setVisibility(View.GONE);
            mTxtFooter.setVisibility(View.VISIBLE);
        }
    }
//
//    @Override
//    public void requestData() {
//        super.requestData();
//        OkhttpParam okhttpParam = new OkhttpParam();
//        okhttpParam.putString("token", SimpleInfo.token + "");
//        okhttpParam.putString("page", page + "");
//        okhttpParam.putString("num", num + "");
//        OkhttpUtils.sendRequest(1001, 1, OkHttpServletUtils.REGISTER, okhttpParam, this);
//    }
//
//    private void loadData() {
//        OkhttpParam param = new OkhttpParam();
//        param.putString("token", SimpleInfo.token + "");
//        param.putString("page", page + "");
//        param.putString("num", num + "");
//        OkhttpUtils.sendRequest(1002, 1, OkHttpServletUtils.REGISTER, param, this);
//    }

    @Override
    public void requestInit() {
        requestData();
    }

    /**
     * 加载出错
     */
    private void loadError() {
        progressLayout.showErrorText("加载出错，点击重试");
        progressLayout.setOnerrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressLayout.showProgress();
                page = 0;
                requestData();
            }
        });
    }


//    @Override
//    public void onActionSuccess(int actionId, String ret) {
//        if (ret != null && ret.length() > 0) {
//            JSONObject object = null;
//            try {
//                object = JSONObject.parseObject(ret);
//            } catch (Exception x) {
//                ToastUtils.MyToast(this, "后端返回数据错误");
//                return;
//            }
//            int status = object.getIntValue("code");
//            switch (actionId) {
//                //请求数据
//                case 1001:
//                    if (status == 1) {
//                        JSONArray result = object.getJSONArray("result");
//
//                        if (result.size() > 0 && result.toString() != null) {
//                            mList = JSON.parseArray(result.toJSONString(), Bill.class);
//                            if (mList.size() == num) {
//                                isHasData = true;
//                                page++;
//                                setOnLoading(true);
//                            } else {
//                                setOnLoading(false);
//                            }
//                            mAdapter.setmDatas(mList);
//
//                        } else {
////                            没有数据添加占位图
//                            changePlaceHolderLayoutByType(BaseActivity.DATA_EMPTY, R.drawable.bai_bill_empty, "");
//                        }
//                        if (isFirst) {
//                            mLvShopMore.addFooterView(footer, null, false);//add footer view to listview
//                            isFirst = false;
//                        }
//                        isLoading = false;
//                        //首次请求的时候  如果页数不够一页的数据 不显示FooterView
//                        mLlFooter.setVisibility(View.GONE);
//                        mTxtFooter.setVisibility(View.GONE);
//                    } else {
//                        loadError();
//                    }
//                    break;
//                //下注返回结果
//                case 1002:
//                    if (status == 1) {
//                        isHasData = false;
//                        JSONArray result = object.getJSONArray("result");
//                        if (result.size() > 0 && result.toString() != null) {
//                            mList = JSON.parseArray(result.toJSONString(), Bill.class);
//                            if (mList.size() == num) {
//                                //还能加载
//                                page++;
//                                setOnLoading(true);
//                                isHasData = true;
//                            } else {
//                                setOnLoading(false);
//                            }
//                            mAdapter.addmDatas(mList);//放入数据
//                        } else {
//                            setOnLoading(false);
//                        }
//                        isLoading = false;
//                        dismissSwipeRefresh(mSwipeRefreshLayout);//关闭
//                        break;
//                    } else {
//                        ToastUtils.MyToast(this, object.getString("msg"));
//                    }
//                    break;
//            }
//
//        } else {
//            ToastUtils.MyToast(BillActivity.this, "暂无数据");
//        }
//        //加载出错的时候 才会有progresslayout
//        progressLayout.showContent();
//        dismissSwipeRefresh(mSwipeRefreshLayout);//关闭
//    }
//
//    @Override
//    public void onActionServerFailed(int actionId, int httpStatus) {
////        changePlaceHolderLayoutByType(SERVER_EXCEPTION, R.drawable.image2, "服务器出现问题了，请稍后再试！");
//        dismissSwipeRefresh(mSwipeRefreshLayout);//关闭
//        progressLayout.showContent();
//        isLoading = false;
//    }
//
//    @Override
//    public void onActionException(int actionId, String exception) {
////      changePlaceHolderLayoutByType(SERVER_EXCEPTION, R.drawable.image2, "服务器出现问题了，请稍后再试！");
//
//        dismissSwipeRefresh(mSwipeRefreshLayout);//关闭
//        progressLayout.showContent();
//        isLoading = false;
//    }

    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


}
