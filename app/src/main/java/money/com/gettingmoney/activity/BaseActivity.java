package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void back(View view){
        finish();
    }


}
