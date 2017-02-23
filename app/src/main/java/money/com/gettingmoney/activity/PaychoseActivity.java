package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;

public class PaychoseActivity extends BaseActivity {

    /**
     * 支付选择界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right,pay_style,pay_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paychose);
        initView();
    }

    private void initView() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("订单支付");
        head_right.setVisibility(View.GONE);
        pay_style = (TextView) this.findViewById(R.id.pay_style);
        pay_num = (TextView) this.findViewById(R.id.pay_num);

        String style = getIntent().getStringExtra("paystyle");
        int money = getIntent().getIntExtra("paynum",0);
        pay_style.setText(style);
        pay_num.setText(money+"元");
    }
}
