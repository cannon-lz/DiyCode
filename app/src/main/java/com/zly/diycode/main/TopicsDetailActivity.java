package com.zly.diycode.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zly.diycode.R;
import com.zly.diycode.databinding.ActivityTopicsDetailBinding;

import org.xml.sax.XMLReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

/**
 * Created by zhangluya on 2017/3/13.
 */

public class TopicsDetailActivity extends AppCompatActivity {

    private static final String CONTENT = "<head><meta name='viewport' content='target-densityDpi=device-dpi'/></head>\n" +
            "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/><style type=\"text/css\">.view{padding:0;word-wrap:break-word;cursor:text;height:90%;}body{margin:8px;font-family:sans-serif;font-size:16px;}p{margin:5px 0;}</style><link rel=\"stylesheet\" type=\"text/css\" href=\"http://10.138.60.102:10080/ams/WebContent/ueditor1.4.3.3/themes/iframe.css\"><style id=\"tablesort\">table.sortEnabled tr.firstRow th,table.sortEnabled tr.firstRow td{padding-right:20px;background-repeat: no-repeat;background-position: center right;   background-image:url(http://10.138.60.102:10080/ams/WebContent/ueditor1.4.3.3/themes/default/images/sortable.png);}</style><style id=\"table\">.selectTdClass{background-color:#edf5fa !important}table.noBorderTable td,table.noBorderTable th,table.noBorderTable caption{border:1px dashed #ddd !important}table{margin-bottom:10px;border-collapse:collapse;display:table;}td,th{padding: 5px 10px;border: 1px solid #DDD;}caption{border:1px dashed #DDD;border-bottom:0;padding:3px;text-align:center;}th{border-top:1px solid #BBB;background-color:#F7F7F7;}table tr.firstRow th{border-top-width:2px;}.ue-table-interlace-color-single{ background-color: #fcfcfc; } .ue-table-interlace-color-double{ background-color: #f7faff; }td p{margin:0;padding:0;}</style><style id=\"list\">ol,ul{margin:0;pading:0;width:95%}li{clear:both;}li.list-cn-1-0{background-image:url(http://bs.baidu.com/listicon/list-cn-1-0.gif)}\n" +
            "li.list-cn-1-1{background-image:url(http://bs.baidu.com/listicon/list-cn-1-1.gif)}\n" +
            "li.list-cn-1-2{background-image:url(http://bs.baidu.com/listicon/list-cn-1-2.gif)}\n" +
            "li.list-cn-1-3{background-image:url(http://bs.baidu.com/listicon/list-cn-1-3.gif)}\n" +
            "li.list-cn-1-4{background-image:url(http://bs.baidu.com/listicon/list-cn-1-4.gif)}\n" +
            "li.list-cn-1-5{background-image:url(http://bs.baidu.com/listicon/list-cn-1-5.gif)}\n" +
            "li.list-cn-1-6{background-image:url(http://bs.baidu.com/listicon/list-cn-1-6.gif)}\n" +
            "li.list-cn-1-7{background-image:url(http://bs.baidu.com/listicon/list-cn-1-7.gif)}\n" +
            "li.list-cn-1-8{background-image:url(http://bs.baidu.com/listicon/list-cn-1-8.gif)}\n" +
            "li.list-cn-1-9{background-image:url(http://bs.baidu.com/listicon/list-cn-1-9.gif)}\n" +
            "li.list-cn-1-10{background-image:url(http://bs.baidu.com/listicon/list-cn-1-10.gif)}\n" +
            "li.list-cn-1-11{background-image:url(http://bs.baidu.com/listicon/list-cn-1-11.gif)}\n" +
            "li.list-cn-1-12{background-image:url(http://bs.baidu.com/listicon/list-cn-1-12.gif)}\n" +
            "li.list-cn-1-13{background-image:url(http://bs.baidu.com/listicon/list-cn-1-13.gif)}\n" +
            "li.list-cn-1-14{background-image:url(http://bs.baidu.com/listicon/list-cn-1-14.gif)}\n" +
            "li.list-cn-1-15{background-image:url(http://bs.baidu.com/listicon/list-cn-1-15.gif)}\n" +
            "li.list-cn-1-16{background-image:url(http://bs.baidu.com/listicon/list-cn-1-16.gif)}\n" +
            "li.list-cn-1-17{background-image:url(http://bs.baidu.com/listicon/list-cn-1-17.gif)}\n" +
            "li.list-cn-1-18{background-image:url(http://bs.baidu.com/listicon/list-cn-1-18.gif)}\n" +
            "li.list-cn-1-19{background-image:url(http://bs.baidu.com/listicon/list-cn-1-19.gif)}\n" +
            "li.list-cn-1-20{background-image:url(http://bs.baidu.com/listicon/list-cn-1-20.gif)}\n" +
            "li.list-cn-1-21{background-image:url(http://bs.baidu.com/listicon/list-cn-1-21.gif)}\n" +
            "li.list-cn-1-22{background-image:url(http://bs.baidu.com/listicon/list-cn-1-22.gif)}\n" +
            "li.list-cn-1-23{background-image:url(http://bs.baidu.com/listicon/list-cn-1-23.gif)}\n" +
            "li.list-cn-1-24{background-image:url(http://bs.baidu.com/listicon/list-cn-1-24.gif)}\n" +
            "li.list-cn-1-25{background-image:url(http://bs.baidu.com/listicon/list-cn-1-25.gif)}\n" +
            "li.list-cn-1-26{background-image:url(http://bs.baidu.com/listicon/list-cn-1-26.gif)}\n" +
            "li.list-cn-1-27{background-image:url(http://bs.baidu.com/listicon/list-cn-1-27.gif)}\n" +
            "li.list-cn-1-28{background-image:url(http://bs.baidu.com/listicon/list-cn-1-28.gif)}\n" +
            "li.list-cn-1-29{background-image:url(http://bs.baidu.com/listicon/list-cn-1-29.gif)}\n" +
            "li.list-cn-1-30{background-image:url(http://bs.baidu.com/listicon/list-cn-1-30.gif)}\n" +
            "li.list-cn-1-31{background-image:url(http://bs.baidu.com/listicon/list-cn-1-31.gif)}\n" +
            "li.list-cn-1-32{background-image:url(http://bs.baidu.com/listicon/list-cn-1-32.gif)}\n" +
            "li.list-cn-1-33{background-image:url(http://bs.baidu.com/listicon/list-cn-1-33.gif)}\n" +
            "li.list-cn-1-34{background-image:url(http://bs.baidu.com/listicon/list-cn-1-34.gif)}\n" +
            "li.list-cn-1-35{background-image:url(http://bs.baidu.com/listicon/list-cn-1-35.gif)}\n" +
            "li.list-cn-1-36{background-image:url(http://bs.baidu.com/listicon/list-cn-1-36.gif)}\n" +
            "li.list-cn-1-37{background-image:url(http://bs.baidu.com/listicon/list-cn-1-37.gif)}\n" +
            "li.list-cn-1-38{background-image:url(http://bs.baidu.com/listicon/list-cn-1-38.gif)}\n" +
            "li.list-cn-1-39{background-image:url(http://bs.baidu.com/listicon/list-cn-1-39.gif)}\n" +
            "li.list-cn-1-40{background-image:url(http://bs.baidu.com/listicon/list-cn-1-40.gif)}\n" +
            "li.list-cn-1-41{background-image:url(http://bs.baidu.com/listicon/list-cn-1-41.gif)}\n" +
            "li.list-cn-1-42{background-image:url(http://bs.baidu.com/listicon/list-cn-1-42.gif)}\n" +
            "li.list-cn-1-43{background-image:url(http://bs.baidu.com/listicon/list-cn-1-43.gif)}\n" +
            "li.list-cn-1-44{background-image:url(http://bs.baidu.com/listicon/list-cn-1-44.gif)}\n" +
            "li.list-cn-1-45{background-image:url(http://bs.baidu.com/listicon/list-cn-1-45.gif)}\n" +
            "li.list-cn-1-46{background-image:url(http://bs.baidu.com/listicon/list-cn-1-46.gif)}\n" +
            "li.list-cn-1-47{background-image:url(http://bs.baidu.com/listicon/list-cn-1-47.gif)}\n" +
            "li.list-cn-1-48{background-image:url(http://bs.baidu.com/listicon/list-cn-1-48.gif)}\n" +
            "li.list-cn-1-49{background-image:url(http://bs.baidu.com/listicon/list-cn-1-49.gif)}\n" +
            "li.list-cn-1-50{background-image:url(http://bs.baidu.com/listicon/list-cn-1-50.gif)}\n" +
            "li.list-cn-1-51{background-image:url(http://bs.baidu.com/listicon/list-cn-1-51.gif)}\n" +
            "li.list-cn-1-52{background-image:url(http://bs.baidu.com/listicon/list-cn-1-52.gif)}\n" +
            "li.list-cn-1-53{background-image:url(http://bs.baidu.com/listicon/list-cn-1-53.gif)}\n" +
            "li.list-cn-1-54{background-image:url(http://bs.baidu.com/listicon/list-cn-1-54.gif)}\n" +
            "li.list-cn-1-55{background-image:url(http://bs.baidu.com/listicon/list-cn-1-55.gif)}\n" +
            "li.list-cn-1-56{background-image:url(http://bs.baidu.com/listicon/list-cn-1-56.gif)}\n" +
            "li.list-cn-1-57{background-image:url(http://bs.baidu.com/listicon/list-cn-1-57.gif)}\n" +
            "li.list-cn-1-58{background-image:url(http://bs.baidu.com/listicon/list-cn-1-58.gif)}\n" +
            "li.list-cn-1-59{background-image:url(http://bs.baidu.com/listicon/list-cn-1-59.gif)}\n" +
            "li.list-cn-1-60{background-image:url(http://bs.baidu.com/listicon/list-cn-1-60.gif)}\n" +
            "li.list-cn-1-61{background-image:url(http://bs.baidu.com/listicon/list-cn-1-61.gif)}\n" +
            "li.list-cn-1-62{background-image:url(http://bs.baidu.com/listicon/list-cn-1-62.gif)}\n" +
            "li.list-cn-1-63{background-image:url(http://bs.baidu.com/listicon/list-cn-1-63.gif)}\n" +
            "li.list-cn-1-64{background-image:url(http://bs.baidu.com/listicon/list-cn-1-64.gif)}\n" +
            "li.list-cn-1-65{background-image:url(http://bs.baidu.com/listicon/list-cn-1-65.gif)}\n" +
            "li.list-cn-1-66{background-image:url(http://bs.baidu.com/listicon/list-cn-1-66.gif)}\n" +
            "li.list-cn-1-67{background-image:url(http://bs.baidu.com/listicon/list-cn-1-67.gif)}\n" +
            "li.list-cn-1-68{background-image:url(http://bs.baidu.com/listicon/list-cn-1-68.gif)}\n" +
            "li.list-cn-1-69{background-image:url(http://bs.baidu.com/listicon/list-cn-1-69.gif)}\n" +
            "li.list-cn-1-70{background-image:url(http://bs.baidu.com/listicon/list-cn-1-70.gif)}\n" +
            "li.list-cn-1-71{background-image:url(http://bs.baidu.com/listicon/list-cn-1-71.gif)}\n" +
            "li.list-cn-1-72{background-image:url(http://bs.baidu.com/listicon/list-cn-1-72.gif)}\n" +
            "li.list-cn-1-73{background-image:url(http://bs.baidu.com/listicon/list-cn-1-73.gif)}\n" +
            "li.list-cn-1-74{background-image:url(http://bs.baidu.com/listicon/list-cn-1-74.gif)}\n" +
            "li.list-cn-1-75{background-image:url(http://bs.baidu.com/listicon/list-cn-1-75.gif)}\n" +
            "li.list-cn-1-76{background-image:url(http://bs.baidu.com/listicon/list-cn-1-76.gif)}\n" +
            "li.list-cn-1-77{background-image:url(http://bs.baidu.com/listicon/list-cn-1-77.gif)}\n" +
            "li.list-cn-1-78{background-image:url(http://bs.baidu.com/listicon/list-cn-1-78.gif)}\n" +
            "li.list-cn-1-79{background-image:url(http://bs.baidu.com/listicon/list-cn-1-79.gif)}\n" +
            "li.list-cn-1-80{background-image:url(http://bs.baidu.com/listicon/list-cn-1-80.gif)}\n" +
            "li.list-cn-1-81{background-image:url(http://bs.baidu.com/listicon/list-cn-1-81.gif)}\n" +
            "li.list-cn-1-82{background-image:url(http://bs.baidu.com/listicon/list-cn-1-82.gif)}\n" +
            "li.list-cn-1-83{background-image:url(http://bs.baidu.com/listicon/list-cn-1-83.gif)}\n" +
            "li.list-cn-1-84{background-image:url(http://bs.baidu.com/listicon/list-cn-1-84.gif)}\n" +
            "li.list-cn-1-85{background-image:url(http://bs.baidu.com/listicon/list-cn-1-85.gif)}\n" +
            "li.list-cn-1-86{background-image:url(http://bs.baidu.com/listicon/list-cn-1-86.gif)}\n" +
            "li.list-cn-1-87{background-image:url(http://bs.baidu.com/listicon/list-cn-1-87.gif)}\n" +
            "li.list-cn-1-88{background-image:url(http://bs.baidu.com/listicon/list-cn-1-88.gif)}\n" +
            "li.list-cn-1-89{background-image:url(http://bs.baidu.com/listicon/list-cn-1-89.gif)}\n" +
            "li.list-cn-1-90{background-image:url(http://bs.baidu.com/listicon/list-cn-1-90.gif)}\n" +
            "li.list-cn-1-91{background-image:url(http://bs.baidu.com/listicon/list-cn-1-91.gif)}\n" +
            "li.list-cn-1-92{background-image:url(http://bs.baidu.com/listicon/list-cn-1-92.gif)}\n" +
            "li.list-cn-1-93{background-image:url(http://bs.baidu.com/listicon/list-cn-1-93.gif)}\n" +
            "li.list-cn-1-94{background-image:url(http://bs.baidu.com/listicon/list-cn-1-94.gif)}\n" +
            "li.list-cn-1-95{background-image:url(http://bs.baidu.com/listicon/list-cn-1-95.gif)}\n" +
            "li.list-cn-1-96{background-image:url(http://bs.baidu.com/listicon/list-cn-1-96.gif)}\n" +
            "li.list-cn-1-97{background-image:url(http://bs.baidu.com/listicon/list-cn-1-97.gif)}\n" +
            "li.list-cn-1-98{background-image:url(http://bs.baidu.com/listicon/list-cn-1-98.gif)}\n" +
            "ol.custom_cn{list-style:none;}ol.custom_cn li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-cn-paddingleft-1{padding-left:25px}\n" +
            "li.list-cn-paddingleft-2{padding-left:40px}\n" +
            "li.list-cn-paddingleft-3{padding-left:55px}\n" +
            "li.list-cn-2-0{background-image:url(http://bs.baidu.com/listicon/list-cn-2-0.gif)}\n" +
            "li.list-cn-2-1{background-image:url(http://bs.baidu.com/listicon/list-cn-2-1.gif)}\n" +
            "li.list-cn-2-2{background-image:url(http://bs.baidu.com/listicon/list-cn-2-2.gif)}\n" +
            "li.list-cn-2-3{background-image:url(http://bs.baidu.com/listicon/list-cn-2-3.gif)}\n" +
            "li.list-cn-2-4{background-image:url(http://bs.baidu.com/listicon/list-cn-2-4.gif)}\n" +
            "li.list-cn-2-5{background-image:url(http://bs.baidu.com/listicon/list-cn-2-5.gif)}\n" +
            "li.list-cn-2-6{background-image:url(http://bs.baidu.com/listicon/list-cn-2-6.gif)}\n" +
            "li.list-cn-2-7{background-image:url(http://bs.baidu.com/listicon/list-cn-2-7.gif)}\n" +
            "li.list-cn-2-8{background-image:url(http://bs.baidu.com/listicon/list-cn-2-8.gif)}\n" +
            "li.list-cn-2-9{background-image:url(http://bs.baidu.com/listicon/list-cn-2-9.gif)}\n" +
            "li.list-cn-2-10{background-image:url(http://bs.baidu.com/listicon/list-cn-2-10.gif)}\n" +
            "li.list-cn-2-11{background-image:url(http://bs.baidu.com/listicon/list-cn-2-11.gif)}\n" +
            "li.list-cn-2-12{background-image:url(http://bs.baidu.com/listicon/list-cn-2-12.gif)}\n" +
            "li.list-cn-2-13{background-image:url(http://bs.baidu.com/listicon/list-cn-2-13.gif)}\n" +
            "li.list-cn-2-14{background-image:url(http://bs.baidu.com/listicon/list-cn-2-14.gif)}\n" +
            "li.list-cn-2-15{background-image:url(http://bs.baidu.com/listicon/list-cn-2-15.gif)}\n" +
            "li.list-cn-2-16{background-image:url(http://bs.baidu.com/listicon/list-cn-2-16.gif)}\n" +
            "li.list-cn-2-17{background-image:url(http://bs.baidu.com/listicon/list-cn-2-17.gif)}\n" +
            "li.list-cn-2-18{background-image:url(http://bs.baidu.com/listicon/list-cn-2-18.gif)}\n" +
            "li.list-cn-2-19{background-image:url(http://bs.baidu.com/listicon/list-cn-2-19.gif)}\n" +
            "li.list-cn-2-20{background-image:url(http://bs.baidu.com/listicon/list-cn-2-20.gif)}\n" +
            "li.list-cn-2-21{background-image:url(http://bs.baidu.com/listicon/list-cn-2-21.gif)}\n" +
            "li.list-cn-2-22{background-image:url(http://bs.baidu.com/listicon/list-cn-2-22.gif)}\n" +
            "li.list-cn-2-23{background-image:url(http://bs.baidu.com/listicon/list-cn-2-23.gif)}\n" +
            "li.list-cn-2-24{background-image:url(http://bs.baidu.com/listicon/list-cn-2-24.gif)}\n" +
            "li.list-cn-2-25{background-image:url(http://bs.baidu.com/listicon/list-cn-2-25.gif)}\n" +
            "li.list-cn-2-26{background-image:url(http://bs.baidu.com/listicon/list-cn-2-26.gif)}\n" +
            "li.list-cn-2-27{background-image:url(http://bs.baidu.com/listicon/list-cn-2-27.gif)}\n" +
            "li.list-cn-2-28{background-image:url(http://bs.baidu.com/listicon/list-cn-2-28.gif)}\n" +
            "li.list-cn-2-29{background-image:url(http://bs.baidu.com/listicon/list-cn-2-29.gif)}\n" +
            "li.list-cn-2-30{background-image:url(http://bs.baidu.com/listicon/list-cn-2-30.gif)}\n" +
            "li.list-cn-2-31{background-image:url(http://bs.baidu.com/listicon/list-cn-2-31.gif)}\n" +
            "li.list-cn-2-32{background-image:url(http://bs.baidu.com/listicon/list-cn-2-32.gif)}\n" +
            "li.list-cn-2-33{background-image:url(http://bs.baidu.com/listicon/list-cn-2-33.gif)}\n" +
            "li.list-cn-2-34{background-image:url(http://bs.baidu.com/listicon/list-cn-2-34.gif)}\n" +
            "li.list-cn-2-35{background-image:url(http://bs.baidu.com/listicon/list-cn-2-35.gif)}\n" +
            "li.list-cn-2-36{background-image:url(http://bs.baidu.com/listicon/list-cn-2-36.gif)}\n" +
            "li.list-cn-2-37{background-image:url(http://bs.baidu.com/listicon/list-cn-2-37.gif)}\n" +
            "li.list-cn-2-38{background-image:url(http://bs.baidu.com/listicon/list-cn-2-38.gif)}\n" +
            "li.list-cn-2-39{background-image:url(http://bs.baidu.com/listicon/list-cn-2-39.gif)}\n" +
            "li.list-cn-2-40{background-image:url(http://bs.baidu.com/listicon/list-cn-2-40.gif)}\n" +
            "li.list-cn-2-41{background-image:url(http://bs.baidu.com/listicon/list-cn-2-41.gif)}\n" +
            "li.list-cn-2-42{background-image:url(http://bs.baidu.com/listicon/list-cn-2-42.gif)}\n" +
            "li.list-cn-2-43{background-image:url(http://bs.baidu.com/listicon/list-cn-2-43.gif)}\n" +
            "li.list-cn-2-44{background-image:url(http://bs.baidu.com/listicon/list-cn-2-44.gif)}\n" +
            "li.list-cn-2-45{background-image:url(http://bs.baidu.com/listicon/list-cn-2-45.gif)}\n" +
            "li.list-cn-2-46{background-image:url(http://bs.baidu.com/listicon/list-cn-2-46.gif)}\n" +
            "li.list-cn-2-47{background-image:url(http://bs.baidu.com/listicon/list-cn-2-47.gif)}\n" +
            "li.list-cn-2-48{background-image:url(http://bs.baidu.com/listicon/list-cn-2-48.gif)}\n" +
            "li.list-cn-2-49{background-image:url(http://bs.baidu.com/listicon/list-cn-2-49.gif)}\n" +
            "li.list-cn-2-50{background-image:url(http://bs.baidu.com/listicon/list-cn-2-50.gif)}\n" +
            "li.list-cn-2-51{background-image:url(http://bs.baidu.com/listicon/list-cn-2-51.gif)}\n" +
            "li.list-cn-2-52{background-image:url(http://bs.baidu.com/listicon/list-cn-2-52.gif)}\n" +
            "li.list-cn-2-53{background-image:url(http://bs.baidu.com/listicon/list-cn-2-53.gif)}\n" +
            "li.list-cn-2-54{background-image:url(http://bs.baidu.com/listicon/list-cn-2-54.gif)}\n" +
            "li.list-cn-2-55{background-image:url(http://bs.baidu.com/listicon/list-cn-2-55.gif)}\n" +
            "li.list-cn-2-56{background-image:url(http://bs.baidu.com/listicon/list-cn-2-56.gif)}\n" +
            "li.list-cn-2-57{background-image:url(http://bs.baidu.com/listicon/list-cn-2-57.gif)}\n" +
            "li.list-cn-2-58{background-image:url(http://bs.baidu.com/listicon/list-cn-2-58.gif)}\n" +
            "li.list-cn-2-59{background-image:url(http://bs.baidu.com/listicon/list-cn-2-59.gif)}\n" +
            "li.list-cn-2-60{background-image:url(http://bs.baidu.com/listicon/list-cn-2-60.gif)}\n" +
            "li.list-cn-2-61{background-image:url(http://bs.baidu.com/listicon/list-cn-2-61.gif)}\n" +
            "li.list-cn-2-62{background-image:url(http://bs.baidu.com/listicon/list-cn-2-62.gif)}\n" +
            "li.list-cn-2-63{background-image:url(http://bs.baidu.com/listicon/list-cn-2-63.gif)}\n" +
            "li.list-cn-2-64{background-image:url(http://bs.baidu.com/listicon/list-cn-2-64.gif)}\n" +
            "li.list-cn-2-65{background-image:url(http://bs.baidu.com/listicon/list-cn-2-65.gif)}\n" +
            "li.list-cn-2-66{background-image:url(http://bs.baidu.com/listicon/list-cn-2-66.gif)}\n" +
            "li.list-cn-2-67{background-image:url(http://bs.baidu.com/listicon/list-cn-2-67.gif)}\n" +
            "li.list-cn-2-68{background-image:url(http://bs.baidu.com/listicon/list-cn-2-68.gif)}\n" +
            "li.list-cn-2-69{background-image:url(http://bs.baidu.com/listicon/list-cn-2-69.gif)}\n" +
            "li.list-cn-2-70{background-image:url(http://bs.baidu.com/listicon/list-cn-2-70.gif)}\n" +
            "li.list-cn-2-71{background-image:url(http://bs.baidu.com/listicon/list-cn-2-71.gif)}\n" +
            "li.list-cn-2-72{background-image:url(http://bs.baidu.com/listicon/list-cn-2-72.gif)}\n" +
            "li.list-cn-2-73{background-image:url(http://bs.baidu.com/listicon/list-cn-2-73.gif)}\n" +
            "li.list-cn-2-74{background-image:url(http://bs.baidu.com/listicon/list-cn-2-74.gif)}\n" +
            "li.list-cn-2-75{background-image:url(http://bs.baidu.com/listicon/list-cn-2-75.gif)}\n" +
            "li.list-cn-2-76{background-image:url(http://bs.baidu.com/listicon/list-cn-2-76.gif)}\n" +
            "li.list-cn-2-77{background-image:url(http://bs.baidu.com/listicon/list-cn-2-77.gif)}\n" +
            "li.list-cn-2-78{background-image:url(http://bs.baidu.com/listicon/list-cn-2-78.gif)}\n" +
            "li.list-cn-2-79{background-image:url(http://bs.baidu.com/listicon/list-cn-2-79.gif)}\n" +
            "li.list-cn-2-80{background-image:url(http://bs.baidu.com/listicon/list-cn-2-80.gif)}\n" +
            "li.list-cn-2-81{background-image:url(http://bs.baidu.com/listicon/list-cn-2-81.gif)}\n" +
            "li.list-cn-2-82{background-image:url(http://bs.baidu.com/listicon/list-cn-2-82.gif)}\n" +
            "li.list-cn-2-83{background-image:url(http://bs.baidu.com/listicon/list-cn-2-83.gif)}\n" +
            "li.list-cn-2-84{background-image:url(http://bs.baidu.com/listicon/list-cn-2-84.gif)}\n" +
            "li.list-cn-2-85{background-image:url(http://bs.baidu.com/listicon/list-cn-2-85.gif)}\n" +
            "li.list-cn-2-86{background-image:url(http://bs.baidu.com/listicon/list-cn-2-86.gif)}\n" +
            "li.list-cn-2-87{background-image:url(http://bs.baidu.com/listicon/list-cn-2-87.gif)}\n" +
            "li.list-cn-2-88{background-image:url(http://bs.baidu.com/listicon/list-cn-2-88.gif)}\n" +
            "li.list-cn-2-89{background-image:url(http://bs.baidu.com/listicon/list-cn-2-89.gif)}\n" +
            "li.list-cn-2-90{background-image:url(http://bs.baidu.com/listicon/list-cn-2-90.gif)}\n" +
            "li.list-cn-2-91{background-image:url(http://bs.baidu.com/listicon/list-cn-2-91.gif)}\n" +
            "li.list-cn-2-92{background-image:url(http://bs.baidu.com/listicon/list-cn-2-92.gif)}\n" +
            "li.list-cn-2-93{background-image:url(http://bs.baidu.com/listicon/list-cn-2-93.gif)}\n" +
            "li.list-cn-2-94{background-image:url(http://bs.baidu.com/listicon/list-cn-2-94.gif)}\n" +
            "li.list-cn-2-95{background-image:url(http://bs.baidu.com/listicon/list-cn-2-95.gif)}\n" +
            "li.list-cn-2-96{background-image:url(http://bs.baidu.com/listicon/list-cn-2-96.gif)}\n" +
            "li.list-cn-2-97{background-image:url(http://bs.baidu.com/listicon/list-cn-2-97.gif)}\n" +
            "li.list-cn-2-98{background-image:url(http://bs.baidu.com/listicon/list-cn-2-98.gif)}\n" +
            "ol.custom_cn1{list-style:none;}ol.custom_cn1 li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-cn1-paddingleft-1{padding-left:30px}\n" +
            "li.list-cn1-paddingleft-2{padding-left:40px}\n" +
            "li.list-cn1-paddingleft-3{padding-left:55px}\n" +
            "li.list-cn-3-0{background-image:url(http://bs.baidu.com/listicon/list-cn-3-0.gif)}\n" +
            "li.list-cn-3-1{background-image:url(http://bs.baidu.com/listicon/list-cn-3-1.gif)}\n" +
            "li.list-cn-3-2{background-image:url(http://bs.baidu.com/listicon/list-cn-3-2.gif)}\n" +
            "li.list-cn-3-3{background-image:url(http://bs.baidu.com/listicon/list-cn-3-3.gif)}\n" +
            "li.list-cn-3-4{background-image:url(http://bs.baidu.com/listicon/list-cn-3-4.gif)}\n" +
            "li.list-cn-3-5{background-image:url(http://bs.baidu.com/listicon/list-cn-3-5.gif)}\n" +
            "li.list-cn-3-6{background-image:url(http://bs.baidu.com/listicon/list-cn-3-6.gif)}\n" +
            "li.list-cn-3-7{background-image:url(http://bs.baidu.com/listicon/list-cn-3-7.gif)}\n" +
            "li.list-cn-3-8{background-image:url(http://bs.baidu.com/listicon/list-cn-3-8.gif)}\n" +
            "li.list-cn-3-9{background-image:url(http://bs.baidu.com/listicon/list-cn-3-9.gif)}\n" +
            "li.list-cn-3-10{background-image:url(http://bs.baidu.com/listicon/list-cn-3-10.gif)}\n" +
            "li.list-cn-3-11{background-image:url(http://bs.baidu.com/listicon/list-cn-3-11.gif)}\n" +
            "li.list-cn-3-12{background-image:url(http://bs.baidu.com/listicon/list-cn-3-12.gif)}\n" +
            "li.list-cn-3-13{background-image:url(http://bs.baidu.com/listicon/list-cn-3-13.gif)}\n" +
            "li.list-cn-3-14{background-image:url(http://bs.baidu.com/listicon/list-cn-3-14.gif)}\n" +
            "li.list-cn-3-15{background-image:url(http://bs.baidu.com/listicon/list-cn-3-15.gif)}\n" +
            "li.list-cn-3-16{background-image:url(http://bs.baidu.com/listicon/list-cn-3-16.gif)}\n" +
            "li.list-cn-3-17{background-image:url(http://bs.baidu.com/listicon/list-cn-3-17.gif)}\n" +
            "li.list-cn-3-18{background-image:url(http://bs.baidu.com/listicon/list-cn-3-18.gif)}\n" +
            "li.list-cn-3-19{background-image:url(http://bs.baidu.com/listicon/list-cn-3-19.gif)}\n" +
            "li.list-cn-3-20{background-image:url(http://bs.baidu.com/listicon/list-cn-3-20.gif)}\n" +
            "li.list-cn-3-21{background-image:url(http://bs.baidu.com/listicon/list-cn-3-21.gif)}\n" +
            "li.list-cn-3-22{background-image:url(http://bs.baidu.com/listicon/list-cn-3-22.gif)}\n" +
            "li.list-cn-3-23{background-image:url(http://bs.baidu.com/listicon/list-cn-3-23.gif)}\n" +
            "li.list-cn-3-24{background-image:url(http://bs.baidu.com/listicon/list-cn-3-24.gif)}\n" +
            "li.list-cn-3-25{background-image:url(http://bs.baidu.com/listicon/list-cn-3-25.gif)}\n" +
            "li.list-cn-3-26{background-image:url(http://bs.baidu.com/listicon/list-cn-3-26.gif)}\n" +
            "li.list-cn-3-27{background-image:url(http://bs.baidu.com/listicon/list-cn-3-27.gif)}\n" +
            "li.list-cn-3-28{background-image:url(http://bs.baidu.com/listicon/list-cn-3-28.gif)}\n" +
            "li.list-cn-3-29{background-image:url(http://bs.baidu.com/listicon/list-cn-3-29.gif)}\n" +
            "li.list-cn-3-30{background-image:url(http://bs.baidu.com/listicon/list-cn-3-30.gif)}\n" +
            "li.list-cn-3-31{background-image:url(http://bs.baidu.com/listicon/list-cn-3-31.gif)}\n" +
            "li.list-cn-3-32{background-image:url(http://bs.baidu.com/listicon/list-cn-3-32.gif)}\n" +
            "li.list-cn-3-33{background-image:url(http://bs.baidu.com/listicon/list-cn-3-33.gif)}\n" +
            "li.list-cn-3-34{background-image:url(http://bs.baidu.com/listicon/list-cn-3-34.gif)}\n" +
            "li.list-cn-3-35{background-image:url(http://bs.baidu.com/listicon/list-cn-3-35.gif)}\n" +
            "li.list-cn-3-36{background-image:url(http://bs.baidu.com/listicon/list-cn-3-36.gif)}\n" +
            "li.list-cn-3-37{background-image:url(http://bs.baidu.com/listicon/list-cn-3-37.gif)}\n" +
            "li.list-cn-3-38{background-image:url(http://bs.baidu.com/listicon/list-cn-3-38.gif)}\n" +
            "li.list-cn-3-39{background-image:url(http://bs.baidu.com/listicon/list-cn-3-39.gif)}\n" +
            "li.list-cn-3-40{background-image:url(http://bs.baidu.com/listicon/list-cn-3-40.gif)}\n" +
            "li.list-cn-3-41{background-image:url(http://bs.baidu.com/listicon/list-cn-3-41.gif)}\n" +
            "li.list-cn-3-42{background-image:url(http://bs.baidu.com/listicon/list-cn-3-42.gif)}\n" +
            "li.list-cn-3-43{background-image:url(http://bs.baidu.com/listicon/list-cn-3-43.gif)}\n" +
            "li.list-cn-3-44{background-image:url(http://bs.baidu.com/listicon/list-cn-3-44.gif)}\n" +
            "li.list-cn-3-45{background-image:url(http://bs.baidu.com/listicon/list-cn-3-45.gif)}\n" +
            "li.list-cn-3-46{background-image:url(http://bs.baidu.com/listicon/list-cn-3-46.gif)}\n" +
            "li.list-cn-3-47{background-image:url(http://bs.baidu.com/listicon/list-cn-3-47.gif)}\n" +
            "li.list-cn-3-48{background-image:url(http://bs.baidu.com/listicon/list-cn-3-48.gif)}\n" +
            "li.list-cn-3-49{background-image:url(http://bs.baidu.com/listicon/list-cn-3-49.gif)}\n" +
            "li.list-cn-3-50{background-image:url(http://bs.baidu.com/listicon/list-cn-3-50.gif)}\n" +
            "li.list-cn-3-51{background-image:url(http://bs.baidu.com/listicon/list-cn-3-51.gif)}\n" +
            "li.list-cn-3-52{background-image:url(http://bs.baidu.com/listicon/list-cn-3-52.gif)}\n" +
            "li.list-cn-3-53{background-image:url(http://bs.baidu.com/listicon/list-cn-3-53.gif)}\n" +
            "li.list-cn-3-54{background-image:url(http://bs.baidu.com/listicon/list-cn-3-54.gif)}\n" +
            "li.list-cn-3-55{background-image:url(http://bs.baidu.com/listicon/list-cn-3-55.gif)}\n" +
            "li.list-cn-3-56{background-image:url(http://bs.baidu.com/listicon/list-cn-3-56.gif)}\n" +
            "li.list-cn-3-57{background-image:url(http://bs.baidu.com/listicon/list-cn-3-57.gif)}\n" +
            "li.list-cn-3-58{background-image:url(http://bs.baidu.com/listicon/list-cn-3-58.gif)}\n" +
            "li.list-cn-3-59{background-image:url(http://bs.baidu.com/listicon/list-cn-3-59.gif)}\n" +
            "li.list-cn-3-60{background-image:url(http://bs.baidu.com/listicon/list-cn-3-60.gif)}\n" +
            "li.list-cn-3-61{background-image:url(http://bs.baidu.com/listicon/list-cn-3-61.gif)}\n" +
            "li.list-cn-3-62{background-image:url(http://bs.baidu.com/listicon/list-cn-3-62.gif)}\n" +
            "li.list-cn-3-63{background-image:url(http://bs.baidu.com/listicon/list-cn-3-63.gif)}\n" +
            "li.list-cn-3-64{background-image:url(http://bs.baidu.com/listicon/list-cn-3-64.gif)}\n" +
            "li.list-cn-3-65{background-image:url(http://bs.baidu.com/listicon/list-cn-3-65.gif)}\n" +
            "li.list-cn-3-66{background-image:url(http://bs.baidu.com/listicon/list-cn-3-66.gif)}\n" +
            "li.list-cn-3-67{background-image:url(http://bs.baidu.com/listicon/list-cn-3-67.gif)}\n" +
            "li.list-cn-3-68{background-image:url(http://bs.baidu.com/listicon/list-cn-3-68.gif)}\n" +
            "li.list-cn-3-69{background-image:url(http://bs.baidu.com/listicon/list-cn-3-69.gif)}\n" +
            "li.list-cn-3-70{background-image:url(http://bs.baidu.com/listicon/list-cn-3-70.gif)}\n" +
            "li.list-cn-3-71{background-image:url(http://bs.baidu.com/listicon/list-cn-3-71.gif)}\n" +
            "li.list-cn-3-72{background-image:url(http://bs.baidu.com/listicon/list-cn-3-72.gif)}\n" +
            "li.list-cn-3-73{background-image:url(http://bs.baidu.com/listicon/list-cn-3-73.gif)}\n" +
            "li.list-cn-3-74{background-image:url(http://bs.baidu.com/listicon/list-cn-3-74.gif)}\n" +
            "li.list-cn-3-75{background-image:url(http://bs.baidu.com/listicon/list-cn-3-75.gif)}\n" +
            "li.list-cn-3-76{background-image:url(http://bs.baidu.com/listicon/list-cn-3-76.gif)}\n" +
            "li.list-cn-3-77{background-image:url(http://bs.baidu.com/listicon/list-cn-3-77.gif)}\n" +
            "li.list-cn-3-78{background-image:url(http://bs.baidu.com/listicon/list-cn-3-78.gif)}\n" +
            "li.list-cn-3-79{background-image:url(http://bs.baidu.com/listicon/list-cn-3-79.gif)}\n" +
            "li.list-cn-3-80{background-image:url(http://bs.baidu.com/listicon/list-cn-3-80.gif)}\n" +
            "li.list-cn-3-81{background-image:url(http://bs.baidu.com/listicon/list-cn-3-81.gif)}\n" +
            "li.list-cn-3-82{background-image:url(http://bs.baidu.com/listicon/list-cn-3-82.gif)}\n" +
            "li.list-cn-3-83{background-image:url(http://bs.baidu.com/listicon/list-cn-3-83.gif)}\n" +
            "li.list-cn-3-84{background-image:url(http://bs.baidu.com/listicon/list-cn-3-84.gif)}\n" +
            "li.list-cn-3-85{background-image:url(http://bs.baidu.com/listicon/list-cn-3-85.gif)}\n" +
            "li.list-cn-3-86{background-image:url(http://bs.baidu.com/listicon/list-cn-3-86.gif)}\n" +
            "li.list-cn-3-87{background-image:url(http://bs.baidu.com/listicon/list-cn-3-87.gif)}\n" +
            "li.list-cn-3-88{background-image:url(http://bs.baidu.com/listicon/list-cn-3-88.gif)}\n" +
            "li.list-cn-3-89{background-image:url(http://bs.baidu.com/listicon/list-cn-3-89.gif)}\n" +
            "li.list-cn-3-90{background-image:url(http://bs.baidu.com/listicon/list-cn-3-90.gif)}\n" +
            "li.list-cn-3-91{background-image:url(http://bs.baidu.com/listicon/list-cn-3-91.gif)}\n" +
            "li.list-cn-3-92{background-image:url(http://bs.baidu.com/listicon/list-cn-3-92.gif)}\n" +
            "li.list-cn-3-93{background-image:url(http://bs.baidu.com/listicon/list-cn-3-93.gif)}\n" +
            "li.list-cn-3-94{background-image:url(http://bs.baidu.com/listicon/list-cn-3-94.gif)}\n" +
            "li.list-cn-3-95{background-image:url(http://bs.baidu.com/listicon/list-cn-3-95.gif)}\n" +
            "li.list-cn-3-96{background-image:url(http://bs.baidu.com/listicon/list-cn-3-96.gif)}\n" +
            "li.list-cn-3-97{background-image:url(http://bs.baidu.com/listicon/list-cn-3-97.gif)}\n" +
            "li.list-cn-3-98{background-image:url(http://bs.baidu.com/listicon/list-cn-3-98.gif)}\n" +
            "ol.custom_cn2{list-style:none;}ol.custom_cn2 li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-cn2-paddingleft-1{padding-left:40px}\n" +
            "li.list-cn2-paddingleft-2{padding-left:55px}\n" +
            "li.list-cn2-paddingleft-3{padding-left:68px}\n" +
            "li.list-num-1-0{background-image:url(http://bs.baidu.com/listicon/list-num-1-0.gif)}\n" +
            "li.list-num-1-1{background-image:url(http://bs.baidu.com/listicon/list-num-1-1.gif)}\n" +
            "li.list-num-1-2{background-image:url(http://bs.baidu.com/listicon/list-num-1-2.gif)}\n" +
            "li.list-num-1-3{background-image:url(http://bs.baidu.com/listicon/list-num-1-3.gif)}\n" +
            "li.list-num-1-4{background-image:url(http://bs.baidu.com/listicon/list-num-1-4.gif)}\n" +
            "li.list-num-1-5{background-image:url(http://bs.baidu.com/listicon/list-num-1-5.gif)}\n" +
            "li.list-num-1-6{background-image:url(http://bs.baidu.com/listicon/list-num-1-6.gif)}\n" +
            "li.list-num-1-7{background-image:url(http://bs.baidu.com/listicon/list-num-1-7.gif)}\n" +
            "li.list-num-1-8{background-image:url(http://bs.baidu.com/listicon/list-num-1-8.gif)}\n" +
            "li.list-num-1-9{background-image:url(http://bs.baidu.com/listicon/list-num-1-9.gif)}\n" +
            "li.list-num-1-10{background-image:url(http://bs.baidu.com/listicon/list-num-1-10.gif)}\n" +
            "li.list-num-1-11{background-image:url(http://bs.baidu.com/listicon/list-num-1-11.gif)}\n" +
            "li.list-num-1-12{background-image:url(http://bs.baidu.com/listicon/list-num-1-12.gif)}\n" +
            "li.list-num-1-13{background-image:url(http://bs.baidu.com/listicon/list-num-1-13.gif)}\n" +
            "li.list-num-1-14{background-image:url(http://bs.baidu.com/listicon/list-num-1-14.gif)}\n" +
            "li.list-num-1-15{background-image:url(http://bs.baidu.com/listicon/list-num-1-15.gif)}\n" +
            "li.list-num-1-16{background-image:url(http://bs.baidu.com/listicon/list-num-1-16.gif)}\n" +
            "li.list-num-1-17{background-image:url(http://bs.baidu.com/listicon/list-num-1-17.gif)}\n" +
            "li.list-num-1-18{background-image:url(http://bs.baidu.com/listicon/list-num-1-18.gif)}\n" +
            "li.list-num-1-19{background-image:url(http://bs.baidu.com/listicon/list-num-1-19.gif)}\n" +
            "li.list-num-1-20{background-image:url(http://bs.baidu.com/listicon/list-num-1-20.gif)}\n" +
            "li.list-num-1-21{background-image:url(http://bs.baidu.com/listicon/list-num-1-21.gif)}\n" +
            "li.list-num-1-22{background-image:url(http://bs.baidu.com/listicon/list-num-1-22.gif)}\n" +
            "li.list-num-1-23{background-image:url(http://bs.baidu.com/listicon/list-num-1-23.gif)}\n" +
            "li.list-num-1-24{background-image:url(http://bs.baidu.com/listicon/list-num-1-24.gif)}\n" +
            "li.list-num-1-25{background-image:url(http://bs.baidu.com/listicon/list-num-1-25.gif)}\n" +
            "li.list-num-1-26{background-image:url(http://bs.baidu.com/listicon/list-num-1-26.gif)}\n" +
            "li.list-num-1-27{background-image:url(http://bs.baidu.com/listicon/list-num-1-27.gif)}\n" +
            "li.list-num-1-28{background-image:url(http://bs.baidu.com/listicon/list-num-1-28.gif)}\n" +
            "li.list-num-1-29{background-image:url(http://bs.baidu.com/listicon/list-num-1-29.gif)}\n" +
            "li.list-num-1-30{background-image:url(http://bs.baidu.com/listicon/list-num-1-30.gif)}\n" +
            "li.list-num-1-31{background-image:url(http://bs.baidu.com/listicon/list-num-1-31.gif)}\n" +
            "li.list-num-1-32{background-image:url(http://bs.baidu.com/listicon/list-num-1-32.gif)}\n" +
            "li.list-num-1-33{background-image:url(http://bs.baidu.com/listicon/list-num-1-33.gif)}\n" +
            "li.list-num-1-34{background-image:url(http://bs.baidu.com/listicon/list-num-1-34.gif)}\n" +
            "li.list-num-1-35{background-image:url(http://bs.baidu.com/listicon/list-num-1-35.gif)}\n" +
            "li.list-num-1-36{background-image:url(http://bs.baidu.com/listicon/list-num-1-36.gif)}\n" +
            "li.list-num-1-37{background-image:url(http://bs.baidu.com/listicon/list-num-1-37.gif)}\n" +
            "li.list-num-1-38{background-image:url(http://bs.baidu.com/listicon/list-num-1-38.gif)}\n" +
            "li.list-num-1-39{background-image:url(http://bs.baidu.com/listicon/list-num-1-39.gif)}\n" +
            "li.list-num-1-40{background-image:url(http://bs.baidu.com/listicon/list-num-1-40.gif)}\n" +
            "li.list-num-1-41{background-image:url(http://bs.baidu.com/listicon/list-num-1-41.gif)}\n" +
            "li.list-num-1-42{background-image:url(http://bs.baidu.com/listicon/list-num-1-42.gif)}\n" +
            "li.list-num-1-43{background-image:url(http://bs.baidu.com/listicon/list-num-1-43.gif)}\n" +
            "li.list-num-1-44{background-image:url(http://bs.baidu.com/listicon/list-num-1-44.gif)}\n" +
            "li.list-num-1-45{background-image:url(http://bs.baidu.com/listicon/list-num-1-45.gif)}\n" +
            "li.list-num-1-46{background-image:url(http://bs.baidu.com/listicon/list-num-1-46.gif)}\n" +
            "li.list-num-1-47{background-image:url(http://bs.baidu.com/listicon/list-num-1-47.gif)}\n" +
            "li.list-num-1-48{background-image:url(http://bs.baidu.com/listicon/list-num-1-48.gif)}\n" +
            "li.list-num-1-49{background-image:url(http://bs.baidu.com/listicon/list-num-1-49.gif)}\n" +
            "li.list-num-1-50{background-image:url(http://bs.baidu.com/listicon/list-num-1-50.gif)}\n" +
            "li.list-num-1-51{background-image:url(http://bs.baidu.com/listicon/list-num-1-51.gif)}\n" +
            "li.list-num-1-52{background-image:url(http://bs.baidu.com/listicon/list-num-1-52.gif)}\n" +
            "li.list-num-1-53{background-image:url(http://bs.baidu.com/listicon/list-num-1-53.gif)}\n" +
            "li.list-num-1-54{background-image:url(http://bs.baidu.com/listicon/list-num-1-54.gif)}\n" +
            "li.list-num-1-55{background-image:url(http://bs.baidu.com/listicon/list-num-1-55.gif)}\n" +
            "li.list-num-1-56{background-image:url(http://bs.baidu.com/listicon/list-num-1-56.gif)}\n" +
            "li.list-num-1-57{background-image:url(http://bs.baidu.com/listicon/list-num-1-57.gif)}\n" +
            "li.list-num-1-58{background-image:url(http://bs.baidu.com/listicon/list-num-1-58.gif)}\n" +
            "li.list-num-1-59{background-image:url(http://bs.baidu.com/listicon/list-num-1-59.gif)}\n" +
            "li.list-num-1-60{background-image:url(http://bs.baidu.com/listicon/list-num-1-60.gif)}\n" +
            "li.list-num-1-61{background-image:url(http://bs.baidu.com/listicon/list-num-1-61.gif)}\n" +
            "li.list-num-1-62{background-image:url(http://bs.baidu.com/listicon/list-num-1-62.gif)}\n" +
            "li.list-num-1-63{background-image:url(http://bs.baidu.com/listicon/list-num-1-63.gif)}\n" +
            "li.list-num-1-64{background-image:url(http://bs.baidu.com/listicon/list-num-1-64.gif)}\n" +
            "li.list-num-1-65{background-image:url(http://bs.baidu.com/listicon/list-num-1-65.gif)}\n" +
            "li.list-num-1-66{background-image:url(http://bs.baidu.com/listicon/list-num-1-66.gif)}\n" +
            "li.list-num-1-67{background-image:url(http://bs.baidu.com/listicon/list-num-1-67.gif)}\n" +
            "li.list-num-1-68{background-image:url(http://bs.baidu.com/listicon/list-num-1-68.gif)}\n" +
            "li.list-num-1-69{background-image:url(http://bs.baidu.com/listicon/list-num-1-69.gif)}\n" +
            "li.list-num-1-70{background-image:url(http://bs.baidu.com/listicon/list-num-1-70.gif)}\n" +
            "li.list-num-1-71{background-image:url(http://bs.baidu.com/listicon/list-num-1-71.gif)}\n" +
            "li.list-num-1-72{background-image:url(http://bs.baidu.com/listicon/list-num-1-72.gif)}\n" +
            "li.list-num-1-73{background-image:url(http://bs.baidu.com/listicon/list-num-1-73.gif)}\n" +
            "li.list-num-1-74{background-image:url(http://bs.baidu.com/listicon/list-num-1-74.gif)}\n" +
            "li.list-num-1-75{background-image:url(http://bs.baidu.com/listicon/list-num-1-75.gif)}\n" +
            "li.list-num-1-76{background-image:url(http://bs.baidu.com/listicon/list-num-1-76.gif)}\n" +
            "li.list-num-1-77{background-image:url(http://bs.baidu.com/listicon/list-num-1-77.gif)}\n" +
            "li.list-num-1-78{background-image:url(http://bs.baidu.com/listicon/list-num-1-78.gif)}\n" +
            "li.list-num-1-79{background-image:url(http://bs.baidu.com/listicon/list-num-1-79.gif)}\n" +
            "li.list-num-1-80{background-image:url(http://bs.baidu.com/listicon/list-num-1-80.gif)}\n" +
            "li.list-num-1-81{background-image:url(http://bs.baidu.com/listicon/list-num-1-81.gif)}\n" +
            "li.list-num-1-82{background-image:url(http://bs.baidu.com/listicon/list-num-1-82.gif)}\n" +
            "li.list-num-1-83{background-image:url(http://bs.baidu.com/listicon/list-num-1-83.gif)}\n" +
            "li.list-num-1-84{background-image:url(http://bs.baidu.com/listicon/list-num-1-84.gif)}\n" +
            "li.list-num-1-85{background-image:url(http://bs.baidu.com/listicon/list-num-1-85.gif)}\n" +
            "li.list-num-1-86{background-image:url(http://bs.baidu.com/listicon/list-num-1-86.gif)}\n" +
            "li.list-num-1-87{background-image:url(http://bs.baidu.com/listicon/list-num-1-87.gif)}\n" +
            "li.list-num-1-88{background-image:url(http://bs.baidu.com/listicon/list-num-1-88.gif)}\n" +
            "li.list-num-1-89{background-image:url(http://bs.baidu.com/listicon/list-num-1-89.gif)}\n" +
            "li.list-num-1-90{background-image:url(http://bs.baidu.com/listicon/list-num-1-90.gif)}\n" +
            "li.list-num-1-91{background-image:url(http://bs.baidu.com/listicon/list-num-1-91.gif)}\n" +
            "li.list-num-1-92{background-image:url(http://bs.baidu.com/listicon/list-num-1-92.gif)}\n" +
            "li.list-num-1-93{background-image:url(http://bs.baidu.com/listicon/list-num-1-93.gif)}\n" +
            "li.list-num-1-94{background-image:url(http://bs.baidu.com/listicon/list-num-1-94.gif)}\n" +
            "li.list-num-1-95{background-image:url(http://bs.baidu.com/listicon/list-num-1-95.gif)}\n" +
            "li.list-num-1-96{background-image:url(http://bs.baidu.com/listicon/list-num-1-96.gif)}\n" +
            "li.list-num-1-97{background-image:url(http://bs.baidu.com/listicon/list-num-1-97.gif)}\n" +
            "li.list-num-1-98{background-image:url(http://bs.baidu.com/listicon/list-num-1-98.gif)}\n" +
            "ol.custom_num{list-style:none;}ol.custom_num li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-num-paddingleft-1{padding-left:25px}\n" +
            "li.list-num-2-0{background-image:url(http://bs.baidu.com/listicon/list-num-2-0.gif)}\n" +
            "li.list-num-2-1{background-image:url(http://bs.baidu.com/listicon/list-num-2-1.gif)}\n" +
            "li.list-num-2-2{background-image:url(http://bs.baidu.com/listicon/list-num-2-2.gif)}\n" +
            "li.list-num-2-3{background-image:url(http://bs.baidu.com/listicon/list-num-2-3.gif)}\n" +
            "li.list-num-2-4{background-image:url(http://bs.baidu.com/listicon/list-num-2-4.gif)}\n" +
            "li.list-num-2-5{background-image:url(http://bs.baidu.com/listicon/list-num-2-5.gif)}\n" +
            "li.list-num-2-6{background-image:url(http://bs.baidu.com/listicon/list-num-2-6.gif)}\n" +
            "li.list-num-2-7{background-image:url(http://bs.baidu.com/listicon/list-num-2-7.gif)}\n" +
            "li.list-num-2-8{background-image:url(http://bs.baidu.com/listicon/list-num-2-8.gif)}\n" +
            "li.list-num-2-9{background-image:url(http://bs.baidu.com/listicon/list-num-2-9.gif)}\n" +
            "li.list-num-2-10{background-image:url(http://bs.baidu.com/listicon/list-num-2-10.gif)}\n" +
            "li.list-num-2-11{background-image:url(http://bs.baidu.com/listicon/list-num-2-11.gif)}\n" +
            "li.list-num-2-12{background-image:url(http://bs.baidu.com/listicon/list-num-2-12.gif)}\n" +
            "li.list-num-2-13{background-image:url(http://bs.baidu.com/listicon/list-num-2-13.gif)}\n" +
            "li.list-num-2-14{background-image:url(http://bs.baidu.com/listicon/list-num-2-14.gif)}\n" +
            "li.list-num-2-15{background-image:url(http://bs.baidu.com/listicon/list-num-2-15.gif)}\n" +
            "li.list-num-2-16{background-image:url(http://bs.baidu.com/listicon/list-num-2-16.gif)}\n" +
            "li.list-num-2-17{background-image:url(http://bs.baidu.com/listicon/list-num-2-17.gif)}\n" +
            "li.list-num-2-18{background-image:url(http://bs.baidu.com/listicon/list-num-2-18.gif)}\n" +
            "li.list-num-2-19{background-image:url(http://bs.baidu.com/listicon/list-num-2-19.gif)}\n" +
            "li.list-num-2-20{background-image:url(http://bs.baidu.com/listicon/list-num-2-20.gif)}\n" +
            "li.list-num-2-21{background-image:url(http://bs.baidu.com/listicon/list-num-2-21.gif)}\n" +
            "li.list-num-2-22{background-image:url(http://bs.baidu.com/listicon/list-num-2-22.gif)}\n" +
            "li.list-num-2-23{background-image:url(http://bs.baidu.com/listicon/list-num-2-23.gif)}\n" +
            "li.list-num-2-24{background-image:url(http://bs.baidu.com/listicon/list-num-2-24.gif)}\n" +
            "li.list-num-2-25{background-image:url(http://bs.baidu.com/listicon/list-num-2-25.gif)}\n" +
            "li.list-num-2-26{background-image:url(http://bs.baidu.com/listicon/list-num-2-26.gif)}\n" +
            "li.list-num-2-27{background-image:url(http://bs.baidu.com/listicon/list-num-2-27.gif)}\n" +
            "li.list-num-2-28{background-image:url(http://bs.baidu.com/listicon/list-num-2-28.gif)}\n" +
            "li.list-num-2-29{background-image:url(http://bs.baidu.com/listicon/list-num-2-29.gif)}\n" +
            "li.list-num-2-30{background-image:url(http://bs.baidu.com/listicon/list-num-2-30.gif)}\n" +
            "li.list-num-2-31{background-image:url(http://bs.baidu.com/listicon/list-num-2-31.gif)}\n" +
            "li.list-num-2-32{background-image:url(http://bs.baidu.com/listicon/list-num-2-32.gif)}\n" +
            "li.list-num-2-33{background-image:url(http://bs.baidu.com/listicon/list-num-2-33.gif)}\n" +
            "li.list-num-2-34{background-image:url(http://bs.baidu.com/listicon/list-num-2-34.gif)}\n" +
            "li.list-num-2-35{background-image:url(http://bs.baidu.com/listicon/list-num-2-35.gif)}\n" +
            "li.list-num-2-36{background-image:url(http://bs.baidu.com/listicon/list-num-2-36.gif)}\n" +
            "li.list-num-2-37{background-image:url(http://bs.baidu.com/listicon/list-num-2-37.gif)}\n" +
            "li.list-num-2-38{background-image:url(http://bs.baidu.com/listicon/list-num-2-38.gif)}\n" +
            "li.list-num-2-39{background-image:url(http://bs.baidu.com/listicon/list-num-2-39.gif)}\n" +
            "li.list-num-2-40{background-image:url(http://bs.baidu.com/listicon/list-num-2-40.gif)}\n" +
            "li.list-num-2-41{background-image:url(http://bs.baidu.com/listicon/list-num-2-41.gif)}\n" +
            "li.list-num-2-42{background-image:url(http://bs.baidu.com/listicon/list-num-2-42.gif)}\n" +
            "li.list-num-2-43{background-image:url(http://bs.baidu.com/listicon/list-num-2-43.gif)}\n" +
            "li.list-num-2-44{background-image:url(http://bs.baidu.com/listicon/list-num-2-44.gif)}\n" +
            "li.list-num-2-45{background-image:url(http://bs.baidu.com/listicon/list-num-2-45.gif)}\n" +
            "li.list-num-2-46{background-image:url(http://bs.baidu.com/listicon/list-num-2-46.gif)}\n" +
            "li.list-num-2-47{background-image:url(http://bs.baidu.com/listicon/list-num-2-47.gif)}\n" +
            "li.list-num-2-48{background-image:url(http://bs.baidu.com/listicon/list-num-2-48.gif)}\n" +
            "li.list-num-2-49{background-image:url(http://bs.baidu.com/listicon/list-num-2-49.gif)}\n" +
            "li.list-num-2-50{background-image:url(http://bs.baidu.com/listicon/list-num-2-50.gif)}\n" +
            "li.list-num-2-51{background-image:url(http://bs.baidu.com/listicon/list-num-2-51.gif)}\n" +
            "li.list-num-2-52{background-image:url(http://bs.baidu.com/listicon/list-num-2-52.gif)}\n" +
            "li.list-num-2-53{background-image:url(http://bs.baidu.com/listicon/list-num-2-53.gif)}\n" +
            "li.list-num-2-54{background-image:url(http://bs.baidu.com/listicon/list-num-2-54.gif)}\n" +
            "li.list-num-2-55{background-image:url(http://bs.baidu.com/listicon/list-num-2-55.gif)}\n" +
            "li.list-num-2-56{background-image:url(http://bs.baidu.com/listicon/list-num-2-56.gif)}\n" +
            "li.list-num-2-57{background-image:url(http://bs.baidu.com/listicon/list-num-2-57.gif)}\n" +
            "li.list-num-2-58{background-image:url(http://bs.baidu.com/listicon/list-num-2-58.gif)}\n" +
            "li.list-num-2-59{background-image:url(http://bs.baidu.com/listicon/list-num-2-59.gif)}\n" +
            "li.list-num-2-60{background-image:url(http://bs.baidu.com/listicon/list-num-2-60.gif)}\n" +
            "li.list-num-2-61{background-image:url(http://bs.baidu.com/listicon/list-num-2-61.gif)}\n" +
            "li.list-num-2-62{background-image:url(http://bs.baidu.com/listicon/list-num-2-62.gif)}\n" +
            "li.list-num-2-63{background-image:url(http://bs.baidu.com/listicon/list-num-2-63.gif)}\n" +
            "li.list-num-2-64{background-image:url(http://bs.baidu.com/listicon/list-num-2-64.gif)}\n" +
            "li.list-num-2-65{background-image:url(http://bs.baidu.com/listicon/list-num-2-65.gif)}\n" +
            "li.list-num-2-66{background-image:url(http://bs.baidu.com/listicon/list-num-2-66.gif)}\n" +
            "li.list-num-2-67{background-image:url(http://bs.baidu.com/listicon/list-num-2-67.gif)}\n" +
            "li.list-num-2-68{background-image:url(http://bs.baidu.com/listicon/list-num-2-68.gif)}\n" +
            "li.list-num-2-69{background-image:url(http://bs.baidu.com/listicon/list-num-2-69.gif)}\n" +
            "li.list-num-2-70{background-image:url(http://bs.baidu.com/listicon/list-num-2-70.gif)}\n" +
            "li.list-num-2-71{background-image:url(http://bs.baidu.com/listicon/list-num-2-71.gif)}\n" +
            "li.list-num-2-72{background-image:url(http://bs.baidu.com/listicon/list-num-2-72.gif)}\n" +
            "li.list-num-2-73{background-image:url(http://bs.baidu.com/listicon/list-num-2-73.gif)}\n" +
            "li.list-num-2-74{background-image:url(http://bs.baidu.com/listicon/list-num-2-74.gif)}\n" +
            "li.list-num-2-75{background-image:url(http://bs.baidu.com/listicon/list-num-2-75.gif)}\n" +
            "li.list-num-2-76{background-image:url(http://bs.baidu.com/listicon/list-num-2-76.gif)}\n" +
            "li.list-num-2-77{background-image:url(http://bs.baidu.com/listicon/list-num-2-77.gif)}\n" +
            "li.list-num-2-78{background-image:url(http://bs.baidu.com/listicon/list-num-2-78.gif)}\n" +
            "li.list-num-2-79{background-image:url(http://bs.baidu.com/listicon/list-num-2-79.gif)}\n" +
            "li.list-num-2-80{background-image:url(http://bs.baidu.com/listicon/list-num-2-80.gif)}\n" +
            "li.list-num-2-81{background-image:url(http://bs.baidu.com/listicon/list-num-2-81.gif)}\n" +
            "li.list-num-2-82{background-image:url(http://bs.baidu.com/listicon/list-num-2-82.gif)}\n" +
            "li.list-num-2-83{background-image:url(http://bs.baidu.com/listicon/list-num-2-83.gif)}\n" +
            "li.list-num-2-84{background-image:url(http://bs.baidu.com/listicon/list-num-2-84.gif)}\n" +
            "li.list-num-2-85{background-image:url(http://bs.baidu.com/listicon/list-num-2-85.gif)}\n" +
            "li.list-num-2-86{background-image:url(http://bs.baidu.com/listicon/list-num-2-86.gif)}\n" +
            "li.list-num-2-87{background-image:url(http://bs.baidu.com/listicon/list-num-2-87.gif)}\n" +
            "li.list-num-2-88{background-image:url(http://bs.baidu.com/listicon/list-num-2-88.gif)}\n" +
            "li.list-num-2-89{background-image:url(http://bs.baidu.com/listicon/list-num-2-89.gif)}\n" +
            "li.list-num-2-90{background-image:url(http://bs.baidu.com/listicon/list-num-2-90.gif)}\n" +
            "li.list-num-2-91{background-image:url(http://bs.baidu.com/listicon/list-num-2-91.gif)}\n" +
            "li.list-num-2-92{background-image:url(http://bs.baidu.com/listicon/list-num-2-92.gif)}\n" +
            "li.list-num-2-93{background-image:url(http://bs.baidu.com/listicon/list-num-2-93.gif)}\n" +
            "li.list-num-2-94{background-image:url(http://bs.baidu.com/listicon/list-num-2-94.gif)}\n" +
            "li.list-num-2-95{background-image:url(http://bs.baidu.com/listicon/list-num-2-95.gif)}\n" +
            "li.list-num-2-96{background-image:url(http://bs.baidu.com/listicon/list-num-2-96.gif)}\n" +
            "li.list-num-2-97{background-image:url(http://bs.baidu.com/listicon/list-num-2-97.gif)}\n" +
            "li.list-num-2-98{background-image:url(http://bs.baidu.com/listicon/list-num-2-98.gif)}\n" +
            "ol.custom_num1{list-style:none;}ol.custom_num1 li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-num1-paddingleft-1{padding-left:25px}\n" +
            "li.list-num-3-0{background-image:url(http://bs.baidu.com/listicon/list-num-3-0.gif)}\n" +
            "li.list-num-3-1{background-image:url(http://bs.baidu.com/listicon/list-num-3-1.gif)}\n" +
            "li.list-num-3-2{background-image:url(http://bs.baidu.com/listicon/list-num-3-2.gif)}\n" +
            "li.list-num-3-3{background-image:url(http://bs.baidu.com/listicon/list-num-3-3.gif)}\n" +
            "li.list-num-3-4{background-image:url(http://bs.baidu.com/listicon/list-num-3-4.gif)}\n" +
            "li.list-num-3-5{background-image:url(http://bs.baidu.com/listicon/list-num-3-5.gif)}\n" +
            "li.list-num-3-6{background-image:url(http://bs.baidu.com/listicon/list-num-3-6.gif)}\n" +
            "li.list-num-3-7{background-image:url(http://bs.baidu.com/listicon/list-num-3-7.gif)}\n" +
            "li.list-num-3-8{background-image:url(http://bs.baidu.com/listicon/list-num-3-8.gif)}\n" +
            "li.list-num-3-9{background-image:url(http://bs.baidu.com/listicon/list-num-3-9.gif)}\n" +
            "li.list-num-3-10{background-image:url(http://bs.baidu.com/listicon/list-num-3-10.gif)}\n" +
            "li.list-num-3-11{background-image:url(http://bs.baidu.com/listicon/list-num-3-11.gif)}\n" +
            "li.list-num-3-12{background-image:url(http://bs.baidu.com/listicon/list-num-3-12.gif)}\n" +
            "li.list-num-3-13{background-image:url(http://bs.baidu.com/listicon/list-num-3-13.gif)}\n" +
            "li.list-num-3-14{background-image:url(http://bs.baidu.com/listicon/list-num-3-14.gif)}\n" +
            "li.list-num-3-15{background-image:url(http://bs.baidu.com/listicon/list-num-3-15.gif)}\n" +
            "li.list-num-3-16{background-image:url(http://bs.baidu.com/listicon/list-num-3-16.gif)}\n" +
            "li.list-num-3-17{background-image:url(http://bs.baidu.com/listicon/list-num-3-17.gif)}\n" +
            "li.list-num-3-18{background-image:url(http://bs.baidu.com/listicon/list-num-3-18.gif)}\n" +
            "li.list-num-3-19{background-image:url(http://bs.baidu.com/listicon/list-num-3-19.gif)}\n" +
            "li.list-num-3-20{background-image:url(http://bs.baidu.com/listicon/list-num-3-20.gif)}\n" +
            "li.list-num-3-21{background-image:url(http://bs.baidu.com/listicon/list-num-3-21.gif)}\n" +
            "li.list-num-3-22{background-image:url(http://bs.baidu.com/listicon/list-num-3-22.gif)}\n" +
            "li.list-num-3-23{background-image:url(http://bs.baidu.com/listicon/list-num-3-23.gif)}\n" +
            "li.list-num-3-24{background-image:url(http://bs.baidu.com/listicon/list-num-3-24.gif)}\n" +
            "li.list-num-3-25{background-image:url(http://bs.baidu.com/listicon/list-num-3-25.gif)}\n" +
            "li.list-num-3-26{background-image:url(http://bs.baidu.com/listicon/list-num-3-26.gif)}\n" +
            "li.list-num-3-27{background-image:url(http://bs.baidu.com/listicon/list-num-3-27.gif)}\n" +
            "li.list-num-3-28{background-image:url(http://bs.baidu.com/listicon/list-num-3-28.gif)}\n" +
            "li.list-num-3-29{background-image:url(http://bs.baidu.com/listicon/list-num-3-29.gif)}\n" +
            "li.list-num-3-30{background-image:url(http://bs.baidu.com/listicon/list-num-3-30.gif)}\n" +
            "li.list-num-3-31{background-image:url(http://bs.baidu.com/listicon/list-num-3-31.gif)}\n" +
            "li.list-num-3-32{background-image:url(http://bs.baidu.com/listicon/list-num-3-32.gif)}\n" +
            "li.list-num-3-33{background-image:url(http://bs.baidu.com/listicon/list-num-3-33.gif)}\n" +
            "li.list-num-3-34{background-image:url(http://bs.baidu.com/listicon/list-num-3-34.gif)}\n" +
            "li.list-num-3-35{background-image:url(http://bs.baidu.com/listicon/list-num-3-35.gif)}\n" +
            "li.list-num-3-36{background-image:url(http://bs.baidu.com/listicon/list-num-3-36.gif)}\n" +
            "li.list-num-3-37{background-image:url(http://bs.baidu.com/listicon/list-num-3-37.gif)}\n" +
            "li.list-num-3-38{background-image:url(http://bs.baidu.com/listicon/list-num-3-38.gif)}\n" +
            "li.list-num-3-39{background-image:url(http://bs.baidu.com/listicon/list-num-3-39.gif)}\n" +
            "li.list-num-3-40{background-image:url(http://bs.baidu.com/listicon/list-num-3-40.gif)}\n" +
            "li.list-num-3-41{background-image:url(http://bs.baidu.com/listicon/list-num-3-41.gif)}\n" +
            "li.list-num-3-42{background-image:url(http://bs.baidu.com/listicon/list-num-3-42.gif)}\n" +
            "li.list-num-3-43{background-image:url(http://bs.baidu.com/listicon/list-num-3-43.gif)}\n" +
            "li.list-num-3-44{background-image:url(http://bs.baidu.com/listicon/list-num-3-44.gif)}\n" +
            "li.list-num-3-45{background-image:url(http://bs.baidu.com/listicon/list-num-3-45.gif)}\n" +
            "li.list-num-3-46{background-image:url(http://bs.baidu.com/listicon/list-num-3-46.gif)}\n" +
            "li.list-num-3-47{background-image:url(http://bs.baidu.com/listicon/list-num-3-47.gif)}\n" +
            "li.list-num-3-48{background-image:url(http://bs.baidu.com/listicon/list-num-3-48.gif)}\n" +
            "li.list-num-3-49{background-image:url(http://bs.baidu.com/listicon/list-num-3-49.gif)}\n" +
            "li.list-num-3-50{background-image:url(http://bs.baidu.com/listicon/list-num-3-50.gif)}\n" +
            "li.list-num-3-51{background-image:url(http://bs.baidu.com/listicon/list-num-3-51.gif)}\n" +
            "li.list-num-3-52{background-image:url(http://bs.baidu.com/listicon/list-num-3-52.gif)}\n" +
            "li.list-num-3-53{background-image:url(http://bs.baidu.com/listicon/list-num-3-53.gif)}\n" +
            "li.list-num-3-54{background-image:url(http://bs.baidu.com/listicon/list-num-3-54.gif)}\n" +
            "li.list-num-3-55{background-image:url(http://bs.baidu.com/listicon/list-num-3-55.gif)}\n" +
            "li.list-num-3-56{background-image:url(http://bs.baidu.com/listicon/list-num-3-56.gif)}\n" +
            "li.list-num-3-57{background-image:url(http://bs.baidu.com/listicon/list-num-3-57.gif)}\n" +
            "li.list-num-3-58{background-image:url(http://bs.baidu.com/listicon/list-num-3-58.gif)}\n" +
            "li.list-num-3-59{background-image:url(http://bs.baidu.com/listicon/list-num-3-59.gif)}\n" +
            "li.list-num-3-60{background-image:url(http://bs.baidu.com/listicon/list-num-3-60.gif)}\n" +
            "li.list-num-3-61{background-image:url(http://bs.baidu.com/listicon/list-num-3-61.gif)}\n" +
            "li.list-num-3-62{background-image:url(http://bs.baidu.com/listicon/list-num-3-62.gif)}\n" +
            "li.list-num-3-63{background-image:url(http://bs.baidu.com/listicon/list-num-3-63.gif)}\n" +
            "li.list-num-3-64{background-image:url(http://bs.baidu.com/listicon/list-num-3-64.gif)}\n" +
            "li.list-num-3-65{background-image:url(http://bs.baidu.com/listicon/list-num-3-65.gif)}\n" +
            "li.list-num-3-66{background-image:url(http://bs.baidu.com/listicon/list-num-3-66.gif)}\n" +
            "li.list-num-3-67{background-image:url(http://bs.baidu.com/listicon/list-num-3-67.gif)}\n" +
            "li.list-num-3-68{background-image:url(http://bs.baidu.com/listicon/list-num-3-68.gif)}\n" +
            "li.list-num-3-69{background-image:url(http://bs.baidu.com/listicon/list-num-3-69.gif)}\n" +
            "li.list-num-3-70{background-image:url(http://bs.baidu.com/listicon/list-num-3-70.gif)}\n" +
            "li.list-num-3-71{background-image:url(http://bs.baidu.com/listicon/list-num-3-71.gif)}\n" +
            "li.list-num-3-72{background-image:url(http://bs.baidu.com/listicon/list-num-3-72.gif)}\n" +
            "li.list-num-3-73{background-image:url(http://bs.baidu.com/listicon/list-num-3-73.gif)}\n" +
            "li.list-num-3-74{background-image:url(http://bs.baidu.com/listicon/list-num-3-74.gif)}\n" +
            "li.list-num-3-75{background-image:url(http://bs.baidu.com/listicon/list-num-3-75.gif)}\n" +
            "li.list-num-3-76{background-image:url(http://bs.baidu.com/listicon/list-num-3-76.gif)}\n" +
            "li.list-num-3-77{background-image:url(http://bs.baidu.com/listicon/list-num-3-77.gif)}\n" +
            "li.list-num-3-78{background-image:url(http://bs.baidu.com/listicon/list-num-3-78.gif)}\n" +
            "li.list-num-3-79{background-image:url(http://bs.baidu.com/listicon/list-num-3-79.gif)}\n" +
            "li.list-num-3-80{background-image:url(http://bs.baidu.com/listicon/list-num-3-80.gif)}\n" +
            "li.list-num-3-81{background-image:url(http://bs.baidu.com/listicon/list-num-3-81.gif)}\n" +
            "li.list-num-3-82{background-image:url(http://bs.baidu.com/listicon/list-num-3-82.gif)}\n" +
            "li.list-num-3-83{background-image:url(http://bs.baidu.com/listicon/list-num-3-83.gif)}\n" +
            "li.list-num-3-84{background-image:url(http://bs.baidu.com/listicon/list-num-3-84.gif)}\n" +
            "li.list-num-3-85{background-image:url(http://bs.baidu.com/listicon/list-num-3-85.gif)}\n" +
            "li.list-num-3-86{background-image:url(http://bs.baidu.com/listicon/list-num-3-86.gif)}\n" +
            "li.list-num-3-87{background-image:url(http://bs.baidu.com/listicon/list-num-3-87.gif)}\n" +
            "li.list-num-3-88{background-image:url(http://bs.baidu.com/listicon/list-num-3-88.gif)}\n" +
            "li.list-num-3-89{background-image:url(http://bs.baidu.com/listicon/list-num-3-89.gif)}\n" +
            "li.list-num-3-90{background-image:url(http://bs.baidu.com/listicon/list-num-3-90.gif)}\n" +
            "li.list-num-3-91{background-image:url(http://bs.baidu.com/listicon/list-num-3-91.gif)}\n" +
            "li.list-num-3-92{background-image:url(http://bs.baidu.com/listicon/list-num-3-92.gif)}\n" +
            "li.list-num-3-93{background-image:url(http://bs.baidu.com/listicon/list-num-3-93.gif)}\n" +
            "li.list-num-3-94{background-image:url(http://bs.baidu.com/listicon/list-num-3-94.gif)}\n" +
            "li.list-num-3-95{background-image:url(http://bs.baidu.com/listicon/list-num-3-95.gif)}\n" +
            "li.list-num-3-96{background-image:url(http://bs.baidu.com/listicon/list-num-3-96.gif)}\n" +
            "li.list-num-3-97{background-image:url(http://bs.baidu.com/listicon/list-num-3-97.gif)}\n" +
            "li.list-num-3-98{background-image:url(http://bs.baidu.com/listicon/list-num-3-98.gif)}\n" +
            "ol.custom_num2{list-style:none;}ol.custom_num2 li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-num2-paddingleft-1{padding-left:35px}\n" +
            "li.list-num2-paddingleft-2{padding-left:40px}\n" +
            "li.list-dash{background-image:url(http://bs.baidu.com/listicon/dash.gif)}\n" +
            "ul.custom_dash{list-style:none;}ul.custom_dash li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-dash-paddingleft{padding-left:35px}\n" +
            "li.list-dot{background-image:url(http://bs.baidu.com/listicon/dot.gif)}\n" +
            "ul.custom_dot{list-style:none;}ul.custom_dot li{background-position:0 3px;background-repeat:no-repeat}\n" +
            "li.list-dot-paddingleft{padding-left:20px}\n" +
            ".list-paddingleft-1{padding-left:0}\n" +
            ".list-paddingleft-2{padding-left:30px}\n" +
            ".list-paddingleft-3{padding-left:60px}</style><style id=\"pagebreak\">.pagebreak{display:block;clear:both !important;cursor:default !important;width: 100% !important;margin:0;}</style><style id=\"pre\">pre{margin:.5em 0;padding:.4em .6em;border-radius:8px;background:#f8f8f8;}</style><style id=\"loading\">.loadingclass{display:inline-block;cursor:default;background: url('http://10.138.60.102:10080/ams/WebContent/ueditor1.4.3.3/themes/default/images/loading.gif') no-repeat center center transparent;border:1px solid #cccccc;margin-left:1px;height: 22px;width: 22px;}\n" +
            ".loaderrorclass{display:inline-block;cursor:default;background: url('http://10.138.60.102:10080/ams/WebContent/ueditor1.4.3.3/themes/default/images/loaderror.png') no-repeat center center transparent;border:1px solid #cccccc;margin-right:1px;height: 22px;width: 22px;}</style><style id=\"anchor\">.anchorclass{background: url('http://10.138.60.102:10080/ams/WebContent/ueditor1.4.3.3/themes/default/images/anchor.gif') no-repeat scroll left center transparent;cursor: auto;display: inline-block;height: 16px;width: 15px;}</style><style type=\"text/css\">body{background-color:#ffffff; background-image:; background-repeat:repeat; background-position:0% 0%; height: 1181px}</style> </head><body ><p><img src=\"http://10.138.60.102:10080/ams/webapp/ueditor/loadPoto?picUrl=/ueditor/20170309/1489054199224035530.png\" title=\"1489054199224035530.png\" alt=\"blob.png\"/></p><p><img src=\"http://10.138.60.102:10080/ams/webapp/ueditor/loadPoto?picUrl=/ueditor/20170309/1489054217830093580.png\" title=\"1489054217830093580.png\" alt=\"blob.png\"/></p><p><img src=\"http://10.138.60.102:10080/ams/webapp/ueditor/loadPoto?picUrl=/ueditor/20170309/1489054242602054606.png\" title=\"1489054242602054606.png\" alt=\"blob.png\"/></p></body></html>";
    private ActivityTopicsDetailBinding mDataBinding;

