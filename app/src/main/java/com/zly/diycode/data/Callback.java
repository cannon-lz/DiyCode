package com.zly.diycode.data;

/**
 * Created by zhangluya on 2017/3/22.
 */

public interface Callback<T> {

    void onSuccess(T t);

    void onError(String messgae);
}
