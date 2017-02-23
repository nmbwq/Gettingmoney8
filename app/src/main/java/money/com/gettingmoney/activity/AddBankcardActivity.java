package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.ActivityCollector;
import money.com.gettingmoney.util.ActivityJump;

public class AddBankcardActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 添加银行卡界面
     */
    private TextView head_title,head_right,addbankcard_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bankcard);
        ActivityCollector.addActivity(this);
        initView();
    }

    private void initView() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("添加银行卡");
        head_right.setVisibility(View.GONE);
        addbankcard_next = (TextView) this.findViewById(R.id.addbankcard_next);

        addbankcard_next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbankcard_next:
                ActivityJump.jumpActivity(AddBankcardActivity.this,WritecodeActivity.class);
                break;
        }
    }
}