    @JavascriptInterface
    public void resize(final float height) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int webviewHeight = mDataBinding.wvWebView.getHeight();
                int webViewContentHeight = mDataBinding.wvWebView.getContentHeight();
                Log.i("WebView", String.format("webView geight %s, webView content height %s, hright %s", webviewHeight, webViewContentHeight, height));
                //mDataBinding.wvWebView.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, Math.max(webviewHeight, webViewContentHeight) * getResources().getDisplayMetrics().densityDpi));
            }
        });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_topics_detail);
        final WebView wvWebView = mDataBinding.wvWebView;
        wvWebView.setHorizontalScrollBarEnabled(false);
        wvWebView.setWebChromeClient(new WebChromeClient());
        wvWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.cancel();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:Android.resize(document.body.scrollHeight)");
                super.onPageFinished(view, url);
            }
        });
        WebSettings settings = wvWebView.getSettings();
        settings.setSupportZoom(false);
        settings.setDefaultFontSize(14);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        wvWebView.addJavascriptInterface(this, "Android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wvWebView.addJavascriptInterface(new OnWebViewImageListener() {
                @Override
                @JavascriptInterface
                public void showImagePreview(String bigImageUrl) {
                    if (!TextUtils.isEmpty(bigImageUrl)) {

                        Toast.makeText(TopicsDetailActivity.this, "click html img", Toast.LENGTH_SHORT).show();
                    }
                }
            }, "mWebViewImageListener");
        }
        //String content = setupWebContent(CONTENT, true, true, "");
        String regEx_style = "<style[^>]+id=\"([list]+)\"[^>]*>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(CONTENT);
        String htmlStr = m_style.replaceAll(""); //过滤style标签
        wvWebView.loadDataWithBaseURL("", htmlStr, "text/html", "UTF-8", "");
        //wvWebView.loadDataWithBaseURL("", "<html><img src=\"https://ww3.sinaimg.cn/large/006tNbRwgy1fdhug16dnhj30km0b4glu.jpg\"/></html>", "text/html", "UTF-8", "");

        /*wvWebView.loadDataWithBaseURL(null, , "text/html", "uft-8", null);*/
    }

    private static String setupWebContent(String content, boolean isShowHighlight,
                                          boolean isShowImagePreview, String css) {
        if (isEmpty(content) || isEmpty(content.trim())) return "";

        // 读取用户设置：是否加载文章图片--默认有wifi下始终加载图片
        //if (AppContext.get(AppConfig.KEY_LOAD_IMAGE, true)
        //        || TDevice.isWifiOpen()) {
        // 过滤掉 img标签的width,height属性
        content = content.replaceAll("(<img[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
        content = content.replaceAll("(<img[^>]*?)\\s+height\\s*=\\s*\\S+", "$1");

        // 添加点击图片放大支持
        if (isShowImagePreview) {
            // TODO 用一个正则就搞定??
            content = content.replaceAll("<img[^>]+src=\"([^\"\'\\s]+)\"[^>]*>",
                    "<img src=\"$1\" onClick=\"javascript:mWebViewImageListener.showImagePreview('$1')\"/>");
            content = content.replaceAll(
                    "<a\\s+[^<>]*href=[\"\']([^\"\']+)[\"\'][^<>]*>\\s*<img\\s+src=\"([^\"\']+)\"[^<>]*/>\\s*</a>",
                    "<a href=\"$1\"><img src=\"$2\"/></a>");
        }
        //} else {
        //    // 过滤掉 img标签
        //    content = content.replaceAll("<\\s*img\\s+([^>]*)\\s*/>", "");
        //}

        // 过滤table的内部属性
        content = content.replaceAll("(<table[^>]*?)\\s+border\\s*=\\s*\\S+", "$1");
        content = content.replaceAll("(<table[^>]*?)\\s+cellspacing\\s*=\\s*\\S+", "$1");
        content = content.replaceAll("(<table[^>]*?)\\s+cellpadding\\s*=\\s*\\S+", "$1");

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
}
