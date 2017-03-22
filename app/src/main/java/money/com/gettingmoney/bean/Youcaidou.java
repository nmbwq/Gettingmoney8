package money.com.gettingmoney.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/21.
 */
public class Youcaidou implements Serializable {
    public int douId;
    public int youcainum;
    public int youcaimoney;

    public int getDouId() {
        return douId;
    }

    public void setDouId(int douId) {
        this.douId = douId;
    }

    public int getYoucainum() {
        return youcainum;
    }

    public void setYoucainum(int youcainum) {
        this.youcainum = youcainum;
    }

    public int getYoucaimoney() {
        return youcaimoney;
    }

    public void setYoucaimoney(int youcaimoney) {
        this.youcaimoney = youcaimoney;
    }
}
