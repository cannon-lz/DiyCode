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

    private static final String HTML_ROOT = "<!DOCTYPE html><html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/html/css/front.css\"><script type=\"text/javascript\" src=\"file:///android_asset/html/js/d3.min.js\"></script></head><body data-controller-name=\"topics\"><div class=\"row\"><div class=\"col-md-9\"><div class=\"topic-detail panel panel-default\"><div class=\"panel-body markdown\"><article>%s</article></div></div></div></div></body></html>";

    public static String removePTag(String html) {
        return TextUtils.replace(html, new String[]{"<p>", "</p>"}, new CharSequence[]{"", ""}).toString();
    }

    public static String addStyleAndHeader(String source) {
        return String.format(HTML_ROOT, source);
    }
}
