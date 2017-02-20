package money.com.gettingmoney.bai.main.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;




/**
 * Created by Administrator on 2016/3/21.
 */
public class CommonDialog {

    /**
     * 加载中的弹窗
     *
     * @param context    上下文
     * @param content    加载中的内容
     * @param canCelable 是否可以取消
     * @return
     */
    public static ProgressDialog showProgressDialog(Context context, String content, boolean canCelable, boolean canBackCancel) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage(content);
        pd.setCanceledOnTouchOutside(canCelable);
        pd.setCancelable(canBackCancel);
        return pd;
    }

    /**
     * 加载中的弹窗
     *
     * @param context    上下文
     * @param content    加载中的内容
     * @param canCelable 是否可以取消
     * @return
     */
    public static ProgressDialog showProgressDialog(Context context, String content, boolean canCelable) {
//        ProgressDialog pd = new ProgressDialog(context);
//        pd.setMessage(content);
//        pd.setCanceledOnTouchOutside(canCelable);
        return showProgressDialog(context, content, canCelable, true);
    }

    /**
     * 加载中的弹窗，默认内容：“加载中，请稍后...”
     *
     * @param context    上下文
     * @param canCelable 是否可以取消
     * @return
     */
    public static ProgressDialog showProgressDialog(Context context, boolean canCelable) {
        return showProgressDialog(context, "加载中,请稍后...", canCelable);
    }

    /**
     * 加载中的弹窗，默认内容：“加载中，请稍后...”，不能取消
     *
     * @param context 上下文
     * @return
     */
    public static ProgressDialog showProgressDialog(Context context) {
        return showProgressDialog(context, "加载中,请稍后...", false);
    }

//    /**
//     * 主页的弹窗
//     * @return
//     */
//    public static AlertDialog homeDialog(Context context,View.OnClickListener cancelListener,View.OnClickListener activationListener){
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_home_view,null);
//        EditText mInput = (EditText) view.findViewById(R.id.et_act_code);//输入框
//        TextView mCancel = (TextView) view.findViewById(R.id.tv_home_dialog_cancel);//取消
//        mCancel.setOnClickListener(cancelListener);
//        TextView mActivation = (TextView) view.findViewById(R.id.tv_home_dialog_active);//激活
//        mActivation.setOnClickListener(activationListener);
//        //点击事件
//        AlertDialog dialog = new AlertDialog.Builder(context).create();
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(0));
//        window.setContentView(view);
//        window.setLayout(ZhUtils.DimenTrans.dip2px(context, 300), -2);
//        dialog.setCanceledOnTouchOutside(false);
//        //使弹出输入法
//        InputMethodManager im2 = (InputMethodManager) context
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        im2.showSoftInput(mInput, InputMethodManager.SHOW_FORCED);
//        dialog.getWindow()
//                .clearFlags(
//                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        dialog.getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        return dialog;
//    }
//
//    /**
//     * 通用的弹窗
//     * @param context 上下文
//     * @param context 上下文
//     * @param content 弹窗内容
//     * @param buttonStr 按钮文字
//     * @param cancelListener 按钮监听事件
//     * @return
//     */
//    public static AlertDialog commonDialog(Context context,String content,String buttonStr,View.OnClickListener cancelListener){
//        View view = LayoutInflater.from(context).inflate(R.layout.zhang_dialog_common,null);
//        TextView mContent = (TextView) view.findViewById(R.id.tv_comdialog_content);//输入框
//        mContent.setText(content);
//        TextView mConfirm = (TextView) view.findViewById(R.id.tv_comdialog_confirm);//取消
//        mConfirm.setText(buttonStr);
//        mConfirm.setOnClickListener(cancelListener);
//        //点击事件
//        AlertDialog dialog = new AlertDialog.Builder(context).create();
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(0));
//        window.setContentView(view);
//        window.setLayout(ZhUtils.DimenTrans.dip2px(context,300),-2);
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }


    /**
     * 默认两个按钮的，一个标题，一段话
     *
     * @param v
     * @param context
     * @param title
     * @param message
     * @param commitListener
     * @param cancleListener
     * @param negativeButton
     * @param positiveButton
     * @param flag           0默认值，不关闭dialog,1.点击空白关闭，2，点击返回也不关闭
     * @param type           默认0 。代表展示TextView,1.代表3列的城市选择。2.代表2列的城市选择,3代表一行输入框,4三围输入框3个，5.选择星座
     * @param maxLength      默认输入长度
     * @param hintString     输入框提示
     * @param editString     输入框默认文字
     * @param inputType      代表输入框种类。1.代表Text密文，2代表Text明文,3.文字明文，4代表数字，5数字密码
     * @param isChinese      输入的类型是不是汉字 false 不是汉字 true为汉字
     */


//    return SimpleDialog22(v, context, title, message, commitListener,
//                          cancleListener, negativeButton, positiveButton, flag, type,
//                          -1, null, null, 3, false);




