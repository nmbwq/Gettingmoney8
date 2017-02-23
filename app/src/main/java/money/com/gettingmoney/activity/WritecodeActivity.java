package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.ActivityCollector;

public class WritecodeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 填写验证码界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right,addbankcard_finsh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writecode);
        ActivityCollector.addActivity(this);
        initView();
    }

    private void initView() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("输入短信验证码");
        head_right.setVisibility(View.GONE);
        addbankcard_finsh = (TextView) this.findViewById(R.id.addbankcard_finsh);

        addbankcard_finsh.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbankcard_finsh:
                ActivityCollector.finishAll();
                break;
        }
    }
}
