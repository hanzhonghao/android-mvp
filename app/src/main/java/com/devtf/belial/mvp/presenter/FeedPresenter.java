package com.devtf.belial.mvp.presenter;

import com.devtf.belial.mvp.model.FeedDataStoreFactory;
import com.devtf.belial.mvp.view.FeedView;

/**
 * Created by 小雨 on 2015/11/13.
 */
public class FeedPresenter {

    private FeedView mFeedView;

    public FeedPresenter(FeedView feedView) {
        this.mFeedView = feedView;
    }

    public void loadFeedList() {
        this.mFeedView.showFeedList(FeedDataStoreFactory.getInstance().getFeedStoreData());
    }
}
