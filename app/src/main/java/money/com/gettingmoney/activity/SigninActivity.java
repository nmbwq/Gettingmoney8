package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.widget.TextView;

import money.com.gettingmoney.R;

public class SigninActivity extends BaseActivity {

    /**
     * 注册
     */
    private TextView head_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_title.setText("注册");
    }
}
