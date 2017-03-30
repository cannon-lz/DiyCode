package com.zly.diycode.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zly.diycode.common.Navigation;

import static android.text.TextUtils.isEmpty;

/**
 * Created by zhangluya on 2017/3/21.
 */

public class AppWebView extends WebView {

    public AppWebView(Context context) {
        this(context, null);
    }

    public AppWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WebSettings settings = getSettings();
        settings.setDefaultFontSize(14);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);

        setWebViewClient(new AppWebViewClient());
    }

    public String addStyleAndHeader(String html, String style) {

        return setupWebContent(html, true, true, style);
    }

    private static String setupWebContent(String content, boolean isShowHighlight,
                                          boolean isShowImagePreview, String css) {
        if (isEmpty(content) || isEmpty(content.trim())) return "";

        /*content = content.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
        content = content.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");

        // 添加点击图片放大支持
        if (isShowImagePreview) {
            // TODO 用一个正则就搞定??
            content = content.replaceAll("<img[^>]+src=\"([^\"\'\\s]+)\"[^>]*>",
                    "<img src=\"$1\" onClick=\"javascript:mWebViewImageListener.showImagePreview('$1')\"/>");
            content = content.replaceAll(
                    "<a\\s+[^<>]*href=[\"\']([^\"\']+)[\"\'][^<>]*>\\s*<img\\s+src=\"([^\"\']+)\"[^<>]*//*>\\s*</a>",
                    "<a href=\"$1\"><img src=\"$2\"/></a>");
        }

        content = content.replaceAll("(<table[^>]*?)\\s+border\\s*=\\s*\\S+", "$1");
        content = content.replaceAll("(<table[^>]*?)\\s+cellspacing\\s*=\\s*\\S+", "$1");
        content = content.replaceAll("(<table[^>]*?)\\s+cellpadding\\s*=\\s*\\S+", "$1");*/

        return String.format("<!DOCTYPE html>"
                + "<html><head>"
                + (isShowHighlight
                ? "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/html/css/front.css\">"
                : "")
                + (isShowHighlight
                ? "<script type=\"text/javascript\" src=\"file:///android_asset/html/js/d3.min.js\"></script>"
                : "")
                + "%s"
                + "</head>"
                + "<body data-controller-name=\"topics\">"
                + "<div class=\"row\"><div class=\"col-md-9\"><div class=\"topic-detail panel panel-default\"><div class=\"panel-body markdown\">"
                + "<article>"
                + "%s"
                + "</article>"
                + "</div></div></div></div>"
                + "</body></html>", (css == null ? "" : css), content);
    }

    @Override
    public void destroy() {
        setWebViewClient(null);
        setWebChromeClient(null);

        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(false);

        removeAllViewsInLayout();

        removeAllViews();

        super.destroy();
    }

    public static class AppWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Navigation.getInstance().openWebBrowser(view.getContext(), url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.cancel();
        }
    }
}
