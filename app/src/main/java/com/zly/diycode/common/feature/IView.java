package com.zly.diycode.common.feature;

import android.support.annotation.StringRes;

/**
 * Created by zhangluya on 2017/3/20.
 */

public interface IView {

    void toast(String message);

    void toast(@StringRes int stringRes);
}
