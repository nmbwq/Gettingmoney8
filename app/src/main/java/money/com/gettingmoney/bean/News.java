package money.com.gettingmoney.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/20.
 */
public class News implements Serializable {
    public int newsId;
    public String newsName;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }
}
