package money.com.gettingmoney.webutil.news;

import java.util.HashMap;
import java.util.Map;

import money.com.gettingmoney.util.MyAppApiConfig;
import money.com.gettingmoney.util.MyXutils;

/**
 * Created by Administrator on 2017/2/23.
 */
public class NewsUtil implements INewsUtil {

    private String HOST = MyAppApiConfig.HOST_URL;
    /**
     * 新闻列表
     * @param type 新闻类型,0：查询四个大分类 其余可填参数有：1、2、3、4代表查询某个类型的新闻列表
     * @param currentPage
     * @param pageSize
     * @param userNumber
     * @param callBack
     */
    @Override
    public void newslist(int type, int currentPage, int pageSize, String userNumber, MyXutils.XCallBack callBack) {
        String url = HOST+"news/list";
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("userNumber", userNumber);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 获取评论列表
     * @param newsId
     * @param currentPage
     * @param pageSize
     * @param callBack
     */
    @Override
    public void commentList(int newsId, int currentPage, int pageSize,String userNumber, MyXutils.XCallBack callBack) {
        String url = HOST+"news/newsCommentList";
        Map<String, Object> map = new HashMap<>();
        map.put("newsId", newsId);
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        map.put("userNumber", userNumber);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 添加评论
     * @param userNumber
     * @param newsId
     * @param comment
     * @param callBack
     */
    @Override
    public void addComment(String userNumber, int newsId, String comment, MyXutils.XCallBack callBack) {
        String url = HOST+"news/addComment";
        Map<String, Object> map = new HashMap<>();
        map.put("newsId", newsId);
        map.put("userNumber", userNumber);
        map.put("comment", comment);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 删除评论
     * @param userNumber
     * @param id
     * @param callBack
     */
    @Override
    public void delComment(String userNumber, int id, MyXutils.XCallBack callBack) {
        String url = HOST+"news/delComment";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("id", id);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 获取新闻详情
     * @param
     * @param userNumber
     * @param callBack
     */
    @Override
    public void getNewsData(int pageSize, int currentPage, String userNumber, int newsId, MyXutils.XCallBack callBack) {
        String url = HOST+"news/appNewsData";
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("currentPage", currentPage);
        map.put("userNumber", userNumber);
        map.put("newsId", newsId);
        MyXutils.getInstance().post(null,url,map,callBack);
    }

    /**
     * 添加收藏
     */

    @Override
    public void addCollection(String userNumber, int newsId, MyXutils.XCallBack callBack) {
        String url = HOST+"news/addCollection";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("newsId", newsId);
        MyXutils.getInstance().post(null,url,map,callBack);
    }
    /**
     * 删除收藏
     */

    @Override
    public void delCollection(String userNumber, int id, MyXutils.XCallBack callBack) {
        String url = HOST+"news/delCollection";
        Map<String, Object> map = new HashMap<>();
        map.put("userNumber", userNumber);
        map.put("id", id);
        MyXutils.getInstance().post(null,url,map,callBack);
    }


}
