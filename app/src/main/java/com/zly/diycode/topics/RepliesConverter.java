package com.zly.diycode.topics;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.adapter.BaseAdapter;
import com.zly.diycode.common.adapter.DataBindingViewHolder;
import com.zly.diycode.databinding.ItemReplyBinding;
import com.zly.diycode.topics.EntitiesContract;

/**
 * Created by zhangluya on 2017/3/24.
 */

public class RepliesConverter implements BaseAdapter.Converter<EntitiesContract.Reply> {

    @Override
    public void convert(BaseAdapter adapter, DataBindingViewHolder viewHolder, EntitiesContract.Reply item, int position) {
        /*ItemReplyBinding replyBinding = adapter.getDataBindingByItemType(item.getItemViewType());
        TextView tvReplyContent = replyBinding.tvReplyContent;
        CharSequence text = tvReplyContent.getText();
        if (text instanceof SpannableString) {
            Spannable spannable = (Spannable) text;
            final String realUrl = findHttpLinkBySpan(spannable);
            if (!TextUtils.isEmpty(realUrl)) {
                spannable.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Navigation.getInstance().openWebBrowser(widget.getContext(), realUrl);
                    }
                }, 0, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                tvReplyContent.setText(spannable);
                tvReplyContent.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }*/
    }

    private String findHttpLinkBySpan(Spannable spannable) {
        String clickUrl = null;
        URLSpan[] urls = spannable.getSpans(0, spannable.length(), URLSpan.class);
        if (urls.length == 0) {
            return null;
        }
        for (URLSpan url : urls) {
            String u = url.getURL();
            if (u.startsWith("http://") || u.startsWith("https://")) {
                clickUrl = u;
                break;
            }
        }
        return clickUrl;
    }
}
