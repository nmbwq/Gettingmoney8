package money.com.gettingmoney.bai.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.adapter.MoniAllFragmentAdapter;
import money.com.gettingmoney.bai.freagment.MoniCheFragment;
import money.com.gettingmoney.bai.freagment.MoniChiFragment;
import money.com.gettingmoney.bai.freagment.MoniDieFragment;
import money.com.gettingmoney.bai.freagment.MoniSelectFragment;
import money.com.gettingmoney.bai.freagment.MoniZhangFragment;
import money.com.gettingmoney.bai.main.base.BaseActivity;
import money.com.gettingmoney.bai.main.base.MyToolBar;
import money.com.gettingmoney.bai.main.utils.ZhUtils;
import money.com.gettingmoney.bai.main.view.ProgressLayout;

import static money.com.gettingmoney.R.id.tv_che;
import static money.com.gettingmoney.R.id.tv_chi;
import static money.com.gettingmoney.R.id.tv_die;
import static money.com.gettingmoney.R.id.tv_select;
import static money.com.gettingmoney.R.id.tv_zhang;


/**
 * Created by Administrator on 2016/6/8.
 * 用户账户表
 */
public class MoniAllActivity extends BaseActivity {


    @InjectView(R.id.pl_message)
    ProgressLayout plMessage;
    @InjectView(R.id.ll_themecolor_line)
    TextView llThemecolorLine;
    @InjectView(R.id.vp_account)
    ViewPager mViewPager;
    @InjectView(tv_zhang)
    TextView tvZhang;
    @InjectView(tv_die)
    TextView tvDie;
    @InjectView(tv_chi)
    TextView tvChi;
    @InjectView(tv_che)
    TextView tvChe;
    @InjectView(tv_select)
    TextView tvSelect;
    //用来确定跳那个界面的
    int  type=1;

    private List<Fragment> mFragments;
    private MoniAllFragmentAdapter mAdapter;
    // 记录当前选中的按钮id
      private int textViewId;
    /**
     * 头部黄色下划线
     */
    private LinearLayout.LayoutParams paramsFour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBar = new MyToolBar(this, R.mipmap.bai_back, "模拟交易", "");
        setContentView(requestView(R.layout.bai_moniall_layout));
        ButterKnife.inject(this);
        initEvents();
    }

    private void initEvents() {
        //滑动到那个freagment的下面 那个freagment的下面的条条变颜色
        paramsFour = new LinearLayout.LayoutParams(
                (int) (ZhUtils.getScreenWidth(MoniAllActivity.this) / 5) - 5, ZhUtils.DimenTrans.dip2px(
                MoniAllActivity.this, 2));
        paramsFour.setMargins(7, 0, 0, 0);
        llThemecolorLine.setLayoutParams(paramsFour);
        mFragments = new ArrayList<>();
//        顺序不能变,按照顺序来排序的
        mFragments.add(MoniZhangFragment.getInstance());
        mFragments.add(MoniDieFragment.getInstance());
        mFragments.add(MoniChiFragment.getInstance());
        mFragments.add(MoniCheFragment.getInstance());
        mFragments.add(MoniSelectFragment.getInstance());
        mAdapter = new MoniAllFragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        //默认为1加载两个页面    为2的话加载三个页面
        mViewPager.setOffscreenPageLimit(1);
        //跳转到什么界面
        mViewPager.setCurrentItem(type);

        //跳那个界面 那个界面上面的字边颜色
       switch (type){
           case 0:
               tvZhang.setTextColor(getResources().getColor(R.color._e93030));
               tvDie.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChe.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChi.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvSelect.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               break;
           case 1:
               tvDie.setTextColor(getResources().getColor(R.color._e93030));
               tvZhang.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChe.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChi.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvSelect.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               break;
           case 2:
               tvChi.setTextColor(getResources().getColor(R.color._e93030));
               tvZhang.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChe.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvDie.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvSelect.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               break;
           case 3:
               tvChe.setTextColor(getResources().getColor(R.color._e93030));
               tvZhang.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvDie.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChi.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvSelect.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               break;
           case 4:
               tvSelect.setTextColor(getResources().getColor(R.color._e93030));
               tvZhang.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChe.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvChi.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               tvDie.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
               break;

       }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                paramsFour
                        .setMargins(
                                (int) ((paramsFour.width + 7) * (position + positionOffset)),
                                0, 0, 0);
                llThemecolorLine.setLayoutParams(paramsFour);
            }


            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTextColor(tvZhang.getId());
                        break;
                    case 1:
                        setTextColor(tvDie.getId());
                        break;
                    case 2:
                        setTextColor(tvChi.getId());
                        break;
                    case 3:
                        setTextColor(tvChe.getId());
                        break;
                    case 4:
                        setTextColor(tvSelect.getId());
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setTextColor(int id) {
        refreshAllColor();
        //只和点击事件有关系 和滑动没有关系
        switch (id) {
            case tv_zhang:
                tvZhang.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));

                textViewId=tv_zhang;
                break;
            case tv_die:
                tvDie.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));

                textViewId=tv_die;

                break;
            case tv_chi:
                tvChi.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));

                textViewId=tv_chi;

                break;
            case tv_che:
                tvChe.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));

                textViewId=tv_che;

                break;
            case tv_select:
                tvSelect.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));

                textViewId=tv_select;
                break;
        }
    }

    @Override
    public void requestInit() {

    }

//    @Override
//    public void onDestroyView() {
////        super.onDestroyView();
//        ButterKnife.reset(this);
//    }

    @OnClick({R.id.tv_zhang, tv_die, tv_chi, tv_che, tv_select})
    public void onClick(View view) {
        switch (view.getId()) {
            //我的收藏
            case tv_zhang:
                if (textViewId != tv_zhang) {
                    refreshAllColor();
                    tvZhang.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));
                    mViewPager.setCurrentItem(0);
                    textViewId = tv_zhang;
                }
                break;
            //我当关注
            case tv_die:
                if (textViewId != tv_die) {
                    refreshAllColor();
                    tvDie.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));
                    mViewPager.setCurrentItem(1);
                    textViewId = tv_die;
                }
                break;
            case tv_chi:
                if (textViewId != tv_chi) {
                    refreshAllColor();
                    tvChi.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));
                    mViewPager.setCurrentItem(2);
                    textViewId = tv_chi;
                }
                break;
            case tv_che:
                if (textViewId != tv_che) {
                    refreshAllColor();
                    tvChe.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));
                    mViewPager.setCurrentItem(3);
                    textViewId = tv_che;
                }
                break;
            case tv_select:
                if (textViewId != tv_select) {
                    refreshAllColor();
                    tvSelect.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.themeColor));
                    mViewPager.setCurrentItem(4);
                    textViewId = tv_select;
                }
                break;

        }
    }

    private void refreshAllColor() {
        tvZhang.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
        tvDie.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
        tvChi.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
        tvChe.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
        tvSelect.setTextColor(ContextCompat.getColor(MoniAllActivity.this, R.color.black));
    }

}
