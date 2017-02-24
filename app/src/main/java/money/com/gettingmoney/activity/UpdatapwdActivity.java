package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.utils.ToastUtils;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.util.ShareUtil;
import money.com.gettingmoney.webutil.user.UserUtil;
import money.com.gettingmoney.weiget.LoadingDialog;

public class UpdatapwdActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 修改密码
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private EditText nowpwd,newpwd1,newpwd2;
    private TextView updata_sure;
    private String now,new1,new2;
    private LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpwd);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("修改密码");
        head_right.setVisibility(View.GONE);

        initview();
    }

    private void initview() {
        nowpwd = (EditText) this.findViewById(R.id.nowpwd);
        newpwd1 = (EditText) this.findViewById(R.id.newpwd1);
        newpwd2 = (EditText) this.findViewById(R.id.newpwd2);
        updata_sure = (TextView) this.findViewById(R.id.updata_sure);

        updata_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updata_sure:
                now = nowpwd.getText().toString();
                new1 = newpwd1.getText().toString();
                new2 = newpwd2.getText().toString();
                if(!now.equals("")&&!new1.equals("")&&!new2.equals("")){
                    if(new1.equals(new2)){
                        dialog = new LoadingDialog(UpdatapwdActivity.this,"正在修改");
                        dialog.show();
                        new Thread(updatapwd).run();
                    }else{
                        ToastUtils.MyToast(UpdatapwdActivity.this,"两次密码不匹配");
                    }
                }else{
                    ToastUtils.MyToast(UpdatapwdActivity.this,"请把信息填写完整");
                }
                break;
        }
    }

    Runnable updatapwd = new Runnable() {
        @Override
        public void run() {
            UserUtil util = new UserUtil();
            util.updateUserPwd(dialog, ShareUtil.getInstance().getUserNumber(UpdatapwdActivity.this), now, new1, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    ToastUtils.MyToast(UpdatapwdActivity.this,"密码修改成功");
                    finish();
                }
            });
        }
    };
}
