package money.com.gettingmoney.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import money.com.gettingmoney.R;

public class PaychoseActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 支付选择界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right,pay_style,pay_num;
    private RelativeLayout pay_weixin,pay_zhifubao,pay_bank;
    private PopupWindow selectpoupWindow;
    private LinearLayout my_parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paychose);
        initView();
    }

    private void initView() {
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("订单支付");
        head_right.setVisibility(View.GONE);
        pay_style = (TextView) this.findViewById(R.id.pay_style);
        pay_num = (TextView) this.findViewById(R.id.pay_num);
        pay_weixin = (RelativeLayout) this.findViewById(R.id.pay_weixin);
        pay_zhifubao = (RelativeLayout) this.findViewById(R.id.pay_zhifubao);
        pay_bank = (RelativeLayout) this.findViewById(R.id.pay_bank);
        my_parent = (LinearLayout) this.findViewById(R.id.my_parent);
        String style = getIntent().getStringExtra("paystyle");
        int money = getIntent().getIntExtra("paynum",0);
        pay_style.setText(style);
        pay_num.setText(money+"元");

        pay_weixin.setOnClickListener(this);
        pay_zhifubao.setOnClickListener(this);
        pay_bank.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_weixin:

                break;
            case R.id.pay_zhifubao:

                break;
            case R.id.pay_bank:
                showupdataname();
                break;

        }
    }


    private void showupdataname(){
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_bankpay,
                null);



        WindowManager wm2 = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        int width2 = wm2.getDefaultDisplay().getWidth();

        selectpoupWindow = new PopupWindow(view2);
        // 设置SelectPicPopupWindow弹出窗体的宽
        selectpoupWindow.setWidth(width2);
        // 设置SelectPicPopupWindow弹出窗体的高
        selectpoupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        selectpoupWindow.setAnimationStyle(R.style.clear_animstyle);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000);
        selectpoupWindow.setBackgroundDrawable(dw);
        WindowManager.LayoutParams lp2=getWindow().getAttributes();
        lp2.alpha = 0.4f;
        getWindow().setAttributes(lp2);
        selectpoupWindow.setFocusable(true);
        selectpoupWindow.setTouchable(true);
        selectpoupWindow.showAtLocation(my_parent, Gravity.BOTTOM, 0, -100);

        selectpoupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        // 显示窗口
//			selectpoupWindow.showAtLocation(this.findViewById(R.id.fabu), 0, 0, 50);
        view2.setOnKeyListener(new View.OnKeyListener() {// 对返回键进行监听 实现点击popupWindow消失

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_BACK) {

                    selectpoupWindow.dismiss();

                    selectpoupWindow = null;
                }
                return false;
            }
        });

    }
}
