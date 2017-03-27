package com.zly.diycode.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.zly.diycode.R;
import com.zly.diycode.databinding.LayoutActionBinding;

/**
 * Created by zhangly on 2017/3/27.
 */

public class ToggleActionLayout extends RelativeLayout {

    private LayoutActionBinding mLayoutBinding;

    private Drawable mToggleDrawable;

    public ToggleActionLayout(Context context) {
        this(context, null);
    }

    public ToggleActionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleActionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLayoutBinding = LayoutActionBinding.bind(this);

        final TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ToggleActionLayout, 0, 0);
        mToggleDrawable = ta.getDrawable(R.styleable.ToggleActionLayout_toggleDrawable);

        mLayoutBinding.toggle.setButtonDrawable(mToggleDrawable);
        ta.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


    }

    public void setBadger(String badger) {
        mLayoutBinding.badger.setText(badger);
    }

}
