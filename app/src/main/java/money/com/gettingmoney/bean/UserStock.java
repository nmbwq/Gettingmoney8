package money.com.gettingmoney.bean;

/**
 * Created by Administrator on 2017/2/23.
 */
public class UserStock {
    public Integer userSharesId;
    public String sharesCode;

    public Integer getUserSharesId() {
        return userSharesId;
    }

    public void setUserSharesId(Integer userSharesId) {
        this.userSharesId = userSharesId;
    }

    public String getSharesCode() {
        return sharesCode;
    }

    public void setSharesCode(String sharesCode) {
        this.sharesCode = sharesCode;
    }
}
