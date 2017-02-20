package money.com.gettingmoney.bai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/18 0018.
 */

public class zuJianModel implements Serializable{
    private String name;
    private String photoUrl;

    public zuJianModel(String name, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public zuJianModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
