package money.com.gettingmoney.webutil.collect;

import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface IcollectUtil  {
    public void addCollection(String userNumber,int newsId,MyXutils.XCallBack callBack);
    public void delCollection(int id,String userNumber,MyXutils.XCallBack callBack);
    public void collectionList(LoadingDialog dialog,int currentPage,int pageSize,String userNumber,MyXutils.XCallBack callBack);
}
