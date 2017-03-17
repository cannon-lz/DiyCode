package com.zly.diycode;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zly.diycode.databinding.ItemReplyBinding;

/**
 * Created by zhangluya on 2017/3/17.
 */

public class TestActivity extends AppCompatActivity {

    ItemReplyBinding itemReplyBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemReplyBinding = DataBindingUtil.setContentView(this, R.layout.item_reply);
    }
}
