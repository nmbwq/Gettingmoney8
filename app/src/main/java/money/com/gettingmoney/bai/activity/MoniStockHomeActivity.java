package money.com.gettingmoney.bai.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.Butils.BDialog;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.MoniStockHomeModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;


public class MoniStockHomeActivity extends BaseActivity /*implements OnActionListener */ {

    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.ll_zhang)
    LinearLayout llZhang;
    @InjectView(R.id.ll_die)
    LinearLayout llDie;
    @InjectView(R.id.ll_chi)
    LinearLayout llChi;
    @InjectView(R.id.ll_che)
    LinearLayout llChe;
    @InjectView(R.id.ll_select)
    LinearLayout llSelect;
    @InjectView(R.id.mLvShopMore)
    ListViewForScrollView mLvShopMore;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;


    //
//适配器
    private CommonAdapter<MoniStockHomeModel> mAdapter;
    private List<MoniStockHomeModel> mList = new ArrayList<>();

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
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟股票资产", "");
        setContentView(requestView(R.layout.bai_moni_stock_home));
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
//        requestData();
        for (int i = 0; i < 5; i++) {
            MoniStockHomeModel MoniStockHomeModel = new MoniStockHomeModel();
            mList.add(MoniStockHomeModel);
        }
        //列表
        footer = LayoutInflater.from(this).inflate(R.layout.zhang_footer_listivew, null);
        mLlFooter = (LinearLayout) footer.findViewById(R.id.mLlFooter);
        mTxtFooter = (TextView) footer.findViewById(R.id.mTxtFooter);

        mAdapter = new CommonAdapter<MoniStockHomeModel>(MoniStockHomeActivity.this, mList, R.layout.bai_moni_stock_home_items) {
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


    @OnClick({R.id.ll_zhang, R.id.ll_die, R.id.ll_chi, R.id.ll_che, R.id.ll_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_zhang:
                startActivity(new Intent(MoniStockHomeActivity.this, MoniAllActivity.class));
                break;
            case R.id.ll_die:
                startActivity(new Intent(MoniStockHomeActivity.this, MoniDetailActivity.class));
                break;
            case R.id.ll_chi:
                alertDialog = BDialog.showDialog(MoniStockHomeActivity.this, R.layout.bai_dialog_pingcang, "", "", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = (EditText) alertDialog.findViewById(R.id.tv_number);
                        Log.d("Debug", "dialog上面填写的数量为" + editText.getText().toString());
                        alertDialog.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                break;
            case R.id.ll_che:
                break;
            case R.id.ll_select:
                break;
        }
    }


}
