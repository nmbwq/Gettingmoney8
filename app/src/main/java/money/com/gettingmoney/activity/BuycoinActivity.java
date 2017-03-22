package money.com.gettingmoney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;

public class BuycoinActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 有财币充值界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right,chongzhi_btn;
    private TextView ten,fifteen,onehuandryd,fivehandryd,needmoney;
    private int paynum = 6;
    private int position=0;
    private ArrayList<TextView> textViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycoin);
        initView();
    }

    private void initView() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("购买");
        head_right.setVisibility(View.GONE);
        textViews = new ArrayList<>();

        ten = (TextView) this.findViewById(R.id.ten);
        fifteen = (TextView) this.findViewById(R.id.fifteen);
        onehuandryd = (TextView) this.findViewById(R.id.onehuandryd);
        fivehandryd = (TextView) this.findViewById(R.id.fivehandryd);
        textViews.add(ten);
        textViews.add(fifteen);
        textViews.add(onehuandryd);
        textViews.add(fivehandryd);

        needmoney.setText(6+" 元");
        needmoney = (TextView) this.findViewById(R.id.needmoney);
        chongzhi_btn = (TextView) this.findViewById(R.id.chongzhi_btn);
        chongzhi_btn.setOnClickListener(this);
        ten.setOnClickListener(this);
        fifteen.setOnClickListener(this);
        onehuandryd.setOnClickListener(this);
        fivehandryd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chongzhi_btn:
                Intent intent = new Intent(BuycoinActivity.this,PaychoseActivity.class);
                intent.putExtra("paystyle","有财币");
                intent.putExtra("paynum",paynum);
                startActivity(intent);
                break;
            case R.id.ten:
                if(position==0){
                    return;
                }
                changetext(0);
                paynum = 6;
                needmoney.setText(6+" 元");
                break;
            case R.id.fifteen:
                if(position==1){
                    return;
                }
                changetext(1);
                paynum = 30;
                needmoney.setText(30+" 元");
                break;
            case R.id.onehuandryd:
                if(position==2){
                    return;
                }
                changetext(2);
                paynum = 60;
                needmoney.setText(60+" 元");
                break;
            case R.id.fivehandryd:
                if(position==3){
                    return;
                }
                changetext(3);
                paynum = 300;
                needmoney.setText(300+" 元");
                break;
        }
    }

    public  void changetext(int po){
        for (int i=0;i<textViews.size();i++){
            if(i==po){
                textViews.get(i).setBackground(getResources().getDrawable(R.drawable.bichose_shape));
                textViews.get(i).setTextColor(getResources().getColor(R.color.home_color));
            }else{
                textViews.get(i).setBackground(getResources().getDrawable(R.drawable.gray_shape));
                textViews.get(i).setTextColor(getResources().getColor(R.color.text_black));
            }
        }

        position = po;
    }
}
