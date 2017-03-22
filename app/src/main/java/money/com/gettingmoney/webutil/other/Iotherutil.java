package money.com.gettingmoney.webutil.other;

import com.qiniu.android.storage.UpCompletionHandler;

import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface Iotherutil {
    public void sendSMS(String mobile,MyXutils.XCallBack callBack);
    public void uploadImageToQiniu(String filePath, String token,UpCompletionHandler handler);
}
