package money.com.gettingmoney.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
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

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.ActivityCollector;
import money.com.gettingmoney.util.ActivityJump;
import money.com.gettingmoney.util.FileUtil;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.ShareUtil;

public class SetActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 设置界面
     * @param savedInstanceState
     */
    private TextView head_title,head_right;
    private RelativeLayout setpwd_layout,feedback_layout,set_clean,exit_layout,mybank_layout;
    private PopupWindow selectpoupWindow;
    private LinearLayout parent;
    private File file;
    private String sizea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        head_title = (TextView) this.findViewById(R.id.head_title);
        head_right = (TextView) this.findViewById(R.id.head_right);
        head_title.setText("设置");
        head_right.setVisibility(View.GONE);
        ActivityCollector.addActivity(this);
        initView();
    }

    private void initView() {
        setpwd_layout = (RelativeLayout) this.findViewById(R.id.setpwd_layout);
        feedback_layout = (RelativeLayout) this.findViewById(R.id.feedback_layout);
        set_clean = (RelativeLayout) this.findViewById(R.id.set_clean);
        exit_layout = (RelativeLayout) this.findViewById(R.id.exit_layout);
        parent = (LinearLayout) this.findViewById(R.id.parent);
        mybank_layout = (RelativeLayout) this.findViewById(R.id.mybank_layout);

        setpwd_layout.setOnClickListener(this);
        feedback_layout.setOnClickListener(this);
        set_clean.setOnClickListener(this);
        exit_layout.setOnClickListener(this);
        mybank_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setpwd_layout:
                ActivityJump.jumpActivity(SetActivity.this,UpdatapwdActivity.class);
                break;
            case R.id.feedback_layout:
                ActivityJump.jumpActivity(SetActivity.this,FeedbackActivity.class);
                break;
            case R.id.mybank_layout:
                ActivityJump.jumpActivity(SetActivity.this,BankCardActivity.class);
                break;
            case R.id.set_clean:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    file = ImageLoader.getInstance().getDiskCache().getDirectory();
                    long size = FileUtil.getFolderSize(file);
                    sizea = FileUtil.getFormatSize(size);

                }
                showclean();
                break;
            case R.id.exit_layout:
                ActivityJump.jumpActivity(SetActivity.this,LoginActivity.class);
                ActivityCollector.addActivity(MyAppApiConfig.MAIN_ACTIVITY);
                ActivityCollector.finishAll();
                ShareUtil.getInstance().saveUser(SetActivity.this, "");
                ShareUtil.getInstance().saveUserNumber(SetActivity.this,"");
                break;

            case R.id.cancel:
                selectpoupWindow.dismiss();
                break;
            case R.id.sure:
                FileUtil.deleteFolderFile(file.getPath(), true);
                selectpoupWindow.dismiss();
                break;
        }
    }




    private void showclean(){
        View view2 = LayoutInflater.from(this).inflate(R.layout.clear_item,
                null);

        view2.findViewById(R.id.sure).setOnClickListener(this);
        TextView mo_name = (TextView) view2.findViewById(R.id.mo_name);
        mo_name.setText("内存占用"+sizea+",您确定清除缓存吗？");
        view2.findViewById(R.id.cancel).setOnClickListener(this);
        WindowManager wm2 = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        int width2 = wm2.getDefaultDisplay().getWidth();

        selectpoupWindow = new PopupWindow(view2);
        // 设置SelectPicPopupWindow弹出窗体的宽
        selectpoupWindow.setWidth(width2-120);
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
        selectpoupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

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