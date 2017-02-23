package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.adapter.DouAdapter;
import money.com.gettingmoney.bean.Youcaidou;

public class BuybeanActivity extends BaseActivity {

    /**
     * 购买有财豆界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private ListView youcaidou_listview;
    private DouAdapter adapter;
    private int[] nums = new int[]{60,180,300,600,880,1280,1880};
    private int[] moneys = new int[]{6,18,30,60,88,128,188};
    private ArrayList<Youcaidou> youcaidous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buybean);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("购买");
        head_right.setVisibility(View.GONE);

        youcaidou_listview = (ListView) this.findViewById(R.id.youcaidou_listview);
        youcaidous = new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            Youcaidou dou= new Youcaidou();
            dou.setYoucaimoney(moneys[i]);
            dou.setYoucainum(nums[i]);
            youcaidous.add(dou);
        }

        adapter = new DouAdapter(this,youcaidous);
        youcaidou_listview.setAdapter(adapter);
    }
}
