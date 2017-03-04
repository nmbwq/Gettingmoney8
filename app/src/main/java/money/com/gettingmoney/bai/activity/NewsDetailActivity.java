package money.com.gettingmoney.bai.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.utils.ToastUtils;
import money.com.gettingmoney.bai.main.utils.ZhUtils;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.NewsComment;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.news.NewsUtil;

/**
 * Created by Administrator on 2016/8/22.
 * 资讯详情
 */
public class NewsDetailActivity extends BaseActivity {

    public static final String KEY_ID = "noticeId";
    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.mWebView)
    WebView mWebView;
    @InjectView(R.id.pb)
    ProgressBar mProssBar;
    @InjectView(R.id.mTxtCommentNum)
    TextView mTxtCommentNum;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;
    @InjectView(R.id.pl_message)
    ProgressLayout progressLayout;
    @InjectView(R.id.is_conllect)
    ImageView isConllect;
    @InjectView(R.id.et_pinlun_content)
    EditText etPinlunContent;


    private BaseQuickAdapter<NewsComment> mAdapter;
    private List<NewsComment> mList;
    private int pageSize = 4;//每页的数量
    private int currentPage = 2;//从第二页开始
    private String url = "";
    private int noticeId;
    //0 请求的数据是 url和评论首页的数据 1 评论的分页请求 （从第二页开始）2 收藏接口 3 取消收藏接口 4 删除评论接口 5添加评论接口
    int type = 0;
    int xiala = 0;
    private ProgressDialog progressDialog;
    int CommentId;
    //0 没有收藏 1收藏
    int checkCollection;
    //第一次进来加载WEB界面  在其他的请求中不加载
    int symbol = 1001;
//   收藏id
    int collectionId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, R.mipmap.bai_back, "财经新闻", "");
        toolBar.changeBackgroundCoLor(R.color.white,R.color.black);
        setContentView(requestView(R.layout.bai_activity_news_detail,R.color.black));
        ButterKnife.inject(this);
        //获取前一个界面传来的资讯id
        Intent intent = getIntent();
        noticeId = intent.getIntExtra(KEY_ID, -1);
        Log.d("Debug", "传过来的新闻的id" + noticeId);
        initEvent();
    }

    private void initEvent() {
        progressLayout.showProgress();
        new DataTask().execute();
        initListview();
        initAdapter();
    }

    private void initAdapter() {

        mAdapter = new BaseQuickAdapter<NewsComment>(R.layout.bai_commment_item, null) {
            @Override
            protected void convert(final BaseViewHolder baseViewHolder, final NewsComment item) {
                TextView textView = (TextView) baseViewHolder.getView(R.id.tv_delete);
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) baseViewHolder.getView(R.id.mIvHeadImage);
                simpleDraweeView.setImageURI(item.getHeadImg() + "");
                baseViewHolder.setText(R.id.user_name, item.getNickName());
                baseViewHolder.setText(R.id.tv_time, item.getTime());
                baseViewHolder.setText(R.id.tv_content, item.getComment());
//                0是自己评论 1不是
                if (item.getCurrentComment() == 0) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                    Log.d("Debug", "自己评论的");
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Debug", "删除操作");
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type = 4;
                        CommentId = item.getId();
                        progressDialog = ZhUtils.ProgressDialog.showProgressDialog(NewsDetailActivity.this, "删除中，请稍后...", false);
                        progressDialog.show();
                        new DataTask().execute();
                        mAdapter.remove(baseViewHolder.getPosition());
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }
        };
        mAdapter.openLoadAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initWebView() {
        mWebView.requestFocusFromTouch();
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProssBar.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == mProssBar.getVisibility()) {
                        mProssBar.setVisibility(View.VISIBLE);
                    }
                    mProssBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        // 设置WebView属性，能够执行Javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                initAdapter();
