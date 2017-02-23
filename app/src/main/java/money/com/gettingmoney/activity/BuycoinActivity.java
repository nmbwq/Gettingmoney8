package money.com.gettingmoney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;

public class BuycoinActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 有财币充值界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right,chongzhi_btn;
    private TextView ten,fifteen,onehuandryd,fivehandryd;
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

        chongzhi_btn = (TextView) this.findViewById(R.id.chongzhi_btn);
        chongzhi_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chongzhi_btn:
                Intent intent = new Intent(BuycoinActivity.this,PaychoseActivity.class);
                intent.putExtra("paystyle","有财币");
                intent.putExtra("paynum",20);
                startActivity(intent);
                break;
        }
    }
}
