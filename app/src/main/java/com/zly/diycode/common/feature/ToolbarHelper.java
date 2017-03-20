package com.zly.diycode.common.feature;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zly.diycode.R;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangluya on 2016/12/9.
 */

public class ToolbarHelper {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    private final AppCompatActivity mActivity;
    private ActionBar mSupportActionBar;

    private Toolbar mToolbar;

    private ToolbarHelper(AppCompatActivity activity, Toolbar toolbarView) {
        this.mActivity = activity;
        mActivity.setSupportActionBar(toolbarView);
        mSupportActionBar = mActivity.getSupportActionBar();
        if (mSupportActionBar != null) {
            mSupportActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public ToolbarHelper setTitle(String text) {
        mToolbar.setTitle(text);
        return this;
    }

    public ToolbarHelper setTitle(@StringRes int text) {
        mToolbar.setTitle(text);
        return this;
    }

    public ToolbarHelper goBack() {
        if (mSupportActionBar != null) {
            mSupportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        return this;
    }

    public ToolbarHelper hideToolbar() {
        if (mSupportActionBar != null) {
            mSupportActionBar.hide();
        }
        return this;
    }

    public ToolbarHelper showToolbar() {
        if (mSupportActionBar != null) {
            mSupportActionBar.show();
        }
        return this;
    }

    public void setGoBackClickListener(final View.OnClickListener onClickListener) {
        mToolbar.setNavigationOnClickListener(onClickListener);
    }

    @Nullable
    static ToolbarHelper insert(Context context, View contentView) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup rootViewGroup = (ViewGroup) contentView;
        View toolbarLayout = inflater.inflate(R.layout.layout_toolbar, rootViewGroup, false);
        ToolbarHelper toolbarHelper = new ToolbarHelper((AppCompatActivity) context, (Toolbar) toolbarLayout);
        int id = toolbarLayout.getId();
        if (id <= 0) {
            id = generateViewId();
            toolbarLayout.setId(id);
        }
        rootViewGroup.addView(toolbarLayout, 0);
        if (rootViewGroup instanceof RelativeLayout) {
            View secondChild = rootViewGroup.getChildAt(1);
            if (secondChild != null) {
                RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) secondChild.getLayoutParams();
                p.addRule(RelativeLayout.BELOW, id);
                secondChild.setLayoutParams(p);
            }
        }
        return toolbarHelper;
    }

    private static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
}
