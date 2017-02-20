package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import money.com.gettingmoney.R;

public class FeedbackActivity extends BaseActivity {

    /**
     * 意见反馈
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("意见反馈");
        head_right.setVisibility(View.GONE);
    }
}