//    public static AlertDialog SimpleDialog22(final View v,
//                                             final Context context, String title, String message,
//                                             View.OnClickListener commitListener,
//                                             View.OnClickListener cancleListener,
//                                             final String negativeButton, final String positiveButton,
//                                             int flag, int type, int maxLength, String hintString,
//                                             String editString, int inputType, boolean isChinese) {
//        if (v != null) {
//            v.setEnabled(false);
//        }
//        View view = LayoutInflater.from(context).inflate(
//              R.layout.simpledialog_ui, null);
//        TextView titleText = (TextView) view
//                .findViewById(R.id.simple_title);
//        TextView messageText = (TextView) view
//                .findViewById(R.id.simple_message);
//        final TextView leftText = (TextView) view
//                .findViewById(R.id.simple_leftCommit);
//        final TextView rightText = (TextView) view
//                .findViewById(R.id.simple_rightCommit);
//
//        if (!(title == null)) {
//            titleText.setText(title);
//        } else {
//            titleText.setVisibility(View.GONE);
//        }
//
//        leftText.setText(negativeButton);
//        rightText.setText(positiveButton);
//        leftText.setOnClickListener(commitListener);
//        rightText.setOnClickListener(cancleListener);
//
//        AlertDialog dialog22 = new AlertDialog.Builder(context).create();
//        dialog22.show();
//        dialog22.getWindow().setBackgroundDrawable(new ColorDrawable());
//        dialog22.getWindow().setContentView(view);
//        dialog22.getWindow().setLayout(
//              DisplayUtils.dip2px(context, 300), -2);
//        switch (flag) {
//            case 1:
//
//                break;
//            case 2:
//                dialog22.setCancelable(false);
//                break;
//
//            default:
//                dialog22.setCanceledOnTouchOutside(false);
//                break;
//        }
//        if (v != null) {
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    v.setEnabled(true);
//
//                }
//            }, 1000);
//
//        }
//        switch (type) {
//            case 5:
//                ScrollerNumberPicker numberPicker = (ScrollerNumberPicker) dialog22.findViewById(R.id.simple_xingzuo);
//                numberPicker.setVisibility(View.VISIBLE);
//                String[] stringArray = context.getResources().getStringArray(R.array.xingzuo);
//                ArrayList<String> xingzuo = new ArrayList<>();
//                for (int i = 0; i < stringArray.length; i++) {
//                    xingzuo.add(stringArray[i]);
//                }
//                numberPicker.setData(xingzuo);
//                numberPicker.setOnSelectListener(new ScrollerNumberPicker.OnSelectListener() {
//                    @Override
//                    public void endSelect(int id, String text) {
//                        if (text.equals("") || text == null)
//                            return;
//                    }
//
//                    @Override
//                    public void selecting(int id, String text) {
//
//                    }
//                });
//                break;
//            case 4:
//                LinearLayout sanwei = (LinearLayout) dialog22
//                        .findViewById(R.id.sanwei_layout);
//                sanwei.setVisibility(View.VISIBLE);
//                EditText xiongwei = (EditText) dialog22.findViewById(R.id.xiongwei);
//                EditText yaowei = (EditText) dialog22.findViewById(R.id.yaowei);
//                EditText tunwei = (EditText) dialog22.findViewById(R.id.tunwei);
//                if (!TextUtils.isEmpty(editString)) {
//                    String[] split = editString.split("-");
//                    xiongwei.setText(split[0]);
//                    yaowei.setText(split[1]);
//                    tunwei.setText(split[2]);
//
//                }
////                    InputMethodManager imm = (InputMethodManager) context
////                            .getSystemService(Context.INPUT_METHOD_SERVICE);
////                    imm.showSoftInput(xiongwei, InputMethodManager.SHOW_FORCED);
//                dialog22.getWindow()
//                        .clearFlags(
//                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//                dialog22.getWindow().setSoftInputMode(
//                        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                break;
//            case 1:
//                // CityPicker_three cityPicker_three1 = (CityPicker_three)
//                // dialog22
//                // .findViewById(R.id.citypicker);
//                LinearLayout cityInclude = (LinearLayout) dialog22
//                        .findViewById(R.id.simple_citypickinclude);
//                cityInclude.setVisibility(View.VISIBLE);
//                break;
//
//            case 2:
//                LinearLayout cityInclude1 = (LinearLayout) dialog22
//                        .findViewById(R.id.simple_citypickinclude);
//                CityPicker_three cityPicker_three = (CityPicker_three) dialog22
//                        .findViewById(R.id.citypicker);
//                cityPicker_three.setCountyGone();
//                cityInclude1.setVisibility(View.VISIBLE);
//                break;
//            case 3:
//                EditText editText = (EditText) dialog22
//                        .findViewById(R.id.simple_simpleEdit);
//                if (isChinese) {
//                    //中文和英文有区别的
////                    editText.addTextChangedListener(new ChineseOrEnglishTextWatcher(editText, maxLength * 2, context));
//                } else {
//                    if (maxLength >= 0) {
//                        InputFilter[] filters = {new InputFilter.LengthFilter(
//                                maxLength)};
//                        editText.setFilters(filters);
//                    }
////                        editText.addTextChangedListener(null);
//                }
//
//                if (hintString != null) {
//                    editText.setHint(hintString);
//                }
//                if (editString != null) {
//                    editText.setText(editString);
//                    editText.setSelection(editString.length());
//                }
////                    if (maxLength >= 0) {
////                        InputFilter[] filters = {new InputFilter.LengthFilter(
////                                maxLength)};
////                        editText.setFilters(filters);
////                    }
//                switch (inputType) {
//                    case 1:
//                        editText.setInputType(InputType.TYPE_CLASS_TEXT
//                                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                        break;
//                    case 2:
//                        editText
//                                .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                        break;
//                    case 3:
//                        break;
//                    case 4:
//                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        //中文和英文有区别的
////                        editText.addTextChangedListener(new IntegerTextWatcher(editText));
//                        break;
//                    case 5:
//                        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        break;
//
//                }
//
//
//                InputMethodManager im2 = (InputMethodManager) context
//                        .getSystemService(Context.INPUT_METHOD_SERVICE);
//                im2.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
//                editText.setVisibility(View.VISIBLE);
//                dialog22.getWindow()
//                        .clearFlags(
//                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//                dialog22.getWindow().setSoftInputMode(
//                        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                break;
//
//            default:
//                if (!(message == null)) {
//                    messageText.setText(message);
//                }
//                messageText.setVisibility(View.VISIBLE);
//
//                break;
//        }
//
//        return dialog22;
//    }
}


