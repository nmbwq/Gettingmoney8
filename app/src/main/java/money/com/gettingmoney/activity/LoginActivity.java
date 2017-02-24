package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.ActivityJump;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.user.UserUtil;
import money.com.gettingmoney.weiget.LoadingDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 登录
     * @param savedInstanceState
     */
    private TextView login_btn,signin_text;
    private EditText login_phone,login_pwd;
    private String phone,pwd;
    private LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        login_btn = (TextView) this.findViewById(R.id.login_btn);
        signin_text = (TextView) this.findViewById(R.id.signin_text);
        login_phone = (EditText) this.findViewById(R.id.login_phone);
        login_pwd = (EditText) this.findViewById(R.id.login_pwd);

        login_btn.setOnClickListener(this);
        signin_text.setOnClickListener(this);
        login_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (login_phone.getText().toString().length() > 0 && login_pwd.getText().toString().length() > 0) {

                    login_btn.setBackground(getResources().getDrawable(R.drawable.red_shape));
                } else {
                    login_btn.setBackground(getResources().getDrawable(R.drawable.login_shape));
                }
            }
        });

        login_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(login_phone.getText().toString().length()>0&&login_pwd.getText().toString().length()>0){

                    login_btn.setBackground(getResources().getDrawable(R.drawable.red_shape));
                }else{
                    login_btn.setBackground(getResources().getDrawable(R.drawable.login_shape));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                phone = login_phone.getText().toString();
                pwd = login_pwd.getText().toString();
                if(!phone.equals("")&&!pwd.equals("")){
                    dialog = new LoadingDialog(LoginActivity.this,"正在登陆");
                    dialog.show();
                    new Thread(login).run();
                }

                break;
            case R.id.signin_text:
                ActivityJump.jumpActivity(LoginActivity.this,SigninActivity.class);
                break;
        }
    }

    Runnable login = new Runnable() {
        @Override
        public void run() {
            UserUtil util = new UserUtil();
            util.userLogin(dialog, phone, pwd, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        JSONObject results  =object.getJSONObject("result");
                        String usernumber = results.getString("userNumber");
                        ShareUtil.getInstance().saveUser(LoginActivity.this,results.getJSONObject("user").toString());
                        ShareUtil.getInstance().saveUserNumber(LoginActivity.this, usernumber);
                        ActivityJump.jumpActivity(LoginActivity.this, MainActivity.class);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    };
}
