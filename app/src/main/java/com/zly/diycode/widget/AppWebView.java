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
