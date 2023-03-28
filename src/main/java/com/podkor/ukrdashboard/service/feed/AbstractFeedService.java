package com.podkor.ukrdashboard.service.feed;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public abstract class AbstractFeedService implements FeedService {

    protected Document getHtmlContent(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException ex) {
            log.error("Cannot receive content by URL: {}", url);
        }
        return null;
    }

}
