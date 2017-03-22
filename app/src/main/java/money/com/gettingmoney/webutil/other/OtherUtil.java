package money.com.gettingmoney.webutil.other;

import android.util.Log;

import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public class OtherUtil implements Iotherutil {

    private String HOST = MyAppApiConfig.HOST_URL;
    /**
     * 发送短信验证码
     * @param mobile
     * @param callBack
     */
    @Override
    public void sendSMS(String mobile, MyXutils.XCallBack callBack) {
        String url = HOST+"other/sendSMS";
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    @Override
    public void uploadImageToQiniu(String filePath, String token,UpCompletionHandler handler) {
        UploadManager uploadManager = new UploadManager();
        // 设置图片名字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String key = "fitment_" + sdf.format(new Date());
        String path =  filePath;
        Log.i("图片长传路劲", "" + path);
        uploadManager.put(path, key, token, handler, null);
    }
}
