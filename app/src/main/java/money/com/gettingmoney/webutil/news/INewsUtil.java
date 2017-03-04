package money.com.gettingmoney.webutil.news;

import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public interface INewsUtil {

    public void newslist(int type,int currentPage,int pageSize,String userNumber,MyXutils.XCallBack callBack);
    public void commentList(int newsId,int currentPage,int pageSize,String userNumber,MyXutils.XCallBack callBack);
    public void addComment(String userNumber,int newsId,String comment,MyXutils.XCallBack callBack);
    public void delComment(String userNumber,int id,MyXutils.XCallBack callBack);
    public void getNewsData(int pageSize,int currentPage,String userNumber,int newsId,MyXutils.XCallBack callBack);
    public void addCollection(String userNumber,int newsId,MyXutils.XCallBack callBack);
    public void delCollection(String userNumber,int id,MyXutils.XCallBack callBack);

}
