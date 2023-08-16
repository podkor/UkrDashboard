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

import static com.podkor.ukrdashboard.dto.FeedType.UEFA_COUNTRY_COEFFICIENTS;
import static com.podkor.ukrdashboard.dto.FeedType.UKRAINE_PREMIER_LIAGUE_TABLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UkrainePremierLiagueTableFeedService extends AbstractFeedService {

    private final static FeedType FEED_TYPE = UKRAINE_PREMIER_LIAGUE_TABLE;
    private final static String DIV_ID = "ukrainePremiereLiagueTable";
    private final static String URL = "https://football.ua/ukraine/table.html";

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(
        // With constant DB it should be changed to cron
        initialDelayString = "${config.scheduler.update-uefa-coefficients-feed.initialDelay}",
        fixedDelayString = "${config.scheduler.update-uefa-coefficients-feed.fixedDelay}")
    public void updateFeed() {
        Document doc = getHtmlContent(getUrl());
        Elements mainCol = doc.getElementsByClass("main-tournament-table");
        String htmlData = mainCol
            .outerHtml();

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
