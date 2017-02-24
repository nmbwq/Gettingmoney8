package money.com.gettingmoney.webutil.wallet;

import money.com.gettingmoney.bean.WalletDetail;
import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface Iwallet {
    public void findWalletByUser(String userNumber,MyXutils.XCallBack callBack);
    public void findWalletDetail(String userNumber,int currentPage,int pageSize,MyXutils.XCallBack callBack);
    public void insertDetail(String userNumber,WalletDetail walletDetail,MyXutils.XCallBack callBack);
}
