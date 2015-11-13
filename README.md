####Android MVP架构浅析

　　**MVP** 是从经典的模式**MVC**衍变而来，它们的基本思想有相通的地方：Controller/Presenter负责逻辑的处理，Model负责提供数据，View负责显示。作为一种新的衍生模式，MVP与MVC有着一个重大的区别：在MVP中View并不直接与Model交互，它们之间的通信是通过Presenter来进行的，所有的交互都发生在Presenter内部，而在MVC中View会从直接Model中读取数据而不是通过Controller。也许您对以上说的还没有一个清晰的概念，那还有什么比图文解释更让人容易理解的呢！
![](http://belial.me/wp-content/uploads/2015/11/mvp-arch.png)

**为什么使用MVP模式**
　　了解他们的区别后，那我们为什么要使用MVP模式呢? 众所周知，在Android开发中，Activity or Fragment并不是一个MVC模式中标准的Controller，它的首要职责是初始化用户界面，并负责与用户操作交互。随着界面和业务逻辑的复杂度的持续提升，Activity的职责不断增加，以致其变得臃肿不堪。当将其中复杂的业务逻辑处理移至另外的一个类（Presneter）中时，Activity or Fragment处理数据的显示以及与用户的交互，也就是MVP模式中View层的职责，其建立UI元素与Presenter的关联，同时也会处理一些简单的界面逻辑。

**MVP带来的好处**
-	降低耦合度
-	利于测试
-	代码复用
-	数据隐藏
-	代码灵活性

**采用MVP模式进行Android开发的实例**

这里我们先定义model层的实体：
```
public class Feed {

    private String title;   //标题

    private String content; //内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
```

有了业务实体之后 我们再定义一个数据仓库工厂用来存取数据：
```
public class FeedDataStoreFactory {

    private FeedDataStoreFactory() {
    }

    public static FeedDataStoreFactory getInstance() {
        return SingletonHolder.instance;
    }

    public List<Feed> getFeedStoreData() {
        List<Feed> feedList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Feed feed = new Feed();
            feed.setTitle("This is title" + i);
            feed.setContent("This is content" + i);
            feedList.add(feed);
        }

        return feedList;
    }

    private static class SingletonHolder {
        private static FeedDataStoreFactory instance = new FeedDataStoreFactory();
    }
}
```
Model层定义暂且告一段落，Presenter要拿到View的句柄，而我们不可能把Activity or Fragment传给Presenter，所以我们在这里需要定义一个View接口：
```
public interface FeedView {

    /**
     * 显示feed列表
     */
    void showFeedList(List<Feed> feedList);
}

```

最后就剩下Presenter没有定义，作为View和Model的交互中枢，因此它需要持有View句柄，构造方法注入View只是其中一种实现，有了View的句柄之后Presenter可以通过View句柄与View交互。定义如下：
```
public class FeedPresenter {

    private FeedView mFeedView;

    public FeedPresenter(FeedView feedView) {
        this.mFeedView = feedView;
    }

    public void loadFeedList() {
        this.mFeedView.showFeedList(FeedDataStoreFactory.getInstance().getFeedStoreData());
    }
}

```

至此对象的依赖图已经构建好了，接着该是Activity Or Fragment出场的时候了......

```
public class MainActivity extends AppCompatActivity implements FeedView {

    private ListView vFeedListView;
    private FeedAdapter mFeedAdapter;
    private FeedPresenter mFeedPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vFeedListView = (ListView) findViewById(R.id.feed_list);

        mFeedPresenter = new FeedPresenter(this);
        mFeedPresenter.loadFeedList();
    }

    @Override
    public void showFeedList(List<Feed> feedList) {
        mFeedAdapter = new FeedAdapter(feedList);
        vFeedListView.setAdapter(mFeedAdapter);
    }
}
```

FeedAdapter的定义就不贴出来了，以免篇幅过长，文终将附加一个实例源码gitgub的地址

细心的你也许会发现这样实现MVP模式耦合还是很严重的，我会一步步重构此项目尽量让它接近最优状态。（ps：我文采不好，大神请口下留情啊）

实例源码github地址：https://github.com/Tiny-Times/android-mvp.git