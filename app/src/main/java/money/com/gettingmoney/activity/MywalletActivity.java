package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.ActivityJump;

public class MywalletActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 钱包界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private RelativeLayout youcaibi_layout,youcaidou_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywallet);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("我的钱包");
        head_right.setText("收支明细");
        head_right.setOnClickListener(this);

        initView();
    }

    private void initView() {
        youcaibi_layout = (RelativeLayout) this.findViewById(R.id.youcaibi_layout);
        youcaidou_layout = (RelativeLayout) this.findViewById(R.id.youcaidou_layout);

        youcaidou_layout.setOnClickListener(this);
        youcaibi_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_right:
                ActivityJump.jumpActivity(MywalletActivity.this,BalanceActivity.class);
                break;
            case R.id.youcaibi_layout:
                ActivityJump.jumpActivity(MywalletActivity.this,BuycoinActivity.class);
                break;
            case R.id.youcaidou_layout:
                ActivityJump.jumpActivity(MywalletActivity.this,BuybeanActivity.class);
                break;
        }
    }
}
