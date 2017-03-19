package com.zly.diycode.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by zhangly on 2017/3/18.
 */

public class DataBindingAdapter {

    @BindingAdapter({"android:drawableLeft"})
    public static void setDrawableLeft(final TextView textView, String url) {
        final Context context = textView.getContext();
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                final Drawable[] compoundDrawables = textView.getCompoundDrawables();
                final BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), resource);
                bitmapDrawable.setBounds(0, 0, 100, 100);
                textView.setCompoundDrawables(bitmapDrawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
            }
        });
    }
}
