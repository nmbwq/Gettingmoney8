package money.com.gettingmoney.weiget;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import money.com.gettingmoney.R;
import money.com.gettingmoney.util.DateFormatUtils;


/**
 * Created by Administrator on 2016/8/4.
 */
public class TextViewTimeCountUtil extends CountDownTimer {


    private TextView tv;// 按钮
    private int sta;
    private Context mcontext;
    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture，一个是countDownInterval，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TextViewTimeCountUtil(Context context,long millisInFuture,
                                 long countDownInterval, TextView tv,int stata) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
        this.sta = stata;
        this.mcontext = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        if(sta==0) {
            if(millisUntilFinished>0) {

                tv.setText("合车剩余时间: " + DateFormatUtils.getHours(millisUntilFinished));
                tv.setTextColor(mcontext.getResources().getColor(R.color.text_green));

            }else{

                tv.setText("合车剩余时间: 00:00:00");
                tv.setTextColor(mcontext.getResources().getColor(R.color.text_green));
            }
        }else if(sta==1){
            tv.setText(DateFormatUtils.getonlySecond(millisUntilFinished));
            tv.setClickable(false);
            tv.setBackground(mcontext.getResources().getDrawable(R.drawable.msgtime_shape));
        }else{
            if(millisUntilFinished>0){

                tv.setText("倒计时: " + DateFormatUtils.getHours(millisUntilFinished));
                tv.setTextColor(mcontext.getResources().getColor(R.color.text_green));
            }else{

                tv.setText("倒计时: 00:00:00" );
                tv.setTextColor(mcontext.getResources().getColor(R.color.text_green));
            }

        }
    }

    @Override
    public void onFinish() {
        if(sta==0) {
            tv.setText("合车剩余时间: 00:00:00");
            tv.setTextColor(mcontext.getResources().getColor(R.color.text_999));
        }else if(sta==1){
            tv.setText("获取验证码");
            tv.setClickable(true);
            tv.setBackground(mcontext.getResources().getDrawable(R.drawable.getcode_shape));
        }else{
            tv.setText("倒计时: 00:00:00" );
            tv.setTextColor(mcontext.getResources().getColor(R.color.text_999));
        }

    }



}
