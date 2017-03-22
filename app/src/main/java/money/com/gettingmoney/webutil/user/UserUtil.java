package money.com.gettingmoney.webutil.user;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.bean.User;
import money.com.gettingmoney.util.JsonUitl;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/23.
 */
public class UserUtil implements IUserutil {

    private String HOST = MyAppApiConfig.HOST_URL;
    /**
     * 注册
     * @param user
     * @param validateCode
     * @param callBack
     */
    @Override
    public void userSign(LoadingDialog dialog,User user, String validateCode, MyXutils.XCallBack callBack) {
        String url = HOST+"other/userSign";
        Map<String, Object> map = new HashMap<>();
        map.put("validateCode", validateCode);
        map.put("User", JsonUitl.objectToString(user));
        MyXutils.getInstance().post(dialog,url,map,callBack);
    }

    /**
     * 密码找回
     * @param dialog
     * @param user
     * @param validateCode
     * @param callBack
     */
    @Override
    public void passReset(LoadingDialog dialog, User user, String validateCode, MyXutils.XCallBack callBack) {
        String url = HOST+"other/passReset";
        Map<String, Object> map = new HashMap<>();
        map.put("validateCode", validateCode);
        map.put("User", JsonUitl.objectToString(user));
        MyXutils.getInstance().post(dialog,url,map,callBack);
    }

    /**
     * 用户登录
     * @param dialog
     * @param code
     * @param pwd
     * @param callBack
     */
    @Override
    public void userLogin(LoadingDialog dialog, String code, String pwd, MyXutils.XCallBack callBack) {

        String url = HOST+"other/userLogin";
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("pwd", pwd);
        MyXutils.getInstance().post(dialog,url,map,callBack);
    }

    /**
     * 用户修改密码
     * @param dialog
     * @param userNumber
     * @param pwd
     * @param newPwd
     * @param callBack
     */
    @Override
    public void updateUserPwd(LoadingDialog dialog, String userNumber, String pwd, String newPwd, MyXutils.XCallBack callBack) {
        String url = HOST+"user/updateUserPwd";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("pwd", pwd);
        MyXutils.getInstance().post(dialog,url,map,callBack);
    }

    /**
     * 用户修改信息
     * @param dialog
     * @param userNumber
     * @param user
     * @param callBack
     */
    @Override
    public void updateUser(LoadingDialog dialog, String userNumber, User user, MyXutils.XCallBack callBack) {
        String url = HOST+"user/updateUser";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("user", JsonUitl.objectToString(user));
        MyXutils.getInstance().post(dialog,url,map,callBack);
    }

    @Override
    public void feedback(LoadingDialog dialog, String userNumber, String content, MyXutils.XCallBack callBack) {
        String url = HOST+"feedback/add";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("content", content);
        MyXutils.getInstance().post(dialog,url,map,callBack);
    }
}
