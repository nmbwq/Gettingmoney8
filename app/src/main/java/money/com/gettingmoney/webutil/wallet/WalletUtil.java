package money.com.gettingmoney.webutil.wallet;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.bean.WalletDetail;
import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public class WalletUtil implements Iwallet {

    private String HOST = MyAppApiConfig.HOST_URL;
    /**
     * 查询钱包
     * @param userNumber
     * @param callBack
     */
    @Override
    public void findWalletByUser(String userNumber, MyXutils.XCallBack callBack) {
        String url = HOST+"wallet/findWalletByUser";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 根据用户查询钱包明细
     * @param userNumber
     * @param currentPage
     * @param pageSize
     * @param callBack
     */
    @Override
    public void findWalletDetail(String userNumber, int currentPage, int pageSize, MyXutils.XCallBack callBack) {
        String url = HOST+"walletDetail/findWalletDetail";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 钱包充值和消费
     * @param userNumber
     * @param walletDetail
     * @param callBack
     */
    @Override
    public void insertDetail(String userNumber, WalletDetail walletDetail, MyXutils.XCallBack callBack) {
        String url = HOST+"walletDetail/insertDetail";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("WalletDetail", walletDetail);
        MyXutils.getInstance().post(null,url,map,callBack);
    }
}
