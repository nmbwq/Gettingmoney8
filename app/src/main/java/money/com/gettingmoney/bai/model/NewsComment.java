package money.com.gettingmoney.bai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/12.
 * 资讯评论
 */
public class NewsComment implements Serializable {

//    评论ID
    private int id;
//    用户ID
    private int userId;
//    新闻ID
    private int newsId;
//    评论内容
    private String  comment;
//    评论时间
    private String  time;

    private String  nickName;

    private String  headImg;
    //    是否为当前用户评论（0：是 1：否）
    private int currentComment;

    public NewsComment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getCurrentComment() {
        return currentComment;
    }

    public void setCurrentComment(int currentComment) {
        this.currentComment = currentComment;
    }
}
