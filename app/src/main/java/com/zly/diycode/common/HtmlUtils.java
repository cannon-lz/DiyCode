package com.zly.diycode.common;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zhangluya on 2017/4/1.
 */

public class HtmlUtils {

    public static String removePTag(String html) {
        return TextUtils.replace(html, new String[]{"<p>", "</p>"}, new CharSequence[]{"", ""}).toString();
    }

    public static String findHttpLinkBySpan(Spanned spannable) {
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

    public static void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span, ClickableSpan clickableSpan) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        strBuilder.setSpan(clickableSpan, start, end, flags);
        strBuilder.removeSpan(span);
    }

    public static void setTextViewHTML(TextView text, CharSequence sequence, ClickableSpan clickableSpan) {
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span, clickableSpan);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
