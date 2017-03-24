package com.zly.diycode.http.entities;

import android.text.TextUtils;

import com.zly.diycode.data.Mapper;

/**
 * Created by zhangluya on 2017/3/22.
 */

public class RespResult implements Mapper<Boolean> {

    private String ok;

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    @Override
    public Boolean map() {
        Boolean result = TextUtils.equals(ok, "ok");
        return result;
    }
}
