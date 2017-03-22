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

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 意见反馈
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private EditText feedback_text;
    private TextView feedback_btn;
    private String content;
    private LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("意见反馈");
        head_right.setVisibility(View.GONE);

        feedback_text = (EditText) this.findViewById(R.id.feedback_text);
        feedback_btn = (TextView) this.findViewById(R.id.feedback_btn);

        feedback_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.feedback_btn:
                content = feedback_text.getText().toString();
                if(!content.equals("")){
                    dialog = new LoadingDialog(FeedbackActivity.this,"正在发送");
                    dialog.show();
                    new Thread(feedback).run();
                }else{
                    ToastUtils.MyToast(FeedbackActivity.this,"请填写您的建议");
                }
                break;
        }
    }

    Runnable feedback = new Runnable() {
        @Override
        public void run() {
            UserUtil util = new UserUtil();
            util.feedback(dialog, ShareUtil.getInstance().getUserNumber(FeedbackActivity.this), content, new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    ToastUtils.MyToast(FeedbackActivity.this,"发送成功");
                    finish();
                }
            });
        }
    };
}
