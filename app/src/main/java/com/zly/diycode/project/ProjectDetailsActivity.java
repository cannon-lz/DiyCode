package com.zly.diycode.project;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;

import com.zly.diycode.R;
import com.zly.diycode.common.HtmlUtils;
import com.zly.diycode.common.Navigation;
import com.zly.diycode.common.feature.BaseActivity;
import com.zly.diycode.common.feature.VoidPresenter;
import com.zly.diycode.data.project.Project;
import com.zly.diycode.databinding.ActivityProjectDetailsBinding;
import com.zly.diycode.widget.AppWebView;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by zhangluya on 2017/4/7.
 */

public class ProjectDetailsActivity extends BaseActivity<ActivityProjectDetailsBinding, VoidPresenter> {


    private Project mProject;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_project_details;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mProject = getIntent().getParcelableExtra("project");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mProject.getName());
        showLoading();
        mDataBinding.setProject(mProject);
        mDataBinding.setClickHandler(this);
        AppWebView wvReadme = mDataBinding.wvReadme;
        WebSettings settings = wvReadme.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        parserMarkdown();
    }

    private void parserMarkdown() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String result = "";
                Markdown4jProcessor markdown4jProcessor = new Markdown4jProcessor();
                try {
                    result = markdown4jProcessor.process(mProject.getReadme());
                } catch (IOException e) {
                    e.printStackTrace();
                    result = mProject.getReadme();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                dismissLoading();
                AppWebView wvReadme = mDataBinding.wvReadme;
                wvReadme.loadDataWithBaseURL(null, HtmlUtils.addStyleAndHeader(s), "text/html", "utf-8", null);
            }
        }.execute();
    }

    public void browser(String url) {
        Navigation.getInstance().openWebBrowser(this, url);
    }

    public void download() {
        toast("download");
    }

    public void sync() {
        toast("sync");
    }

    public void follow() {
        toast("follow");
    }

    public void imUsed() {
        toast("imUsed");
    }
}
