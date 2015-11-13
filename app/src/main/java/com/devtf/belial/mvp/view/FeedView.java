package com.devtf.belial.mvp.view;

import com.devtf.belial.mvp.model.Feed;

import java.util.List;

/**
 * Created by 小雨 on 2015/11/13.
 */
public interface FeedView {

    /**
     * 显示feed列表
     */
    void showFeedList(List<Feed> feedList);
}
