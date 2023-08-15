package com.podkor.ukrdashboard.service.feed;

import com.podkor.ukrdashboard.dto.FeedType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.podkor.ukrdashboard.dto.FeedType.DEEP_STATE;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaticFeedService extends AbstractFeedService {

    private final static FeedType FEED_TYPE = DEEP_STATE;
    private final static String URL = "https://terrikon.com/uk/football/uefa_coefs";
    private final static String SEASON = "2023";

    public void addHtmlData(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(
        // With constant DB it should be changed to cron
        initialDelayString = "${config.scheduler.update-uefa-coefficients-feed.initialDelay}",
        fixedDelayString = "${config.scheduler.update-uefa-coefficients-feed.fixedDelay}")
    public void updateFeed() {
        String htmlData = "<iframe src=\"https://www.google.com/maps/d/embed?mid=10n5IOLu0m7G1jSSKxLXgXPznoGjn7MMO&hl=en_US&ehbc=2E312F\" width=\"640\" height=\"480\"></iframe>";

        updateByFeedType(formatHtmlData(htmlData));
        log.info("Feed data has been updated: {}", getFeedType());
    }

    @Override
    public FeedType getFeedType() {
        return FEED_TYPE;
    }

    @Override
    public String formatHtmlData(String htmlData) {
        return getDivWithStyles("width=\"640\" height=\"480\" float=\"left\"") + htmlData + DIV_CLOSE_TAG;
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
