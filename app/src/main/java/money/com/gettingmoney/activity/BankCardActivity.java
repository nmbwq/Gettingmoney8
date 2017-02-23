package money.com.gettingmoney.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import money.com.gettingmoney.R;
import money.com.gettingmoney.adapter.BankCardAdapter;
import money.com.gettingmoney.bean.BankCard;
import money.com.gettingmoney.util.ActivityJump;
import money.com.gettingmoney.weiget.ListSlideView;

public class BankCardActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 银行卡界面
     */
    private TextView head_title,head_right;
    private ListSlideView bankcard_listview;
    private BankCardAdapter adapter;
    private ArrayList<BankCard> bankCards;
    private RelativeLayout addbankcard_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        initview();
    }

    private void initview() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("设置");
        head_right.setVisibility(View.GONE);

        bankcard_listview = (ListSlideView) this.findViewById(R.id.bankcard_listview);
        addbankcard_layout = (RelativeLayout) this.findViewById(R.id.addbankcard_layout);
        bankCards = new ArrayList<>();
        BankCard bankCard = new BankCard();
        bankCard.setBankName("建设银行");
        bankCards.add(bankCard);
        bankCards.add(bankCard);
        bankCards.add(bankCard);
        adapter = new BankCardAdapter(this,bankCards);
        bankcard_listview.setAdapter(adapter);
        adapter.setRemoveListener(new BankCardAdapter.OnRemoveListener() {

            @Override
            public void onRemoveItem(int position) {
                bankCards.remove(position);
                adapter.notifyDataSetChanged();

                bankcard_listview.setAdapter(adapter);

                Toast.makeText(BankCardActivity.this, "解绑成功", Toast.LENGTH_SHORT).show();
            }
        });
        addbankcard_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addbankcard_layout:
                ActivityJump.jumpActivity(BankCardActivity.this,AddBankcardActivity.class);
                break;
        }
    }
}
