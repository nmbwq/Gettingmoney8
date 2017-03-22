package money.com.gettingmoney.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/23.
 */
public class WalletDetail implements Serializable {
    public int state;
    public int money;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
