package com.devtf.belial.mvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devtf.belial.mvp.model.Feed;

import java.util.List;

/**
 * Created by 小雨 on 2015/11/13.
 */
public class FeedAdapter extends BaseAdapter {

    private List<Feed> mFeedList;

    public FeedAdapter(List<Feed> feedList) {
        this.mFeedList = feedList;
    }

    @Override
    public int getCount() {
        return mFeedList.size();
    }

    @Override
    public Feed getItem(int position) {
        return mFeedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mFeedList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedViewHolder feedViewHolder = null;
        if (feedViewHolder == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list_view_item, parent, false);
            feedViewHolder = new FeedViewHolder();

            feedViewHolder.vTitle = (TextView) convertView.findViewById(R.id.feed_title);
            feedViewHolder.vContent = (TextView) convertView.findViewById(R.id.feed_content);

            convertView.setTag(feedViewHolder);
        } else {
            feedViewHolder = (FeedViewHolder) convertView.getTag();
        }

        feedViewHolder.vTitle.setText(getItem(position).getTitle());
        feedViewHolder.vContent.setText(getItem(position).getContent());

        return convertView;
    }

    static final class FeedViewHolder {

        TextView vTitle;
        TextView vContent;
    }
}
