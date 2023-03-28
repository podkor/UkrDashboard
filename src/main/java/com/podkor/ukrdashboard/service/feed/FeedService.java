package com.podkor.ukrdashboard.service.feed;

import com.podkor.ukrdashboard.dto.FeedType;

public interface FeedService {

    void updateFeed();

    String getUrl();

    String getHtmlData(Long tabId);

    FeedType getFeedType();
}
