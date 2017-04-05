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
}
