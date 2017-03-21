package com.zly.diycode.http;

/**
 * Created by zhangluya on 2017/3/21.
 */

public class Art {


    /**
     * id : 676
     * title : No ViewHolder!!! 一个非官方的纯 java 版 databinding (拒绝 xml 配置).
     * created_at : 2017-03-16T12:34:20.652+08:00
     * updated_at : 2017-03-18T12:46:32.498+08:00
     * replied_at : null
     * replies_count : 0
     * node_name : Android
     * node_id : 1
     * last_reply_user_id : null
     * last_reply_user_login : null
     * user : {"id":3555,"login":"fashare2015","name":"梁山boy","avatar_url":"https://diycode.b0.upaiyun.com/user/large_avatar/3555_1482815407.jpg"}
     * deleted : false
     * excellent : false
     * abilities : {"update":false,"destroy":false}
     * body : # 写在前面
     > 我们的目标是 No ViewHolder and No Adapter.

     官方的`databinding`的确十分厉害，各种`xml`绑定，然后自动生成一波文件，各种吊的飞起，不过容易让人抓不住重点。为了加深理解，我写了这个`纯java版`的`databindng`, 不需要`xml`各种配置`android:text="@{...}"`，同时进一步加了绑定`Adapter`。

     >时间仓促，只粗略的实现了小部分功能。基于注解的性能也有待优化，但它已经极大地提升了我的开发效率。觉得它不错的话，可以一起维护这个项目，向`No ViewHolder`的目标迈进~


     # 预览
     ### 常规的电商首页

     ![](https://user-gold-cdn.xitu.io/2017/3/16/16009283e2dc665b39f99d36d41d0ce7)

     ### 所需代码量
     实现这样一个带`Header`, 带`上拉加载`的列表需要多少代码呢？
     - 两个无聊的 javabean
     - 一个轮播控件
     - 一个Activity（真的不含 Adapter 啊）
     - 然后没有然后了。。。

     ![](https://user-gold-cdn.xitu.io/2017/3/16/4e820744d74e0c6e9f5076638d122d29)

     # 特性
     - `data -> view`的单向绑定
     - 支持常用控件的绑定，同时增加了官方没有的`Adapter`绑定。支持`Header`和`上拉加载`
     - 代码极其简洁, 无需实例化`View`, 也没有`Adapter`, 连`ViewHoler` 也没有。。。
     - 支持绑定行为的自定义
     - 配合 Rxjava + Lambda 简直上天

     # 源码
     https://github.com/fashare2015/NoViewHolder

     # gralde 依赖
     ```gradle
     // 1. Add it in your root build.gradle at the end of repositories:
     allprojects {
     repositories {
     ...
     maven { url 'https://jitpack.io' }
     }
     }

     // 2. Add the dependency in your app/build.gradle
     dependencies {
     compile 'com.github.fashare2015:NoViewHolder:1.0.1'
     }
     ```
     # 绑定 Data 和 View
     这一块和官方差不多，只是`xml`配置换成了`java注解`配置。

     ## 绑定单个 View
     首先，你手头有一个`javabean`，就是你在图中看到的`妹子列表Item`如：
     ```java
     public class MeiZhi {
    @BindImageView(id=R.id.iv_image, placeHolder = R.mipmap.ic_launcher)
    public String url;  // 把 url 绑定在 ImageView 上
    @BindTextView(id=R.id.tv_title) public String desc; // 把 desc 绑定在 TextView 上
    }
     ```
     基本等同于官方的`android:text="@{meizhi.desc}"`，用过`databinding`的话应该秒懂的。。。


     ## 绑定列表
     当然，服务端返回的肯定是个妹子的列表，你手头还会有一个`HomeInfo`的东东。
     ```java
     public class HomeInfo {
     // 妹子列表区
     @BindRecyclerView(id = R.id.rv_meizhi, layout = R.layout.item_meizhi)
     private List<MeiZhi> results = new ArrayList<>();   // 把 List 绑定在 RecyclerView 上

     // banner
     @BindViewPager(id = R.id.vp_banner, layout = R.layout.item_banner)
     private List<MeiZhi> bannerInfo;    // 把 List 绑定在 ViewPager 上
     }
     ```
     这部分是官方没有的，相应的还提供了 `@BindListView`

     ## 绑定 header
     像上面的配置，`banner`和妹子列表是分开的，不会一起滑动的。因此，提供了向`RecyclerView`中添加`Header`的注解——`@BindRvHeader`.
     让我们把`banner`加进`RecyclerView`

     ```java
     public class HomeInfo {
     // 妹子列表区
     @BindRecyclerView(id = R.id.rv_meizhi, layout = R.layout.item_meizhi)
     private List<MeiZhi> results = new ArrayList<>();   // 把 List 绑定在 RecyclerView 上

     // banner
     @BindRvHeader(id = R.id.rv_meizhi, layout = R.layout.layout_banner, itemType = 0) // 增加这一行 !!!
     @BindViewPager(id = R.id.vp_banner, layout = R.layout.item_banner)
     private List<MeiZhi> bannerInfo;    // 把 List 绑定在 ViewPager 上
     }
     ```

     ## 绑定点击事件
     提供了`@BindItemClick`、`@BindClick`
     ```java
     public class MainActivity extends AppCompatActivity {
     ...
     @BindItemClick(id = R.id.vp_banner)
     NoOnItemClickListener<MeiZhi> clickBanner = (view, data, pos) -> toast("click Banner: " + pos + ", "+ data.toString());

     @BindItemClick(id = R.id.rv_meizhi)
     NoOnItemClickListener<MeiZhi> clickMeiZhi = (view, data, pos) -> toast("click MeiZhi: " + pos + ", "+ data.toString());
     }
     ```

     # 更新 UI
     前面只是一系列绑定关系的配置，还需要一个接口触发他们：
     - 初始化：根据 R.id.XXX 初始化相应的 View 和 Adapter，为后续`更新UI`做准备

     ```java
     mNoViewHolder = new NoViewHolder.Builder(this)
     .initView(new HomeInfo()) // 一定要提供`注解信息`的类，否则无法初始化。
     .build();
     ```
     - 更新UI: `mNoViewHolder.notifyDataSetChanged(homeInfo);`
     自动根据 `homeInfo` 里提供的注解信息，找到相应的控件，并把数据刷新上去。

     ```java
     // 在请求的 onSuccess() 中刷新界面，本例使用了 Rxjava 和 lambda
     homeInfoObservable.subscribe(homeInfo -> {
     mHomeInfo.getResults().addAll(homeInfo.getResults());           // 更新 妹子列表 info
     if(homeInfo.getResults().size() >= 6)
     mHomeInfo.setBannerInfo(homeInfo.getResults().subList(0, 6));   // 更新 bannerInfo

     mNoViewHolder.notifyDataSetChanged(mHomeInfo);  // mHomeInfo 发生变化, 通知 UI 及时刷新
     }
     ```

     # 全局配置——自定义行为
     当你需要自定义的时候 (比如替换图片加载库，默认`Glide`)。可以这样：
     如下，即把`@BindTextView`的行为`override`掉了。
     ```java
     static NoViewHolder.Options mDataOptions = new NoViewHolder.DataOptions()
     .setBehaviors(new BindTextView.Behavior() {
     @Override public void onBind(TextView targetView, BindTextView annotation, Object value) {
     targetView.setText("fashare 到此一游" + value);
     }
     });

     static {
     NoViewHolder.setDataOptions(mDataOptions);
     }
     ```

     # 总结
     水平有限，实现的比较粗糙。但我觉得这个思路还行，用起来简洁性也丝毫不比官方的差。觉得它不错的话，可以一起维护这个项目，向`No ViewHolder`的目标迈进~

     # 一些类似实现
     https://github.com/Kelin-Hong/MVVMLight

     https://github.com/evant/binding-collection-adapter

     # 感谢
     https://github.com/hongyangAndroid/baseAdapter (基于它封装的)

     https://github.com/mcxtzhang/all-base-adapter

     [完全掌握Android Data Binding](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0603/2992.html)

     [[译]关于 Android Adapter，你的实现方式可能一直都有问题](http://www.jianshu.com/p/c6a44e18badb)
      * body_html : <h2 id="写在前面">写在前面</h2>
     <blockquote>
     <p>我们的目标是 No ViewHolder and No Adapter.</p>
     </blockquote>

     <p>官方的<code>databinding</code>的确十分厉害，各种<code>xml</code>绑定，然后自动生成一波文件，各种吊的飞起，不过容易让人抓不住重点。为了加深理解，我写了这个<code>纯java版</code>的<code>databindng</code>, 不需要<code>xml</code>各种配置<code>android:text="@{...}"</code>，同时进一步加了绑定<code>Adapter</code>。</p>

     <blockquote>
     <p>时间仓促，只粗略的实现了小部分功能。基于注解的性能也有待优化，但它已经极大地提升了我的开发效率。觉得它不错的话，可以一起维护这个项目，向<code>No ViewHolder</code>的目标迈进~</p>
     </blockquote>
     <h2 id="预览">预览</h2><h3 id="常规的电商首页">常规的电商首页</h3>
     <p><img src="https://user-gold-cdn.xitu.io/2017/3/16/16009283e2dc665b39f99d36d41d0ce7" title="" alt=""></p>
     <h3 id="所需代码量">所需代码量</h3>
     <p>实现这样一个带<code>Header</code>, 带<code>上拉加载</code>的列表需要多少代码呢？<br>
     - 两个无聊的 javabean<br>
     - 一个轮播控件<br>
     - 一个Activity（真的不含 Adapter 啊）<br>
     - 然后没有然后了。。。</p>

     <p><img src="https://user-gold-cdn.xitu.io/2017/3/16/4e820744d74e0c6e9f5076638d122d29" title="" alt=""></p>
     <h2 id="特性">特性</h2>
     <ul>
     <li>
     <code>data -&gt; view</code>的单向绑定</li>
     <li>支持常用控件的绑定，同时增加了官方没有的<code>Adapter</code>绑定。支持<code>Header</code>和<code>上拉加载</code>
     </li>
     <li>代码极其简洁, 无需实例化<code>View</code>, 也没有<code>Adapter</code>, 连<code>ViewHoler</code> 也没有。。。</li>
     <li>支持绑定行为的自定义</li>
     <li>配合 Rxjava + Lambda 简直上天</li>
     </ul>
     <h2 id="源码">源码</h2>
     <p><a href="https://github.com/fashare2015/NoViewHolder" rel="nofollow" target="_blank">https://github.com/fashare2015/NoViewHolder</a></p>
     <h2 id="gralde 依赖">gralde 依赖</h2><pre class="highlight plaintext"><code>// 1. Add it in your root build.gradle at the end of repositories:
     allprojects {
     repositories {
     ...
     maven { url 'https://jitpack.io' }
     }
     }

     // 2. Add the dependency in your app/build.gradle
     dependencies {
     compile 'com.github.fashare2015:NoViewHolder:1.0.1'
     }</code></pre>
     <h2 id="绑定 Data 和 View">绑定 Data 和 View</h2>
     <p>这一块和官方差不多，只是<code>xml</code>配置换成了<code>java注解</code>配置。</p>
     <h2 id="绑定单个 View">绑定单个 View</h2>
     <p>首先，你手头有一个<code>javabean</code>，就是你在图中看到的<code>妹子列表Item</code>如：</p>
     <pre class="highlight java"><code><span class="kd">public</span> <span class="kd">class</span> <span class="nc">MeiZhi</span> <span class="o">{</span>
     <span class="nd">@BindImageView</span><span class="o">(</span><span class="n">id</span><span class="o">=</span><span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">iv_image</span><span class="o">,</span> <span class="n">placeHolder</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">mipmap</span><span class="o">.</span><span class="na">ic_launcher</span><span class="o">)</span>
     <span class="kd">public</span> <span class="n">String</span> <span class="n">url</span><span class="o">;</span>  <span class="c1">// 把 url 绑定在 ImageView 上</span>
     <span class="nd">@BindTextView</span><span class="o">(</span><span class="n">id</span><span class="o">=</span><span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">tv_title</span><span class="o">)</span>
     <span class="kd">public</span> <span class="n">String</span> <span class="n">desc</span><span class="o">;</span> <span class="c1">// 把 desc 绑定在 TextView 上</span>
     <span class="o">}</span></code></pre>

     <p>基本等同于官方的<code>android:text="@{meizhi.desc}"</code>，用过<code>databinding</code>的话应该秒懂的。。。</p>
     <h2 id="绑定列表">绑定列表</h2>
     <p>当然，服务端返回的肯定是个妹子的列表，你手头还会有一个<code>HomeInfo</code>的东东。</p>
     <pre class="highlight java"><code><span class="kd">public</span> <span class="kd">class</span> <span class="nc">HomeInfo</span> <span class="o">{</span>
     <span class="c1">// 妹子列表区</span>
     <span class="nd">@BindRecyclerView</span><span class="o">(</span><span class="n">id</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">rv_meizhi</span><span class="o">,</span> <span class="n">layout</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">layout</span><span class="o">.</span><span class="na">item_meizhi</span><span class="o">)</span>
     <span class="kd">private</span> <span class="n">List</span><span class="o">&lt;</span><span class="n">MeiZhi</span><span class="o">&gt;</span> <span class="n">results</span> <span class="o">=</span> <span class="k">new</span> <span class="n">ArrayList</span><span class="o">&lt;&gt;();</span>   <span class="c1">// 把 List 绑定在 RecyclerView 上</span>

     <span class="c1">// banner</span>
     <span class="nd">@BindViewPager</span><span class="o">(</span><span class="n">id</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">vp_banner</span><span class="o">,</span> <span class="n">layout</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">layout</span><span class="o">.</span><span class="na">item_banner</span><span class="o">)</span>
     <span class="kd">private</span> <span class="n">List</span><span class="o">&lt;</span><span class="n">MeiZhi</span><span class="o">&gt;</span> <span class="n">bannerInfo</span><span class="o">;</span>    <span class="c1">// 把 List 绑定在 ViewPager 上</span>
     <span class="o">}</span></code></pre>

     <p>这部分是官方没有的，相应的还提供了 <code>@BindListView</code></p>
     <h2 id="绑定 header">绑定 header</h2>
     <p>像上面的配置，<code>banner</code>和妹子列表是分开的，不会一起滑动的。因此，提供了向<code>RecyclerView</code>中添加<code>Header</code>的注解——<code>@BindRvHeader</code>.<br>
     让我们把<code>banner</code>加进<code>RecyclerView</code></p>
     <pre class="highlight java"><code><span class="kd">public</span> <span class="kd">class</span> <span class="nc">HomeInfo</span> <span class="o">{</span>
     <span class="c1">// 妹子列表区</span>
     <span class="nd">@BindRecyclerView</span><span class="o">(</span><span class="n">id</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">rv_meizhi</span><span class="o">,</span> <span class="n">layout</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">layout</span><span class="o">.</span><span class="na">item_meizhi</span><span class="o">)</span>
     <span class="kd">private</span> <span class="n">List</span><span class="o">&lt;</span><span class="n">MeiZhi</span><span class="o">&gt;</span> <span class="n">results</span> <span class="o">=</span> <span class="k">new</span> <span class="n">ArrayList</span><span class="o">&lt;&gt;();</span>   <span class="c1">// 把 List 绑定在 RecyclerView 上</span>

     <span class="c1">// banner</span>
     <span class="nd">@BindRvHeader</span><span class="o">(</span><span class="n">id</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">rv_meizhi</span><span class="o">,</span> <span class="n">layout</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">layout</span><span class="o">.</span><span class="na">layout_banner</span><span class="o">,</span> <span class="n">itemType</span> <span class="o">=</span> <span class="mi">0</span><span class="o">)</span> <span class="c1">// 增加这一行 !!!</span>
     <span class="nd">@BindViewPager</span><span class="o">(</span><span class="n">id</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">vp_banner</span><span class="o">,</span> <span class="n">layout</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">layout</span><span class="o">.</span><span class="na">item_banner</span><span class="o">)</span>
     <span class="kd">private</span> <span class="n">List</span><span class="o">&lt;</span><span class="n">MeiZhi</span><span class="o">&gt;</span> <span class="n">bannerInfo</span><span class="o">;</span>    <span class="c1">// 把 List 绑定在 ViewPager 上</span>
     <span class="o">}</span></code></pre>
     <h2 id="绑定点击事件">绑定点击事件</h2>
     <p>提供了<code>@BindItemClick</code>、<code>@BindClick</code></p>
     <pre class="highlight java"><code><span class="kd">public</span> <span class="kd">class</span> <span class="nc">MainActivity</span> <span class="kd">extends</span> <span class="n">AppCompatActivity</span> <span class="o">{</span>
     <span class="o">...</span>
     <span class="nd">@BindItemClick</span><span class="o">(</span><span class="n">id</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">vp_banner</span><span class="o">)</span>
     <span class="n">NoOnItemClickListener</span><span class="o">&lt;</span><span class="n">MeiZhi</span><span class="o">&gt;</span> <span class="n">clickBanner</span> <span class="o">=</span> <span class="o">(</span><span class="n">view</span><span class="o">,</span> <span class="n">data</span><span class="o">,</span> <span class="n">pos</span><span class="o">)</span> <span class="o">-&gt;</span> <span class="n">toast</span><span class="o">(</span><span class="s">"click Banner: "</span> <span class="o">+</span> <span class="n">pos</span> <span class="o">+</span> <span class="s">", "</span><span class="o">+</span> <span class="n">data</span><span class="o">.</span><span class="na">toString</span><span class="o">());</span>

     <span class="nd">@BindItemClick</span><span class="o">(</span><span class="n">id</span> <span class="o">=</span> <span class="n">R</span><span class="o">.</span><span class="na">id</span><span class="o">.</span><span class="na">rv_meizhi</span><span class="o">)</span>
     <span class="n">NoOnItemClickListener</span><span class="o">&lt;</span><span class="n">MeiZhi</span><span class="o">&gt;</span> <span class="n">clickMeiZhi</span> <span class="o">=</span> <span class="o">(</span><span class="n">view</span><span class="o">,</span> <span class="n">data</span><span class="o">,</span> <span class="n">pos</span><span class="o">)</span> <span class="o">-&gt;</span> <span class="n">toast</span><span class="o">(</span><span class="s">"click MeiZhi: "</span> <span class="o">+</span> <span class="n">pos</span> <span class="o">+</span> <span class="s">", "</span><span class="o">+</span> <span class="n">data</span><span class="o">.</span><span class="na">toString</span><span class="o">());</span>
     <span class="o">}</span></code></pre>
     <h2 id="更新 UI">更新 UI</h2>
     <p>前面只是一系列绑定关系的配置，还需要一个接口触发他们：<br>
     - 初始化：根据 R.id.XXX 初始化相应的 View 和 Adapter，为后续<code>更新UI</code>做准备</p>
     <pre class="highlight java"><code><span class="n">mNoViewHolder</span> <span class="o">=</span> <span class="k">new</span> <span class="n">NoViewHolder</span><span class="o">.</span><span class="na">Builder</span><span class="o">(</span><span class="k">this</span><span class="o">)</span>
     <span class="o">.</span><span class="na">initView</span><span class="o">(</span><span class="k">new</span> <span class="n">HomeInfo</span><span class="o">())</span> <span class="c1">// 一定要提供`注解信息`的类，否则无法初始化。</span>
     <span class="o">.</span><span class="na">build</span><span class="o">();</span></code></pre>

     <ul>
     <li>更新UI: <code>mNoViewHolder.notifyDataSetChanged(homeInfo);</code>
     自动根据 <code>homeInfo</code> 里提供的注解信息，找到相应的控件，并把数据刷新上去。</li>
     </ul>
     <pre class="highlight java"><code><span class="c1">// 在请求的 onSuccess() 中刷新界面，本例使用了 Rxjava 和 lambda</span>
     <span class="n">homeInfoObservable</span><span class="o">.</span><span class="na">subscribe</span><span class="o">(</span><span class="n">homeInfo</span> <span class="o">-&gt;</span> <span class="o">{</span>
     <span class="n">mHomeInfo</span><span class="o">.</span><span class="na">getResults</span><span class="o">().</span><span class="na">addAll</span><span class="o">(</span><span class="n">homeInfo</span><span class="o">.</span><span class="na">getResults</span><span class="o">());</span>           <span class="c1">// 更新 妹子列表 info</span>
     <span class="k">if</span><span class="o">(</span><span class="n">homeInfo</span><span class="o">.</span><span class="na">getResults</span><span class="o">().</span><span class="na">size</span><span class="o">()</span> <span class="o">&gt;=</span> <span class="mi">6</span><span class="o">)</span>
     <span class="n">mHomeInfo</span><span class="o">.</span><span class="na">setBannerInfo</span><span class="o">(</span><span class="n">homeInfo</span><span class="o">.</span><span class="na">getResults</span><span class="o">().</span><span class="na">subList</span><span class="o">(</span><span class="mi">0</span><span class="o">,</span> <span class="mi">6</span><span class="o">));</span>   <span class="c1">// 更新 bannerInfo</span>

     <span class="n">mNoViewHolder</span><span class="o">.</span><span class="na">notifyDataSetChanged</span><span class="o">(</span><span class="n">mHomeInfo</span><span class="o">);</span>  <span class="c1">// mHomeInfo 发生变化, 通知 UI 及时刷新</span>
     <span class="o">}</span></code></pre>
     <h2 id="全局配置——自定义行为">全局配置——自定义行为</h2>
     <p>当你需要自定义的时候 (比如替换图片加载库，默认<code>Glide</code>)。可以这样：<br>
     如下，即把<code>@BindTextView</code>的行为<code>override</code>掉了。</p>
     <pre class="highlight java"><code>    <span class="kd">static</span> <span class="n">NoViewHolder</span><span class="o">.</span><span class="na">Options</span> <span class="n">mDataOptions</span> <span class="o">=</span> <span class="k">new</span> <span class="n">NoViewHolder</span><span class="o">.</span><span class="na">DataOptions</span><span class="o">()</span>
     <span class="o">.</span><span class="na">setBehaviors</span><span class="o">(</span><span class="k">new</span> <span class="n">BindTextView</span><span class="o">.</span><span class="na">Behavior</span><span class="o">()</span> <span class="o">{</span>
     <span class="nd">@Override</span>
     <span class="kd">public</span> <span class="kt">void</span> <span class="n">onBind</span><span class="o">(</span><span class="n">TextView</span> <span class="n">targetView</span><span class="o">,</span> <span class="n">BindTextView</span> <span class="n">annotation</span><span class="o">,</span> <span class="n">Object</span> <span class="n">value</span><span class="o">)</span> <span class="o">{</span>
     <span class="n">targetView</span><span class="o">.</span><span class="na">setText</span><span class="o">(</span><span class="s">"fashare 到此一游"</span> <span class="o">+</span> <span class="n">value</span><span class="o">);</span>
     <span class="o">}</span>
     <span class="o">});</span>

     <span class="kd">static</span> <span class="o">{</span>
     <span class="n">NoViewHolder</span><span class="o">.</span><span class="na">setDataOptions</span><span class="o">(</span><span class="n">mDataOptions</span><span class="o">);</span>
     <span class="o">}</span></code></pre>
     <h2 id="总结">总结</h2>
     <p>水平有限，实现的比较粗糙。但我觉得这个思路还行，用起来简洁性也丝毫不比官方的差。觉得它不错的话，可以一起维护这个项目，向<code>No ViewHolder</code>的目标迈进~</p>
     <h2 id="一些类似实现">一些类似实现</h2>
     <p><a href="https://github.com/Kelin-Hong/MVVMLight" rel="nofollow" target="_blank">https://github.com/Kelin-Hong/MVVMLight</a></p>

     <p><a href="https://github.com/evant/binding-collection-adapter" rel="nofollow" target="_blank">https://github.com/evant/binding-collection-adapter</a></p>
     <h2 id="感谢">感谢</h2>
     <p><a href="https://github.com/hongyangAndroid/baseAdapter" rel="nofollow" target="_blank">https://github.com/hongyangAndroid/baseAdapter</a> (基于它封装的)</p>

     <p><a href="https://github.com/mcxtzhang/all-base-adapter" rel="nofollow" target="_blank">https://github.com/mcxtzhang/all-base-adapter</a></p>

     <p><a href="http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0603/2992.html" target="_blank">完全掌握Android Data Binding</a></p>

     <p><a href="http://www.jianshu.com/p/c6a44e18badb" target="_blank">[译]关于 Android Adapter，你的实现方式可能一直都有问题</a></p>
      * hits : 418
      * likes_count : 2
      * suggested_at : null
      * followed : null
      * liked : null
      * favorited : null
     */

    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private Object replied_at;
    private int replies_count;
    private String node_name;
    private int node_id;
    private Object last_reply_user_id;
    private Object last_reply_user_login;
    private UserBean user;
    private boolean deleted;
    private boolean excellent;
    private AbilitiesBean abilities;
    private String body;
    private String body_html;
    private int hits;
    private int likes_count;
    private Object suggested_at;
    private Object followed;
    private Object liked;
    private Object favorited;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(Object replied_at) {
        this.replied_at = replied_at;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public Object getLast_reply_user_id() {
        return last_reply_user_id;
    }

    public void setLast_reply_user_id(Object last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public Object getLast_reply_user_login() {
        return last_reply_user_login;
    }

    public void setLast_reply_user_login(Object last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public AbilitiesBean getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitiesBean abilities) {
        this.abilities = abilities;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public Object getSuggested_at() {
        return suggested_at;
    }

    public void setSuggested_at(Object suggested_at) {
        this.suggested_at = suggested_at;
    }

    public Object getFollowed() {
        return followed;
    }

    public void setFollowed(Object followed) {
        this.followed = followed;
    }

    public Object getLiked() {
        return liked;
    }

    public void setLiked(Object liked) {
        this.liked = liked;
    }

    public Object getFavorited() {
        return favorited;
    }

    public void setFavorited(Object favorited) {
        this.favorited = favorited;
    }

    public static class UserBean {
        /**
         * id : 3555
         * login : fashare2015
         * name : 梁山boy
         * avatar_url : https://diycode.b0.upaiyun.com/user/large_avatar/3555_1482815407.jpg
         */

        private int id;
        private String login;
        private String name;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

    public static class AbilitiesBean {
        /**
         * update : false
         * destroy : false
         */

        private boolean update;
        private boolean destroy;

        public boolean isUpdate() {
            return update;
        }

        public void setUpdate(boolean update) {
            this.update = update;
        }

        public boolean isDestroy() {
            return destroy;
        }

        public void setDestroy(boolean destroy) {
            this.destroy = destroy;
        }
    }
}
