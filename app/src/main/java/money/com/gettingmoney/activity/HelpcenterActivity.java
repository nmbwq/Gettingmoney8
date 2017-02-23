package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.MyAppApiConfig;

public class HelpcenterActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 帮助中心
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private RelativeLayout kefu_btn1,kefu_btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpcenter);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("帮助中心");
        head_right.setVisibility(View.GONE);

        kefu_btn1 = (RelativeLayout) this.findViewById(R.id.kefu_btn1);
        kefu_btn2 = (RelativeLayout) this.findViewById(R.id.kefu_btn2);

        kefu_btn2.setOnClickListener(this);
        kefu_btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kefu_btn1:
                MyAppApiConfig.call(HelpcenterActivity.this,"4006255269");
                break;
            case R.id.kefu_btn2:
                MyAppApiConfig.call(HelpcenterActivity.this,"4002564661");
                break;
        }
    }
}
