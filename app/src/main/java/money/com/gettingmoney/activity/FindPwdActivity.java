package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bean.User;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.other.OtherUtil;
import money.com.gettingmoney.webutil.user.UserUtil;
import money.com.gettingmoney.weiget.LoadingDialog;
import money.com.gettingmoney.weiget.TextViewTimeCountUtil;

public class FindPwdActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 密码找回
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private EditText find_phone,find_code,findpass_new,findpass_new2;
    private TextView find_getcode,findpass_sure;
    private LoadingDialog dialog;
    private String code;
    private User user;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    long time = 60000;
                    TextViewTimeCountUtil tcu = new TextViewTimeCountUtil(FindPwdActivity.this,
                            time, 1000,
                            find_getcode, 1);
                    tcu.start();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("密码找回");
        head_right.setVisibility(View.GONE);


    }


    private void initview() {
        find_phone = (EditText) this.findViewById(R.id.find_phone);
        find_code = (EditText) this.findViewById(R.id.find_code);
        findpass_new = (EditText) this.findViewById(R.id.findpass_new);
        findpass_new2 = (EditText) this.findViewById(R.id.findpass_new2);
        find_getcode = (TextView) this.findViewById(R.id.find_getcode);
        findpass_sure = (TextView) this.findViewById(R.id.findpass_sure);

        find_getcode.setOnClickListener(this);
        findpass_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_getcode:
                String phone = find_phone.getText().toString();
                if(phone.length()==11){
                    OtherUtil webutil = new OtherUtil();
                    webutil.sendSMS(phone,new MyXutils.XCallBack() {
                        @Override
                        public void onResponse(String result) {
                            Toast.makeText(FindPwdActivity.this, "验证码已发送，请查收", Toast.LENGTH_SHORT).show();
                            Message message = new Message();
                            message.what = 100;
                            handler.sendMessage(message);
                        }
                    });
                }else{
                    Toast.makeText(FindPwdActivity.this,"请填写正确的手机号码",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.findpass_sure:

                dialog = new LoadingDialog(FindPwdActivity.this,"正在修改");
                String phone1 = find_phone.getText().toString();
                String pwd1 = findpass_new.getText().toString();
                String pwd2 = findpass_new2.getText().toString();
                code = find_code.getText().toString();
                if(phone1.length()==11&&!pwd1.equals("")&&!pwd2.equals("")&&!code.equals("")){
                    if(pwd1.equals(pwd2)){
                        dialog.show();
                        user = new User();
                        user.setCode(phone1);
                        user.setPwd(pwd1);
                        new Thread(findpwd).run();
                    }else{
                        Toast.makeText(FindPwdActivity.this,"两次密码不匹配",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(FindPwdActivity.this,"信息还没有填写完整哦！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    Runnable findpwd = new Runnable() {
        @Override
        public void run() {
            UserUtil webutil = new UserUtil();
            webutil.passReset(dialog, user,code, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    Toast.makeText(FindPwdActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    };
}
