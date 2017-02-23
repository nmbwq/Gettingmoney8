package money.com.gettingmoney.bai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/18 0018.
 */

public class MoniStockHomeModel implements Serializable {
    private boolean flag=false;

    public MoniStockHomeModel() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
