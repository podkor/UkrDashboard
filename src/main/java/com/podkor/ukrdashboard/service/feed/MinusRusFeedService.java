package com.podkor.ukrdashboard.service.feed;

import com.podkor.ukrdashboard.dto.FeedType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.podkor.ukrdashboard.dto.FeedType.MINUS_RUS;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinusRusFeedService extends AbstractFeedService {

    private final static FeedType FEED_TYPE = MINUS_RUS;
    private final static String DIV_ID = "minusRus";
    private final static String URL = "";

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(
        // With constant DB it should be changed to cron
        initialDelayString = "${config.scheduler.update-uefa-coefficients-feed.initialDelay}",
        fixedDelayString = "${config.scheduler.update-uefa-coefficients-feed.fixedDelay}")
    public void updateFeed() {
        String htmlData = "<ins data-wrapper=\"minusrus-widget\" data-width=\"240\" data-lang=\"ua\"></ins><script defer src=\"https://minusrus.com/widget/init.js\"></script>";
        updateByFeedType(formatHtmlData(htmlData));
        log.info("Feed data has been updated: {}", getFeedType());
    }

    @Override
    public FeedType getFeedType() {
        return FEED_TYPE;
    }

    @Override
    public String formatHtmlData(String htmlData) {
        return formatDivTag(DIV_ID, "") + htmlData + DIV_CLOSE_TAG;
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
