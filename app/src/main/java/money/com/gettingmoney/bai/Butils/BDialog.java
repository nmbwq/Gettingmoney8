package money.com.gettingmoney.bai.Butils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.bai.main.utils.ZhUtils;

/**
 * Created by Administrator on 2016/8/20.
 * 弹窗
 */
public class BDialog {


    private static  EditText editText;

    /**
     * 弹窗
     *
     * @param context  上下文
     * @param layoutId dailog的布局
     * @param title    标题
     * @param message  内容
     * @return
     */
    public static AlertDialog showDialog(Context context, int layoutId, String title, String message, View.OnClickListener confirmListener, View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);


        TextView mConfirm = (TextView) view.findViewById(R.id.mTxtDialogConfirm);
        TextView mCancel = (TextView) view.findViewById(R.id.mTxtDialogCancel);

        editText = (EditText) view.findViewById(R.id.tv_number);

        mCancel.setOnClickListener(cancelListener);
        mConfirm.setOnClickListener(confirmListener);
        //点击事件
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(view);
        //默认是在中间 -2是有多大显示多大 —1 是在顶部显示 0是没有显示
        window.setLayout(ZhUtils.DimenTrans.dip2px(context, 320), -2);
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//        window.setWindowAnimations(R.style.listdialoganimation);//设置动画
        dialog.setCanceledOnTouchOutside(true);
        //使弹出输入法
        InputMethodManager im2 = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        im2.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        dialog.getWindow()
                .clearFlags(
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showKeyboard(editText);
            }
        }, 200);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            editText.setBackgroundResource(R.drawable.zhang_ripple_home_red_button);
        return dialog;
    }

//

    public static void showKeyboard(EditText editText) {
        if(editText!=null){
            //设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            //请求获得焦点
            editText.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) editText
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
        }
    }
}