//            }
//        },2000);


    }

    private void initListview() {
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
                        type = 0;
                        currentPage = 1;
                        xiala = 0;
                        symbol++;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        type = 1;
                        xiala = 1;
                        new DataTask().execute();
                        currentPage++;
                    }
                });

    }


    private class DataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
                switch (type) {
                    case 0:
                        new Thread(newsCommentDetail).run();
                        break;
                    case 1:
                        new Thread(commentList).run();
                        break;
                    case 2:
                        new Thread(addCollection).run();
                        break;
                    case 3:
                        new Thread(delCollection).run();
                        break;
                    case 4:
                        new Thread(delComment).run();
                        break;
                    case 5:
                        new Thread(addComment).run();
                        break;

                }

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

    /**
     * 获取url和评论首页面的数据
     */
    Runnable newsCommentDetail = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.getNewsData(pageSize, 1, "2ca7d86a476d80859ee32265752ed19f", noticeId, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        //当前用户是否收藏 0：未收藏 1：已收藏
                        checkCollection = object.getInt("checkCollection");
                        if (checkCollection==1){
                            collectionId=object.getInt("collectionId");
                        }
//                        新闻详情地址
                        url = object.getString("url");
                        if (symbol == 1001) {
                            initWebView();
                        }
                        JSONArray newsList = object.getJSONArray("commentList");
                        mList = JSON.parseArray(newsList.toString(), NewsComment.class);
                        Log.d("Debug", "返回的数据是" + object);
                            mAdapter.setNewData(mList);
                        Log.d("Debug", "返回的是否收藏" + checkCollection);
                        if (checkCollection == 0) {
//                            isConllect.setBackground(getResources().getDrawable(R.mipmap.bai_collectionn));
                            isConllect.setImageResource(R.mipmap.bai_collectionn);
                        } else {
                            isConllect.setImageResource(R.mipmap.bai_collectionn_no);
                        }
                        progressLayout.showContent();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressLayout.showContent();
                    }
                }
            });
        }
    };

    /**
     * 获取评论分页数据从第二页开始
     */
    Runnable commentList = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.commentList(noticeId,currentPage,pageSize,"2ca7d86a476d80859ee32265752ed19f",new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        JSONArray newsList = object.getJSONArray("commentList");
                        mList = JSON.parseArray(newsList.toString(), NewsComment.class);
                        mAdapter.addData(mList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };


    /**
     * 添加评论
     */
    Runnable addComment = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.addComment("2ca7d86a476d80859ee32265752ed19f", noticeId, etPinlunContent.getText().toString().trim(), new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        ToastUtils.MyToast(NewsDetailActivity.this, "添加成功");
                        collectionId=object.getInt("collectionId");
                        etPinlunContent.setText("");
//                        mAdapter.notifyDataSetChanged();
                        type = 0;
                        new DataTask().execute();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };
    /**
     * 删除自己的评论
     */
    Runnable delComment = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.delComment("2ca7d86a476d80859ee32265752ed19f", CommentId /*评论的id*/, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        ToastUtils.MyToast(NewsDetailActivity.this, "删除成功");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };
    /**
     * 添加收藏
     */
    Runnable addCollection = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.addCollection("2ca7d86a476d80859ee32265752ed19f", noticeId/*新闻的id*/, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        ToastUtils.MyToast(NewsDetailActivity.this, "添加收藏成功");
                        isConllect.setImageResource(R.mipmap.bai_collectionn_no);
                        checkCollection = 1;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };
    /**
     * 删除收藏
     */
    Runnable delCollection = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.delCollection("2ca7d86a476d80859ee32265752ed19f", collectionId/*收藏的id*/, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        ToastUtils.MyToast(NewsDetailActivity.this, "取消收藏成功");
                        isConllect.setImageResource(R.mipmap.bai_collectionn);
                        checkCollection = 0;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };


    @OnClick({R.id.is_conllect, R.id.send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.is_conllect:
                if (checkCollection == 0) {
                    type = 2;
                    progressDialog = ZhUtils.ProgressDialog.showProgressDialog(this, "收藏中，请稍后...", false);
                    progressDialog.show();
                    symbol++;
                    new DataTask().execute();
                } else {
                    type = 3;
                    progressDialog = ZhUtils.ProgressDialog.showProgressDialog(this, "取消收藏中，请稍后...", false);
                    progressDialog.show();
                    new DataTask().execute();
                }
                break;
            case R.id.send:
                progressDialog = ZhUtils.ProgressDialog.showProgressDialog(this, "添加中，请稍后...", false);
                progressDialog.show();
                new DataTask().execute();
                type = 5;
                symbol++;
                break;
        }
    }


    @Override
    public void requestInit() {
        requestData();
    }


    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

}
