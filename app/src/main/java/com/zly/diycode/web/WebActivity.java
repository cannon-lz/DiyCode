package com.zly.diycode.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.zly.diycode.R;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.databinding.ActivityWebBinding;
import com.zly.diycode.widget.AppWebView;

public class WebActivity extends BaseActivity<ActivityWebBinding, VoidPresenter> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ProgressBar progressBar = mDataBinding.progressBar;
        AppWebView webView = mDataBinding.webView;
        webView.loadUrl(Navigation.IntentReceiver.getInstance().getUrl(this));
        webView.setWebViewClient(new AppWebView.AppWebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress > 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
                if (newProgress >= 100) {
                    progressBar.setVisibility(View.GONE);
                    progressBar.setProgress(0);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {

        mDataBinding.webView.destroy();

        super.onDestroy();
    }
}
