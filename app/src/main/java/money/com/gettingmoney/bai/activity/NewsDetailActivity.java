package money.com.gettingmoney.bai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.model.NewsComment;

/**
 * Created by Administrator on 2016/8/22.
 * 资讯详情
 */
public class NewsDetailActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    public static final String NOTICEID = "noticeId";
    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.mWebView)
    WebView mWebView;
    @InjectView(R.id.pb)
    ProgressBar mProssBar;
    @InjectView(R.id.mTxtCommentNum)
    TextView mTxtCommentNum;


    private BaseQuickAdapter<NewsComment> mAdapter;
    private List<NewsComment> mList;
    private int pageSize = 10;//每页的数量
    private int currentPage = 1;//默认第一页
    private int commentsId = 0;//记录最后的评论id，防止自己评论后出现重复的评论

//    private String url = OkhttpBase.BASE_URL + OkHttpServletUtils.WEBNEWS;

    private String url ="http://lolsjlj.cjdzjj.com/lol_war/" + "notice/findNoticeById";
    private boolean isHasData = true;//是否还能加载数据
    private int noticeId;
    private String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, R.mipmap.bai_back, "财经新闻", "");
        setContentView(requestView(R.layout.bai_activity_news_detail));
        ButterKnife.inject(this);
        initEvent();
    }

    private void initEvent() {
        //获取前一个界面传来的资讯id
        Intent intent = getIntent();
        noticeId = intent.getIntExtra(NOTICEID, -1);
        url += ("?noticeId=" + 6);
        requestData();
        initWebView();
        initAdapter();
    }

    private void initAdapter() {
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NewsComment newsComment = new NewsComment();
            mList.add(newsComment);
        }

        mAdapter = new BaseQuickAdapter<NewsComment>(R.layout.bai_commment_item, mList) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, NewsComment comment) {

            }
        };
        mAdapter.openLoadAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadMore(pageSize, true);
    }

//    @Override
//    public void requestData() {
//        super.requestData();
//        OkhttpParam param = new OkhttpParam();
//        param.putString("currentPage", currentPage + "");
//        param.putString("pageSize", pageSize + "");
//        param.putString(NOTICEID, noticeId + "");
//        OkhttpUtils.sendRequest(1001, 1, OkHttpServletUtils.NEWSCOMMENT, param, this);
//    }

//    /**
//     * 分页请求
//     */
//    private void requestDataByPage() {
//        OkhttpParam param = new OkhttpParam();
//        param.putString(NOTICEID, noticeId + "");
//        param.putString("currentPage", currentPage + "");
//        param.putString("pageSize", pageSize + "");
//        if (commentsId != 0) {
//            param.putString("commentsId", commentsId + "");
//        }
//        OkhttpUtils.sendRequest(1003, 1, OkHttpServletUtils.NEWSCOMMENT, param, this);
//    }

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
    }

    @Override
    public void requestInit() {
        requestData();
    }

//    @Override
//    public void onLoadMoreRequested() {
//        mRecyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                if (!isHasData) {
//                    mAdapter.notifyDataChangedAfterLoadMore(false);
//                    View view = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
//                    mAdapter.addFooterView(view);
//                    Log.d("Debug", "上拉加载后发现没数据了");
//                } else {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mAdapter.notifyDataChangedAfterLoadMore(true);
////                            requestDataByPage();
//                            Log.d("Debug", "上拉加载后发现新数据");
//                        }
//                    }, 2000);
//                }
//            }
//        });
//    }

