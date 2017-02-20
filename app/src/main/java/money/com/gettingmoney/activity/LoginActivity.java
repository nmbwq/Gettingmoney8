package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.ActivityJump;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 登录
     * @param savedInstanceState
     */
    private TextView login_btn,signin_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        login_btn = (TextView) this.findViewById(R.id.login_btn);
        signin_text = (TextView) this.findViewById(R.id.signin_text);
        login_btn.setOnClickListener(this);
        signin_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                ActivityJump.jumpActivity(LoginActivity.this,MainActivity.class);
                break;
            case R.id.signin_text:
                ActivityJump.jumpActivity(LoginActivity.this,SigninActivity.class);
                break;
        }
    }
}
