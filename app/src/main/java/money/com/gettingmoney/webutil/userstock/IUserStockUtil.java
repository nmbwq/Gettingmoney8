package money.com.gettingmoney.webutil.userstock;

import money.com.gettingmoney.bean.UserStock;
import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface IUserStockUtil {
    public void findUserStock(String  userNumber,int pageSize,int currentPage,MyXutils.XCallBack callBack);
    public void createStock(String  userNumber,UserStock userStock,MyXutils.XCallBack callBack);
    public void deleteUserStock(String  userNumber,UserStock userStock,MyXutils.XCallBack callBack);
}
