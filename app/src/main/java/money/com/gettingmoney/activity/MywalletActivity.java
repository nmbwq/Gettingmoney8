package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.ActivityJump;

public class MywalletActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 钱包界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("我的钱包");
        head_right.setText("收支明细");
        head_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_right:
                ActivityJump.jumpActivity(MywalletActivity.this,BalanceActivity.class);
                break;
        }
    }
}
