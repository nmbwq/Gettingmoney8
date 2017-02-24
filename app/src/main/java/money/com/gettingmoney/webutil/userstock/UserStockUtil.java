package money.com.gettingmoney.webutil.userstock;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.bean.UserStock;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public class UserStockUtil implements IUserStockUtil{

    private String HOST = MyAppApiConfig.HOST_URL;
    /**
     * 获取用户关注的股票
     * @param userNumber
     * @param pageSize
     * @param currentPage
     * @param callBack
     */
    @Override
    public void findUserStock(String userNumber, int pageSize, int currentPage, MyXutils.XCallBack callBack) {

        String url = HOST+"userStock/findUserStock";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("pageSize", pageSize);
        map.put("currentPage", currentPage);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 添加用户关注股票
     * @param userNumber
     * @param userStock
     * @param callBack
     */
    @Override
    public void createStock(String userNumber, UserStock userStock, MyXutils.XCallBack callBack) {
        String url = HOST+"userStock/createStock";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("userStock", userStock);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 删除关注的股票
     * @param userNumber
     * @param userStock
     * @param callBack
     */
    @Override
    public void deleteUserStock(String userNumber, UserStock userStock, MyXutils.XCallBack callBack) {
        String url = HOST+"userStock/findUserStock";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("userStock", userStock);
        MyXutils.getInstance().post(null,url,map,callBack);
    }
}
