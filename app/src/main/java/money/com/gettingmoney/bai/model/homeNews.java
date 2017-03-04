package money.com.gettingmoney.bai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/18 0018.
 */

public class homeNews implements Serializable {
    private  int id;
    private String title;
//    新闻类型
    private int type;
//    新闻内容
    private String content;
//    图片地址
    private String pic;
//    未查看新闻的数量
    private int count;

    public homeNews() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
