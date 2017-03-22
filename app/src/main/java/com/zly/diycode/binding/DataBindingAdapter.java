package com.zly.diycode.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
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
                bitmapDrawable.setBounds(0, 0, 70, 70);
                textView.setCompoundDrawables(bitmapDrawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
            }
        });
    }

    @BindingAdapter({"adapter", "layoutManager"})
    public static void setAdapterAndLayoutManager(RecyclerView recyclerView, RecyclerView.Adapter adapter, RecyclerView.LayoutManager manager) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
