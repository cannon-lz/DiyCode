package com.zly.diycode.http.entities;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class AbilitiesBean {

    /**
     * update : false
     * destroy : false
     */

    private boolean update;
    private boolean destroy;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
