package com.zly.diycode.main;

import com.zly.diycode.BR;
import com.zly.diycode.R;
import com.zly.diycode.common.Item;

/**
 * Created by zhangly on 2017/3/18.
 */

public class Value implements Item {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getItemViewType() {
        return R.layout.item_test_1;
    }

}
