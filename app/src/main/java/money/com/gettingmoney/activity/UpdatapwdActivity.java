package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;

public class UpdatapwdActivity extends BaseActivity {

    /**
     * 修改密码
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpwd);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("修改密码");
        head_right.setVisibility(View.GONE);
    }
}
