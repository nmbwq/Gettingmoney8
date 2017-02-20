package money.com.gettingmoney.bai.main.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import money.com.gettingmoney.R;


/**
 * Created by Administrator on 2016/2/26.
 * 自定义吐司
 */
public class ToastUtils {
    public static Toast mToast;
    public static TextView view;

    /**
     * 自定义吐司样式，方便统一修改
     *
     * @param context
     * @param msg
     */
    public static void MyToast(Context context, String msg) {
        if (mToast == null) {
            mToast = new Toast(context);
        }
        if (view == null) {
            view = new TextView(context);
            view.setBackgroundResource(R.drawable.gray4dp_circle_shape);
            view.setAlpha(1f);
            view.setPadding(10, 5, 10, 5);
            view.setTextSize(15);
            view.setTextColor(Color.parseColor("#FFffff"));
            view.setGravity(Gravity.CENTER);
        }
        view.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
    }

    /**
     * 自定义吐司样式，方便统一修改
     *
     * @param context
     * @param msgId
     */
    public static void MyToast(Context context, int msgId) {
        MyToast(context, context.getResources().getString(msgId));
//        if (mToast == null) {
//            mToast = new Toast(context);
//        }
//        if (view == null) {
//            view = new TextView(context);
//            view.setBackgroundResource(R.drawable.gray4dp_circle_shape);
//            view.setAlpha(1f);
//            view.setPadding(10, 5, 10, 5);
//            view.setTextSize(15);
//            view.setTextColor(Color.parseColor("#FFffff"));
//            view.setGravity(Gravity.CENTER);
//        }
//        view.setText(msg);
//        mToast.setDuration(Toast.LENGTH_SHORT);
//        mToast.setView(view);
//        mToast.show();
    }
}
