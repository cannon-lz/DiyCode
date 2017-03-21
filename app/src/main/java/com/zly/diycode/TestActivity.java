package com.zly.diycode;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.zly.diycode.databinding.ItemReplyBinding;
import com.zly.diycode.http.Art;
import com.zly.diycode.http.UserApi;
import com.zly.diycode.widget.AppWebView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangluya on 2017/3/17.
 */

public class TestActivity extends AppCompatActivity {

    public static final String content = "<!DOCTYPE html><html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/html/css/front.css\"><script type=\"text/javascript\" src=\"file:///android_asset/html/js/d3.min.js\"></script></head><body data-controller-name=\"topics\"><div class=\"row\"><div class=\"col-md-9\"><div class=\"topic-detail panel panel-default\"><div class=\"panel-body markdown\"><article><h2 id=\"写在前面\">写在前面</h2>\n" +
            "<blockquote>\n" +
            "<p>我们的目标是 No ViewHolder and No Adapter.</p>\n" +
            "</blockquote>\n" +
            "\n" +
            "<p>官方的<code>databinding</code>的确十分厉害，各种<code>xml</code>绑定，然后自动生成一波文件，各种吊的飞起，不过容易让人抓不住重点。为了加深理解，我写了这个<code>纯java版</code>的<code>databindng</code>, 不需要<code>xml</code>各种配置<code>android:text=\"@{...}\"</code>，同时进一步加了绑定<code>Adapter</code>。</p>\n" +
            "\n" +
            "<blockquote>\n" +
            "<p>时间仓促，只粗略的实现了小部分功能。基于注解的性能也有待优化，但它已经极大地提升了我的开发效率。觉得它不错的话，可以一起维护这个项目，向<code>No ViewHolder</code>的目标迈进~</p>\n" +
            "</blockquote>\n" +
            "<h2 id=\"预览\">预览</h2><h3 id=\"常规的电商首页\">常规的电商首页</h3>\n" +
            "<p><img src=\"https://user-gold-cdn.xitu.io/2017/3/16/16009283e2dc665b39f99d36d41d0ce7\" onClick=\"javascript:mWebViewImageListener.showImagePreview('https://user-gold-cdn.xitu.io/2017/3/16/16009283e2dc665b39f99d36d41d0ce7')\"/></p>\n" +
            "<h3 id=\"所需代码量\">所需代码量</h3>\n" +
            "<p>实现这样一个带<code>Header</code>, 带<code>上拉加载</code>的列表需要多少代码呢？<br>\n" +
            "- 两个无聊的 javabean<br>\n" +
            "- 一个轮播控件<br>\n" +
            "- 一个Activity（真的不含 Adapter 啊）<br>\n" +
            "- 然后没有然后了。。。</p>\n" +
            "\n" +
            "<p><img src=\"https://user-gold-cdn.xitu.io/2017/3/16/4e820744d74e0c6e9f5076638d122d29\" onClick=\"javascript:mWebViewImageListener.showImagePreview('https://user-gold-cdn.xitu.io/2017/3/16/4e820744d74e0c6e9f5076638d122d29')\"/></p>\n" +
            "<h2 id=\"特性\">特性</h2>\n" +
            "<ul>\n" +
            "<li>\n" +
            "<code>data -&gt; view</code>的单向绑定</li>\n" +
            "<li>支持常用控件的绑定，同时增加了官方没有的<code>Adapter</code>绑定。支持<code>Header</code>和<code>上拉加载</code>\n" +
            "</li>\n" +
            "<li>代码极其简洁, 无需实例化<code>View</code>, 也没有<code>Adapter</code>, 连<code>ViewHoler</code> 也没有。。。</li>\n" +
            "<li>支持绑定行为的自定义</li>\n" +
            "<li>配合 Rxjava + Lambda 简直上天</li>\n" +
            "</ul>\n" +
            "<h2 id=\"源码\">源码</h2>\n" +
            "<p><a href=\"https://github.com/fashare2015/NoViewHolder\" rel=\"nofollow\" target=\"_blank\">https://github.com/fashare2015/NoViewHolder</a></p>\n" +
            "<h2 id=\"gralde 依赖\">gralde 依赖</h2><pre class=\"highlight plaintext\"><code>// 1. Add it in your root build.gradle at the end of repositories:\n" +
            "allprojects {\n" +
            "    repositories {\n" +
            "        ...\n" +
            "        maven { url 'https://jitpack.io' }\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "// 2. Add the dependency in your app/build.gradle\n" +
            "dependencies {\n" +
            "        compile 'com.github.fashare2015:NoViewHolder:1.0.1'\n" +
            "}</code></pre>\n" +
            "<h2 id=\"绑定 Data 和 View\">绑定 Data 和 View</h2>\n" +
            "<p>这一块和官方差不多，只是<code>xml</code>配置换成了<code>java注解</code>配置。</p>\n" +
            "<h2 id=\"绑定单个 View\">绑定单个 View</h2>\n" +
            "<p>首先，你手头有一个<code>javabean</code>，就是你在图中看到的<code>妹子列表Item</code>如：</p>\n" +
            "<pre class=\"highlight java\"><code><span class=\"kd\">public</span> <span class=\"kd\">class</span> <span class=\"nc\">MeiZhi</span> <span class=\"o\">{</span>\n" +
            "    <span class=\"nd\">@BindImageView</span><span class=\"o\">(</span><span class=\"n\">id</span><span class=\"o\">=</span><span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">iv_image</span><span class=\"o\">,</span> <span class=\"n\">placeHolder</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">mipmap</span><span class=\"o\">.</span><span class=\"na\">ic_launcher</span><span class=\"o\">)</span>\n" +
            "    <span class=\"kd\">public</span> <span class=\"n\">String</span> <span class=\"n\">url</span><span class=\"o\">;</span>  <span class=\"c1\">// 把 url 绑定在 ImageView 上</span>\n" +
            "    <span class=\"nd\">@BindTextView</span><span class=\"o\">(</span><span class=\"n\">id</span><span class=\"o\">=</span><span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">tv_title</span><span class=\"o\">)</span>\n" +
            "    <span class=\"kd\">public</span> <span class=\"n\">String</span> <span class=\"n\">desc</span><span class=\"o\">;</span> <span class=\"c1\">// 把 desc 绑定在 TextView 上</span>\n" +
            "<span class=\"o\">}</span></code></pre>\n" +
            "\n" +
            "<p>基本等同于官方的<code>android:text=\"@{meizhi.desc}\"</code>，用过<code>databinding</code>的话应该秒懂的。。。</p>\n" +
            "<h2 id=\"绑定列表\">绑定列表</h2>\n" +
            "<p>当然，服务端返回的肯定是个妹子的列表，你手头还会有一个<code>HomeInfo</code>的东东。</p>\n" +
            "<pre class=\"highlight java\"><code><span class=\"kd\">public</span> <span class=\"kd\">class</span> <span class=\"nc\">HomeInfo</span> <span class=\"o\">{</span>\n" +
            "    <span class=\"c1\">// 妹子列表区</span>\n" +
            "    <span class=\"nd\">@BindRecyclerView</span><span class=\"o\">(</span><span class=\"n\">id</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">rv_meizhi</span><span class=\"o\">,</span> <span class=\"n\">layout</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">layout</span><span class=\"o\">.</span><span class=\"na\">item_meizhi</span><span class=\"o\">)</span>\n" +
            "    <span class=\"kd\">private</span> <span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">MeiZhi</span><span class=\"o\">&gt;</span> <span class=\"n\">results</span> <span class=\"o\">=</span> <span class=\"k\">new</span> <span class=\"n\">ArrayList</span><span class=\"o\">&lt;&gt;();</span>   <span class=\"c1\">// 把 List 绑定在 RecyclerView 上</span>\n" +
            "\n" +
            "    <span class=\"c1\">// banner</span>\n" +
            "    <span class=\"nd\">@BindViewPager</span><span class=\"o\">(</span><span class=\"n\">id</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">vp_banner</span><span class=\"o\">,</span> <span class=\"n\">layout</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">layout</span><span class=\"o\">.</span><span class=\"na\">item_banner</span><span class=\"o\">)</span>\n" +
            "    <span class=\"kd\">private</span> <span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">MeiZhi</span><span class=\"o\">&gt;</span> <span class=\"n\">bannerInfo</span><span class=\"o\">;</span>    <span class=\"c1\">// 把 List 绑定在 ViewPager 上</span>\n" +
            "<span class=\"o\">}</span></code></pre>\n" +
            "\n" +
            "<p>这部分是官方没有的，相应的还提供了 <code>@BindListView</code></p>\n" +
            "<h2 id=\"绑定 header\">绑定 header</h2>\n" +
            "<p>像上面的配置，<code>banner</code>和妹子列表是分开的，不会一起滑动的。因此，提供了向<code>RecyclerView</code>中添加<code>Header</code>的注解——<code>@BindRvHeader</code>.<br>\n" +
            "让我们把<code>banner</code>加进<code>RecyclerView</code></p>\n" +
            "<pre class=\"highlight java\"><code><span class=\"kd\">public</span> <span class=\"kd\">class</span> <span class=\"nc\">HomeInfo</span> <span class=\"o\">{</span>\n" +
            "    <span class=\"c1\">// 妹子列表区</span>\n" +
            "    <span class=\"nd\">@BindRecyclerView</span><span class=\"o\">(</span><span class=\"n\">id</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">rv_meizhi</span><span class=\"o\">,</span> <span class=\"n\">layout</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">layout</span><span class=\"o\">.</span><span class=\"na\">item_meizhi</span><span class=\"o\">)</span>\n" +
            "    <span class=\"kd\">private</span> <span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">MeiZhi</span><span class=\"o\">&gt;</span> <span class=\"n\">results</span> <span class=\"o\">=</span> <span class=\"k\">new</span> <span class=\"n\">ArrayList</span><span class=\"o\">&lt;&gt;();</span>   <span class=\"c1\">// 把 List 绑定在 RecyclerView 上</span>\n" +
            "\n" +
            "    <span class=\"c1\">// banner</span>\n" +
            "    <span class=\"nd\">@BindRvHeader</span><span class=\"o\">(</span><span class=\"n\">id</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">rv_meizhi</span><span class=\"o\">,</span> <span class=\"n\">layout</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">layout</span><span class=\"o\">.</span><span class=\"na\">layout_banner</span><span class=\"o\">,</span> <span class=\"n\">itemType</span> <span class=\"o\">=</span> <span class=\"mi\">0</span><span class=\"o\">)</span> <span class=\"c1\">// 增加这一行 !!!</span>\n" +
            "    <span class=\"nd\">@BindViewPager</span><span class=\"o\">(</span><span class=\"n\">id</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">vp_banner</span><span class=\"o\">,</span> <span class=\"n\">layout</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">layout</span><span class=\"o\">.</span><span class=\"na\">item_banner</span><span class=\"o\">)</span>\n" +
            "    <span class=\"kd\">private</span> <span class=\"n\">List</span><span class=\"o\">&lt;</span><span class=\"n\">MeiZhi</span><span class=\"o\">&gt;</span> <span class=\"n\">bannerInfo</span><span class=\"o\">;</span>    <span class=\"c1\">// 把 List 绑定在 ViewPager 上</span>\n" +
            "<span class=\"o\">}</span></code></pre>\n" +
            "<h2 id=\"绑定点击事件\">绑定点击事件</h2>\n" +
            "<p>提供了<code>@BindItemClick</code>、<code>@BindClick</code></p>\n" +
            "<pre class=\"highlight java\"><code><span class=\"kd\">public</span> <span class=\"kd\">class</span> <span class=\"nc\">MainActivity</span> <span class=\"kd\">extends</span> <span class=\"n\">AppCompatActivity</span> <span class=\"o\">{</span>\n" +
            "    <span class=\"o\">...</span>\n" +
            "    <span class=\"nd\">@BindItemClick</span><span class=\"o\">(</span><span class=\"n\">id</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">vp_banner</span><span class=\"o\">)</span>\n" +
            "    <span class=\"n\">NoOnItemClickListener</span><span class=\"o\">&lt;</span><span class=\"n\">MeiZhi</span><span class=\"o\">&gt;</span> <span class=\"n\">clickBanner</span> <span class=\"o\">=</span> <span class=\"o\">(</span><span class=\"n\">view</span><span class=\"o\">,</span> <span class=\"n\">data</span><span class=\"o\">,</span> <span class=\"n\">pos</span><span class=\"o\">)</span> <span class=\"o\">-&gt;</span> <span class=\"n\">toast</span><span class=\"o\">(</span><span class=\"s\">\"click Banner: \"</span> <span class=\"o\">+</span> <span class=\"n\">pos</span> <span class=\"o\">+</span> <span class=\"s\">\", \"</span><span class=\"o\">+</span> <span class=\"n\">data</span><span class=\"o\">.</span><span class=\"na\">toString</span><span class=\"o\">());</span>\n" +
            "\n" +
            "    <span class=\"nd\">@BindItemClick</span><span class=\"o\">(</span><span class=\"n\">id</span> <span class=\"o\">=</span> <span class=\"n\">R</span><span class=\"o\">.</span><span class=\"na\">id</span><span class=\"o\">.</span><span class=\"na\">rv_meizhi</span><span class=\"o\">)</span>\n" +
            "    <span class=\"n\">NoOnItemClickListener</span><span class=\"o\">&lt;</span><span class=\"n\">MeiZhi</span><span class=\"o\">&gt;</span> <span class=\"n\">clickMeiZhi</span> <span class=\"o\">=</span> <span class=\"o\">(</span><span class=\"n\">view</span><span class=\"o\">,</span> <span class=\"n\">data</span><span class=\"o\">,</span> <span class=\"n\">pos</span><span class=\"o\">)</span> <span class=\"o\">-&gt;</span> <span class=\"n\">toast</span><span class=\"o\">(</span><span class=\"s\">\"click MeiZhi: \"</span> <span class=\"o\">+</span> <span class=\"n\">pos</span> <span class=\"o\">+</span> <span class=\"s\">\", \"</span><span class=\"o\">+</span> <span class=\"n\">data</span><span class=\"o\">.</span><span class=\"na\">toString</span><span class=\"o\">());</span>\n" +
            "<span class=\"o\">}</span></code></pre>\n" +
            "<h2 id=\"更新 UI\">更新 UI</h2>\n" +
            "<p>前面只是一系列绑定关系的配置，还需要一个接口触发他们：<br>\n" +
            "- 初始化：根据 R.id.XXX 初始化相应的 View 和 Adapter，为后续<code>更新UI</code>做准备</p>\n" +
            "<pre class=\"highlight java\"><code><span class=\"n\">mNoViewHolder</span> <span class=\"o\">=</span> <span class=\"k\">new</span> <span class=\"n\">NoViewHolder</span><span class=\"o\">.</span><span class=\"na\">Builder</span><span class=\"o\">(</span><span class=\"k\">this</span><span class=\"o\">)</span>\n" +
            "                <span class=\"o\">.</span><span class=\"na\">initView</span><span class=\"o\">(</span><span class=\"k\">new</span> <span class=\"n\">HomeInfo</span><span class=\"o\">())</span> <span class=\"c1\">// 一定要提供`注解信息`的类，否则无法初始化。</span>\n" +
            "                <span class=\"o\">.</span><span class=\"na\">build</span><span class=\"o\">();</span></code></pre>\n" +
            "\n" +
            "<ul>\n" +
            "<li>更新UI: <code>mNoViewHolder.notifyDataSetChanged(homeInfo);</code>\n" +
            "自动根据 <code>homeInfo</code> 里提供的注解信息，找到相应的控件，并把数据刷新上去。</li>\n" +
            "</ul>\n" +
            "<pre class=\"highlight java\"><code><span class=\"c1\">// 在请求的 onSuccess() 中刷新界面，本例使用了 Rxjava 和 lambda</span>\n" +
            "<span class=\"n\">homeInfoObservable</span><span class=\"o\">.</span><span class=\"na\">subscribe</span><span class=\"o\">(</span><span class=\"n\">homeInfo</span> <span class=\"o\">-&gt;</span> <span class=\"o\">{</span>\n" +
            "        <span class=\"n\">mHomeInfo</span><span class=\"o\">.</span><span class=\"na\">getResults</span><span class=\"o\">().</span><span class=\"na\">addAll</span><span class=\"o\">(</span><span class=\"n\">homeInfo</span><span class=\"o\">.</span><span class=\"na\">getResults</span><span class=\"o\">());</span>           <span class=\"c1\">// 更新 妹子列表 info</span>\n" +
            "        <span class=\"k\">if</span><span class=\"o\">(</span><span class=\"n\">homeInfo</span><span class=\"o\">.</span><span class=\"na\">getResults</span><span class=\"o\">().</span><span class=\"na\">size</span><span class=\"o\">()</span> <span class=\"o\">&gt;=</span> <span class=\"mi\">6</span><span class=\"o\">)</span>\n" +
            "            <span class=\"n\">mHomeInfo</span><span class=\"o\">.</span><span class=\"na\">setBannerInfo</span><span class=\"o\">(</span><span class=\"n\">homeInfo</span><span class=\"o\">.</span><span class=\"na\">getResults</span><span class=\"o\">().</span><span class=\"na\">subList</span><span class=\"o\">(</span><span class=\"mi\">0</span><span class=\"o\">,</span> <span class=\"mi\">6</span><span class=\"o\">));</span>   <span class=\"c1\">// 更新 bannerInfo</span>\n" +
            "\n" +
            "        <span class=\"n\">mNoViewHolder</span><span class=\"o\">.</span><span class=\"na\">notifyDataSetChanged</span><span class=\"o\">(</span><span class=\"n\">mHomeInfo</span><span class=\"o\">);</span>  <span class=\"c1\">// mHomeInfo 发生变化, 通知 UI 及时刷新</span>\n" +
            "<span class=\"o\">}</span></code></pre>\n" +
            "<h2 id=\"全局配置——自定义行为\">全局配置——自定义行为</h2>\n" +
            "<p>当你需要自定义的时候 (比如替换图片加载库，默认<code>Glide</code>)。可以这样：<br>\n" +
            "如下，即把<code>@BindTextView</code>的行为<code>override</code>掉了。</p>\n" +
            "<pre class=\"highlight java\"><code>    <span class=\"kd\">static</span> <span class=\"n\">NoViewHolder</span><span class=\"o\">.</span><span class=\"na\">Options</span> <span class=\"n\">mDataOptions</span> <span class=\"o\">=</span> <span class=\"k\">new</span> <span class=\"n\">NoViewHolder</span><span class=\"o\">.</span><span class=\"na\">DataOptions</span><span class=\"o\">()</span>\n" +
            "            <span class=\"o\">.</span><span class=\"na\">setBehaviors</span><span class=\"o\">(</span><span class=\"k\">new</span> <span class=\"n\">BindTextView</span><span class=\"o\">.</span><span class=\"na\">Behavior</span><span class=\"o\">()</span> <span class=\"o\">{</span>\n" +
            "                <span class=\"nd\">@Override</span>\n" +
            "                <span class=\"kd\">public</span> <span class=\"kt\">void</span> <span class=\"n\">onBind</span><span class=\"o\">(</span><span class=\"n\">TextView</span> <span class=\"n\">targetView</span><span class=\"o\">,</span> <span class=\"n\">BindTextView</span> <span class=\"n\">annotation</span><span class=\"o\">,</span> <span class=\"n\">Object</span> <span class=\"n\">value</span><span class=\"o\">)</span> <span class=\"o\">{</span>\n" +
            "                    <span class=\"n\">targetView</span><span class=\"o\">.</span><span class=\"na\">setText</span><span class=\"o\">(</span><span class=\"s\">\"fashare 到此一游\"</span> <span class=\"o\">+</span> <span class=\"n\">value</span><span class=\"o\">);</span>\n" +
            "                <span class=\"o\">}</span>\n" +
            "            <span class=\"o\">});</span>\n" +
            "\n" +
            "    <span class=\"kd\">static</span> <span class=\"o\">{</span>\n" +
            "        <span class=\"n\">NoViewHolder</span><span class=\"o\">.</span><span class=\"na\">setDataOptions</span><span class=\"o\">(</span><span class=\"n\">mDataOptions</span><span class=\"o\">);</span>\n" +
            "    <span class=\"o\">}</span></code></pre>\n" +
            "<h2 id=\"总结\">总结</h2>\n" +
            "<p>水平有限，实现的比较粗糙。但我觉得这个思路还行，用起来简洁性也丝毫不比官方的差。觉得它不错的话，可以一起维护这个项目，向<code>No ViewHolder</code>的目标迈进~</p>\n" +
            "<h2 id=\"一些类似实现\">一些类似实现</h2>\n" +
            "<p><a href=\"https://github.com/Kelin-Hong/MVVMLight\" rel=\"nofollow\" target=\"_blank\">https://github.com/Kelin-Hong/MVVMLight</a></p>\n" +
            "\n" +
            "<p><a href=\"https://github.com/evant/binding-collection-adapter\" rel=\"nofollow\" target=\"_blank\">https://github.com/evant/binding-collection-adapter</a></p>\n" +
            "<h2 id=\"感谢\">感谢</h2>\n" +
            "<p><a href=\"https://github.com/hongyangAndroid/baseAdapter\" rel=\"nofollow\" target=\"_blank\">https://github.com/hongyangAndroid/baseAdapter</a> (基于它封装的)</p>\n" +
            "\n" +
            "<p><a href=\"https://github.com/mcxtzhang/all-base-adapter\" rel=\"nofollow\" target=\"_blank\">https://github.com/mcxtzhang/all-base-adapter</a></p>\n" +
            "\n" +
            "<p><a href=\"http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0603/2992.html\" target=\"_blank\">完全掌握Android Data Binding</a></p>\n" +
            "\n" +
            "<p><a href=\"http://www.jianshu.com/p/c6a44e18badb\" target=\"_blank\">[译]关于 Android Adapter，你的实现方式可能一直都有问题</a></p></article></div></div></div></div></body></html>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AppWebView webView = new AppWebView(this);
        String c = null;
        try {
            InputStream open = getAssets().open("content.txt");
            Scanner scanner = new Scanner(open);
            scanner.useDelimiter("\\A");
            c = scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        webView.loadDataWithBaseURL(null, webView.addStyleAndHeader(content, null), "text/html", "utf-8", null);

        setContentView(webView);


        /*Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.baseUrl("https://diycode.cc/api/v3/");
        Retrofit build = builder.build();
        UserApi userApi = build.create(UserApi.class);
        Call<Art> news = userApi.getNews("676");
        news.enqueue(new Callback<Art>() {
            @Override
            public void onResponse(Call<Art> call, Response<Art> response) {
                if (response.isSuccessful()) {
                    Art body = response.body();
                    String body_html = body.getBody_html();
                    webView.loadDataWithBaseURL(null, webView.addStyleAndHeader(body_html, null), "text/html", "utf-8", null);
                }
            }

            @Override
            public void onFailure(Call<Art> call, Throwable t) {
                t.printStackTrace();
            }
        });*/

    }
}
