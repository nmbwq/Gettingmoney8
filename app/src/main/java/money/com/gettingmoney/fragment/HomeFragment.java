package money.com.gettingmoney.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.activity.BusinessNewsActivity;
import money.com.gettingmoney.bai.main.adapter.CommonAdapter;
import money.com.gettingmoney.bai.main.adapter.ViewHolder;
import money.com.gettingmoney.bai.main.base.BaseFragment;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.utils.ToastUtils;
import money.com.gettingmoney.bai.main.view.ProgressLayout;
import money.com.gettingmoney.bai.model.homeNews;
import money.com.gettingmoney.bai.model.zuJianModel;
import money.com.gettingmoney.bai.view.ListViewForScrollView;

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.tv_zujian)
    TextView tvZujian;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.mZuJianList)
    GridView mZuJianList;
    @InjectView(R.id.mLvShopMore)
    ListViewForScrollView mLvShopMore;
    @InjectView(R.id.mProgress)
    ProgressLayout progressLayout;
    @InjectView(R.id.pullToRefreshScrollVie)
    PullToRefreshScrollView pullToRefreshScrollVie;


    //适配器
    private CommonAdapter<homeNews> mAdapter;
    private List<homeNews> mList;
    private CommonAdapter<zuJianModel> ZmAdapter;
    private List<zuJianModel> ZmList;

    public HomeFragment() {
    }

    /**
     * 有财路主页面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(getActivity());
        toolBar = new MyToolBar(getActivity(), "", "有财路", R.mipmap.bai_sousuo);
//        toolBar.changeBackgroundCoLor(R.color.white,R.color.black);
        View view = requestView(inflater, R.layout.fragment_home);
        ButterKnife.inject(this, view);
        initEvent();
        initListview();
        return view;
    }


    private void initListview() {
        progressLayout.setFocusable(true);
        progressLayout.setFocusableInTouchMode(true);
        progressLayout.requestFocus();
        // 上拉、下拉设定
        pullToRefreshScrollVie.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        // 下拉刷新 业务代码
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setTextTypeface(Typeface.SANS_SERIF);
        pullToRefreshScrollVie.getLoadingLayoutProxy()
                .setReleaseLabel("放开我");
        pullToRefreshScrollVie
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                        page = 1;
//                        xiala = 0;
                        new DataTask().execute();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                        page++;
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
//        requestdate();
        ZmList = new ArrayList<>();
        mList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            homeNews homenews = new homeNews();
            mList.add(homenews);
        }
        mAdapter = new CommonAdapter<homeNews>(getActivity(), mList, R.layout.bai_homenews_items) {
            @Override
            public void convert(ViewHolder baseViewHolder, homeNews item) {
//                baseViewHolder.setText(R.id.image,  "");
            }
        };
        mLvShopMore.setAdapter(mAdapter);
        mLvShopMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), BusinessNewsActivity.class));
            }
        });

        requestdate2();
//        http://b.hiphotos.baidu.com/image/pic/item/d009b3de9c82d15825ffd75c840a19d8bd3e42da.jpg

        zuJianModel zuJianModel = new zuJianModel("添加组件", " http://b.hiphotos.baidu.com/image/pic/item/d009b3de9c82d15825ffd75c840a19d8bd3e42da.jpg");
        ZmList.add(zuJianModel);


        ZmAdapter = new CommonAdapter<zuJianModel>(getActivity(), ZmList, R.layout.bai_zujian_items) {

            @Override
            public void convert(ViewHolder baseViewHolder, zuJianModel item) {
                SimpleDraweeView view = (SimpleDraweeView) baseViewHolder.getView(R.id.im_zujian_photo);
                view.setImageURI(item.getPhotoUrl());
                baseViewHolder.setText(R.id.im_zujian_name, item.getName());
//                baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                });
            }
        };
        mZuJianList.setAdapter(ZmAdapter);


    }

    private void requestdate2() {
        for (int i = 0; i < 2; i++) {
            zuJianModel zuJianModel = new zuJianModel("添加组件", "http://b.hiphotos.baidu.com/image/pic/item/d009b3de9c82d15825ffd75c840a19d8bd3e42da.jpg");
            ZmList.add(zuJianModel);
        }
//        //如果组件有四个将加好移除
//        if (ZmList.size()>4){
//            ZmList.remove(ZmList.size()-1);
//        }

    }


    @Override
    public void requestInit() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.tv_zujian)
    public void onClick() {
        ToastUtils.MyToast(getActivity(), "点击组件的操作");
    }
}