//    @Override
//    public void onActionSuccess(int actionId, String ret) {
//        if (ret != null && ret.length() > 0) {
//            JSONObject object = JSONObject.parseObject(ret);
//            int status = object.getIntValue("status");
//            if (status == 1) {
//                switch (actionId) {
//                    //请求数据
//                    case 1001:
//                        JSONArray result = object.getJSONArray("result");
//                        mList = JSON.parseArray(result.toJSONString(), NewsComment.class);
//                        if (mList.size() == pageSize) {
//                            isHasData = true;//可能还有数据
//                            Log.d("Debug", "还有数据");
//                        } else {
//                            isHasData = false;//没有数据了
//                            Log.d("Debug", "没有数据");
//                        }
//                        if (mList != null && mList.size() > 0) {
//                            commentsId = mList.get(mList.size() - 1).getCommentsId();
//                            for (int i = 0; i < mList.size(); i++) {
//                                NewsComment newsComment = mList.get(i);
//                                String content = newsComment.getContent();
//                                newsComment.setContent(ZhUtils.unicode2String(content));
//                            }
//                            mAdapter.setNewData(mList);
//                            changeSimpleLayout(1);
//
//                        } else {
//                            // 没有评论占位图
//                            TextView textView = new TextView(this);
//                            textView.setText("还没有评论~");
//                            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
//                            textView.setLayoutParams(layoutParams);
//                            textView.setPadding(ZhUtils.DimenTrans.dip2px(this, 10), 0, 0, 0);
//                            mAdapter.addFooterView(textView);
//                        }
//                        mTxtCommentNum.setText("评论(" + object.getIntValue("count") + ")");
//                        break;
//                    //评论
//                    case 1002:
//                        ToastUtils.MyToast(this, "评论成功");
//                        List data = mAdapter.getData();
//                        data.add(0, new NewsComment(SimpleUserInfo.userId, content, "刚刚", SimpleUserInfo.headImg, SimpleUserInfo.nickName));
//                        mAdapter.setNewData(data);
//                        mEtCommentContent.setText("");//评论成功后清空评论框
//                        break;
//                    //分页数据
//                    case 1003:
//                        JSONArray resultByPage = object.getJSONArray("result");
//                        mList = JSON.parseArray(resultByPage.toJSONString(), NewsComment.class);
//                        if (mList.size() == pageSize) {
//                            isHasData = true;//可能还有数据
//                            Log.d("Debug", "还有数据");
//                        } else {
//                            isHasData = false;//没有数据了
//                            Log.d("Debug", "没有数据");
//                        }
//                        if (mList != null && mList.size() > 0) {
//                            commentsId = mList.get(mList.size() - 1).getCommentsId();
//                            for (int i = 0; i < mList.size(); i++) {
//                                NewsComment newsComment = mList.get(i);
//                                String content = newsComment.getContent();
//                                newsComment.setContent(ZhUtils.unicode2String(content));
//                            }
//                            mAdapter.addData(mList);
//                        }
//                        break;
//                }
//            } else {
//                ToastUtils.MyToast(this, object.getString("msg"));
//            }
//        } else {
//            ToastUtils.MyToast(this, "暂无数据");
//        }
//    }

//    @Override
//    public void onActionServerFailed(int actionId, int httpStatus) {
//        switch (actionId) {
//            //请求数据
//            case 1001:
//                changePlaceHolderLayoutByType(SERVER_EXCEPTION, R.drawable.server_exception, "服务器异常！");
//                break;
//            //评论
//            case 1002:
//                ToastUtils.MyToast(this, "评论失败，请稍后再试");
//                break;
//        }
//    }
//
//    @Override
//    public void onActionException(int actionId, String exception) {
//        switch (actionId) {
//            //请求数据
//            case 1001:
//                changePlaceHolderLayoutByType(SERVER_EXCEPTION, R.drawable.server_exception, "服务器异常！");
//                break;
//            //评论
//            case 1002:
//                ToastUtils.MyToast(this, "评论失败，请稍后再试");
//                break;
//        }
//    }


//    private void sendData(String data) {
//        OkhttpParam param = new OkhttpParam();
//        param.putString(NOTICEID, noticeId + "");
//        param.putString("content", data + "");
//        param.putString("userId", SimpleUserInfo.userId + "");
//        OkhttpUtils.sendRequest(1002, 1, OkHttpServletUtils.ADDCOMMENT, param, this);
//    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
