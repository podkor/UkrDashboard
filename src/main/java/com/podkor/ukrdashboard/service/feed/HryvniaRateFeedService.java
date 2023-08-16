package com.podkor.ukrdashboard.service.feed;

import com.podkor.ukrdashboard.dto.FeedType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.podkor.ukrdashboard.dto.FeedType.HRYVNIA_RATE;
import static com.podkor.ukrdashboard.dto.FeedType.UKRAINE_PREMIER_LIAGUE_TABLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class HryvniaRateFeedService extends AbstractFeedService {

    private final static FeedType FEED_TYPE = HRYVNIA_RATE;
    private final static String DIV_ID = "hryvniaRate";
    private final static String URL = "";

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(
        // With constant DB it should be changed to cron
        initialDelayString = "${config.scheduler.update-uefa-coefficients-feed.initialDelay}",
        fixedDelayString = "${config.scheduler.update-uefa-coefficients-feed.fixedDelay}")
    public void updateFeed() {
        String htmlData = "<fxwidget-er inverse=\"false\" amount=\"1\" decimals=\"2\" large=\"false\" shadow=\"true\" symbol=\"true\" flag=\"true\" changes=\"true\" grouping=\"true\" border=\"false\" main-curr=\"USD\" sel-curr=\"UAH\" background-color=\"#faf0be\" border-radius=\"0.15\"></fxwidget-er><a href=\"https://currencyrate.today/\">CurrencyRate</a><script async src=\"https://s.fx-w.io/widgets/exchange-rates/latest.js\"></script>";
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
