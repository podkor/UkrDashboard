package com.podkor.ukrdashboard.service.feed;

import com.podkor.ukrdashboard.dto.FeedType;
import com.podkor.ukrdashboard.dto.RawHtmlData;
import com.podkor.ukrdashboard.repository.RawHtmlDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.podkor.ukrdashboard.dto.FeedType.UEFA_COUNTRY_COEFFICIENTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UEFACountryCoefficientsFeedService extends AbstractFeedService {

    private final static FeedType FEED_TYPE = UEFA_COUNTRY_COEFFICIENTS;
    private final static String URL = "https://terrikon.com/uk/football/uefa_coefs";
    private final static String SEASON = "2023";

    private final RawHtmlDataRepository rawHtmlDataRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(
        // With constant DB it should be changed to cron
        initialDelayString = "${config.scheduler.update-uefa-coefficients-feed.initialDelay}",
        fixedDelayString = "${config.scheduler.update-uefa-coefficients-feed.fixedDelay}")
    public void updateFeed() {
        Document doc = getHtmlContent(String.format(getUrl(), SEASON));
        Elements mainCol = doc.getElementsByClass("maincol");
        String htmlData = mainCol
            .outerHtml()
            .replace("/i/flag/s/", "https://terrikon.com/i/flag/s/");

        rawHtmlDataRepository.updateByFeedType(getFeedType(), htmlData);
        log.info("Feed data has been updated: {}", UEFA_COUNTRY_COEFFICIENTS);
    }

    @Override
    @Transactional(readOnly = true)
    public String getHtmlData(Long tabId) {
        return rawHtmlDataRepository
            .findOneById(tabId)
            .map(RawHtmlData::getHtmlData)
            .orElse("Data is absent");
    }

    @Override
    public FeedType getFeedType() {
        return FEED_TYPE;
    }

    @Override
    public String getUrl() {
        return URL;
    }

}
