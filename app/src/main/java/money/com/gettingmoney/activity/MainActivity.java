package money.com.gettingmoney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.fragment.EntertainmentFragment;
import money.com.gettingmoney.fragment.HomeFragment;
import money.com.gettingmoney.fragment.MarketFragment;
import money.com.gettingmoney.fragment.MyFragment;
import money.com.gettingmoney.fragment.TransacationFragment;
import money.com.gettingmoney.util.ActivityJump;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.ShareUtil;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private HomeFragment homeFragment;//首页
    private MarketFragment marketFragment;//行情
    private EntertainmentFragment entertainmentFragment;//娱乐城
    private TransacationFragment transacationFragment;//交易
    private MyFragment myFragment;//我的界面

    private LinearLayout home_btn,market_btn,transacation_btn,entertainment_btn,my_btn;
    private TextView home_text,market_text,transacation_text,entertainment_text,my_text;
    private ImageView home_img,market_img,transacation_img,entertainment_img,my_img;
    private ArrayList<TextView> carhometext;
    private int position = 0;
    private FragmentManager fm;// FragmentManager
    private FragmentTransaction trans;// FragmentTransaction
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAppApiConfig.MAIN_ACTIVITY = this;
        initView();
    }


    private void initView() {
        home_btn = (LinearLayout) this.findViewById(R.id.home_btn);
        market_btn = (LinearLayout) this.findViewById(R.id.market_btn);
        transacation_btn = (LinearLayout) this.findViewById(R.id.transacation_btn);
        entertainment_btn = (LinearLayout) this.findViewById(R.id.entertainment_btn);
        my_btn = (LinearLayout) this.findViewById(R.id.my_btn);

        home_text = (TextView) this.findViewById(R.id.home_text);
        market_text = (TextView) this.findViewById(R.id.market_text);
        transacation_text = (TextView) this.findViewById(R.id.transacation_text);
        entertainment_text = (TextView) this.findViewById(R.id.entertainment_text);
        my_text = (TextView) this.findViewById(R.id.my_text);

        home_img = (ImageView) this.findViewById(R.id.home_img);
        market_img = (ImageView) this.findViewById(R.id.market_img);
        transacation_img = (ImageView) this.findViewById(R.id.transacation_img);
        entertainment_img = (ImageView) this.findViewById(R.id.entertainment_img);
        my_img = (ImageView) this.findViewById(R.id.my_img);

        carhometext = new ArrayList<>();
        carhometext.add(home_text);
        carhometext.add(market_text);
        carhometext.add(transacation_text);
        carhometext.add(entertainment_text);
        carhometext.add(my_text);

        homeFragment = new HomeFragment();
        marketFragment = new MarketFragment();
        entertainmentFragment = new EntertainmentFragment();
        transacationFragment = new TransacationFragment();
        myFragment = new MyFragment();

        fm = getSupportFragmentManager();
        trans = fm.beginTransaction();
        trans.replace(R.id.main_fragment, homeFragment);
        trans.commit();
        home_text.setTextColor(getResources().getColor(R.color.text_red));
        home_img.setImageResource(R.mipmap.youcailured);
        home_btn.setOnClickListener(this);
        market_btn.setOnClickListener(this);
        transacation_btn.setOnClickListener(this);
        entertainment_btn.setOnClickListener(this);
        my_btn.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        trans = fm.beginTransaction();
        switch (v.getId()){
            case R.id.home_btn:

                if(position==0){
                    return;
                }
                trans.replace(R.id.main_fragment, homeFragment);
                trans.commit();
                home_img.setImageResource(R.mipmap.youcailured);
                market_img.setImageResource(R.mipmap.quotes);
                transacation_img.setImageResource(R.mipmap.transaction);
                entertainment_img.setImageResource(R.mipmap.entertainment);
                my_img.setImageResource(R.mipmap.mine);
                changtextcolor(0);
                break;

            case R.id.market_btn:
                if(position==1){
                    return;
                }
                trans.replace(R.id.main_fragment, marketFragment);
                trans.commit();
                home_img.setImageResource(R.mipmap.youcailu);
                market_img.setImageResource(R.mipmap.quotesred);
                transacation_img.setImageResource(R.mipmap.transaction);
                entertainment_img.setImageResource(R.mipmap.entertainment);
                my_img.setImageResource(R.mipmap.mine);
                changtextcolor(1);
                break;


            case R.id.transacation_btn:
                if(position==2){
                    return;
                }
                trans.replace(R.id.main_fragment, transacationFragment);
                trans.commit();
                home_img.setImageResource(R.mipmap.youcailu);
                market_img.setImageResource(R.mipmap.quotes);
                transacation_img.setImageResource(R.mipmap.transactionred);
                entertainment_img.setImageResource(R.mipmap.entertainment);
                my_img.setImageResource(R.mipmap.mine);
                changtextcolor(2);
                break;

            case R.id.entertainment_btn:
                if(position==3){
                    return;
                }
                trans.replace(R.id.main_fragment, entertainmentFragment);
                trans.commit();
                home_img.setImageResource(R.mipmap.youcailu);
                market_img.setImageResource(R.mipmap.quotes);
                transacation_img.setImageResource(R.mipmap.transaction);
                entertainment_img.setImageResource(R.mipmap.entertainmentred);
                my_img.setImageResource(R.mipmap.mine);
                changtextcolor(3);
                break;

            case R.id.my_btn:
                if(position==4){
                    return;
                }
                String usernumber = ShareUtil.getInstance().getUserNumber(MainActivity.this);
                if(usernumber.equals("")){
                    ActivityJump.jumpActivity(MainActivity.this,LoginActivity.class);
                }else{
                    trans.replace(R.id.main_fragment, myFragment);
                    trans.commit();
                    home_img.setImageResource(R.mipmap.youcailu);
                    market_img.setImageResource(R.mipmap.quotes);
                    transacation_img.setImageResource(R.mipmap.transaction);
                    entertainment_img.setImageResource(R.mipmap.entertainment);
                    my_img.setImageResource(R.mipmap.minered);
                    changtextcolor(4);
                }


                break;

        }
    }

    public void changtextcolor(int po){

        for (int i = 0; i < carhometext.size(); i++) {
            if (i == po) {
                carhometext.get(i).setTextColor(getResources().getColor(R.color.text_red));
            } else {
                carhometext.get(i).setTextColor(getResources().getColor(R.color.text_black));
            }

        }
        position = po;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
