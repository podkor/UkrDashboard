package com.podkor.ukrdashboard.service.feed;

import com.podkor.ukrdashboard.dto.FeedType;
import com.podkor.ukrdashboard.dto.RawHtmlData;
import com.podkor.ukrdashboard.repository.RawHtmlDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
public abstract class AbstractFeedService implements FeedService {

    protected static final String DIV_TAG = "<div class=\"tabDiv\">";
    protected static final String DIV_CLOSE_TAG = "</div>";
    private static final String DIV_FORMAT_TAG = "<div class=\"tabDiv\" id=\"%s\" %s>";
    private static final String DIV_WITH_STYLES = "<div %s>";

    @Autowired
    private RawHtmlDataRepository rawHtmlDataRepository;

    protected Document getHtmlContent(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException ex) {
            log.error("Cannot receive content by URL: {}", url);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public String getHtmlData(Long tabId) {
        return rawHtmlDataRepository
            .findOneById(tabId)
            .map(RawHtmlData::getHtmlData)
            .orElse("Data is absent");
    }

    @Override
    public boolean canHandle(FeedType feedType) {
        return getFeedType() == feedType;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateByFeedType(String htmlData) {
        rawHtmlDataRepository.updateByFeedType(getFeedType(), htmlData);
    }

    protected String formatDivTag(String divId, String styles){
        return String.format(DIV_FORMAT_TAG, divId, styles);
    }

    protected String getDivWithStyles(String styles){
        return String.format(DIV_WITH_STYLES, styles);
    }
}
