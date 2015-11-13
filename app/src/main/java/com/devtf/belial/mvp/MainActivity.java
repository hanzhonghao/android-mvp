package com.devtf.belial.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.devtf.belial.mvp.model.Feed;
import com.devtf.belial.mvp.presenter.FeedPresenter;
import com.devtf.belial.mvp.view.FeedView;

import java.util.List;

/**
 * Created by 小雨 on 2015/11/13.
 */
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
