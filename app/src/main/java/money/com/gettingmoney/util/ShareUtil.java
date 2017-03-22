package money.com.gettingmoney.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.qiniu.android.utils.UrlSafeBase64;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import money.com.gettingmoney.bean.User;


/**
 * Created by Administrator on 2016/11/5.
 */
public class ShareUtil {

    public static ShareUtil instance;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String AccessKey = "TKFTn87DWJO2-JsmZDYYs8OI6XzHwmpwHHq8W3By";
    private String SecretKey = "XF_r3qKHmugTx8XqAyQCucmf2N98TdKydf6DAuyE";
    public static  ShareUtil getInstance(){
        if (instance==null){
            instance = new ShareUtil();

        }

        return instance;
    }

    public String getUserNumber(Context context){

         sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        String usernumber = sharedPreferences.getString("usernumber","");
        return usernumber;
    }
    public void saveUserNumber(Context context,String uerNumber){
        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("usernumber",uerNumber);
        editor.commit();
    }

    public int getUserId(Context context){

        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        int userid = sharedPreferences.getInt("uerId", 0);
        return userid;
    }
    public void saveUserId(Context context,int uerId){
        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("uerId", uerId);
        editor.commit();
    }

    public void saveUseryue(Context context,float yue){
        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putFloat("yue", yue);
        editor.commit();
    }

    public float getUseryue(Context context){

        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        float useryue = sharedPreferences.getFloat("yue", 0);
        return useryue;
    }


    public int getUserorder(Context context){

        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        int ordernum = sharedPreferences.getInt("ordernum", 0);
        return ordernum;
    }

    public void saveUserpwd(Context context,String userPhone,String userPwd){
        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("userPhone",userPhone);
        editor.putString("userPwd",userPwd);
        editor.commit();
    }

    public String getUserPhone(Context context){

        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        String userPhone = sharedPreferences.getString("userPhone", "");
        return userPhone;
    }

    public String getUserPwd(Context context){

        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        String userPwd = sharedPreferences.getString("userPwd","");
        return userPwd;
    }

    public User getUser(Context context){

        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);

        User user = (User) JsonUitl.stringToObject(sharedPreferences.getString("user",""),User.class);
        return user;
    }
    public void saveUser(Context context,String user){
        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user",user);
        editor.commit();
    }


    public void saveisfirst(Context context,int isfirst){
        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("isfirst", isfirst);
        editor.commit();
    }

    public int getisfirst(Context context){

        sharedPreferences= context.getSharedPreferences("myshare",
                Activity.MODE_PRIVATE);
        int isfirst = sharedPreferences.getInt("isfirst", 0);
        return isfirst;
    }


    public String gettoken(){
        String _uploadToken = null;
        try {
            // 1 构造上传策略
            JSONObject _json = new JSONObject();
            long _dataline = System.currentTimeMillis() / 1000 + 3600;
            _json.put("deadline", _dataline);// 有效时间为一个小时
            _json.put("scope", "xiaofeizhuangshi");
            String _encodedPutPolicy = UrlSafeBase64.encodeToString(_json
                    .toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, SecretKey);
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
             _uploadToken = AccessKey + ':' + _encodedSign + ':'
                    + _encodedPutPolicy;

//            String SAVE_FILE_DIRECTORY = Environment
//                    .getExternalStorageDirectory() + "/testdir/test.jpg";
//            UploadManager uploadManager = new UploadManager();
//            uploadManager.put(SAVE_FILE_DIRECTORY, null, _uploadToken,
//                    new UpCompletionHandler() {
//                        @Override
//                        public void complete(String key, ResponseInfo info,
//                                             JSONObject response) {
//                            Log.e("qiniu", info.toString());
//                        }
//                    }, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return _uploadToken;
    }


    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     *
     * 这个签名方法找了半天 一个个对出来的、、、、程序猿辛苦啊、、、 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText
     *            被签名的字符串
     * @param encryptKey
     *            密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }





}
