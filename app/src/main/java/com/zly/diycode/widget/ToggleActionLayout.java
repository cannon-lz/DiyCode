package com.zly.diycode.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.zly.diycode.R;

/**
 * Created by zhangly on 2017/3/27.
 */

public class ToggleActionLayout extends RelativeLayout {

    private ToggleButton mBtnToggle;
    private TextView mTvBadger;

    public ToggleActionLayout(Context context) {
        this(context, null);
    }

    public ToggleActionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleActionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_action, this);
        mTvBadger = (TextView) findViewById(R.id.badger);
        mBtnToggle = (ToggleButton) findViewById(R.id.toggle);
        final TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ToggleActionLayout, 0, 0);
        Drawable toggleDrawable = ta.getDrawable(R.styleable.ToggleActionLayout_toggleDrawable);
        mBtnToggle.setButtonDrawable(toggleDrawable);
        setBadger(ta.getString(R.styleable.ToggleActionLayout_badger));
        ta.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    public void setBadger(String badger) {
        mTvBadger.setText(badger);
    }

    public void setChecked(boolean checked) {
        mBtnToggle.setChecked(checked);
    }

    public boolean isChecked() {
        return mBtnToggle.isChecked();
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mBtnToggle.setOnCheckedChangeListener(listener);
    }
}
