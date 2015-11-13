package com.devtf.belial.mvp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小雨 on 2015/11/13.
 */
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
