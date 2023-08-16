package com.podkor.ukrdashboard.service.feed;

import com.podkor.ukrdashboard.dto.DataFeed;
import com.podkor.ukrdashboard.repository.DataFeedRepository;
import com.podkor.ukrdashboard.repository.DataTabRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataFeedService {

    protected static final String DIV_TAG = "<div class=\"tabDiv\" %s>";
    protected static final String DIV_CLOSE_TAG = "</div>";

    private final DataFeedRepository dataFeedRepository;
    private final DataTabRepository dataTabRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(
        // With constant DB it should be changed to cron
        initialDelayString = "${config.scheduler.update-uefa-coefficients-feed.initialDelay}",
        fixedDelayString = "${config.scheduler.update-uefa-coefficients-feed.fixedDelay}")
    public void updateFeed() {
        List<DataFeed> dataFeedList = dataFeedRepository.findAll();
        dataFeedList.forEach(
            f -> updateDataTabHtmlData(f)
        );
    }

    public void updateDataTabHtmlData(DataFeed dataFeed) {
        Elements elements = getRequiredElements(dataFeed);
        String htmlData = formatHtmlData(elements.outerHtml(), dataFeed.getDataTab().getStyles())
            .replace("/i/flag/s/", "https://terrikon.com/i/flag/s/");
        dataTabRepository.updateHtmlData(dataFeed.getDataTab().getId(), htmlData);
        log.info("Feed data has been updated: {}", dataFeed.getDataTab().getName());
    }

    private Elements getRequiredElements(DataFeed dataFeed) {
        Document doc = getHtmlContent(dataFeed.getProviderUrl());
        Elements elements = doc.getElementsByClass(dataFeed.getHtmlElementId());
        if (isNull(elements)) {
            elements = doc.getElementsByClass(dataFeed.getHtmlElementId());
        }
        return elements;
    }

    private Document getHtmlContent(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException ex) {
            log.error("Cannot receive content by URL: {}", url);
        }
        return null;
    }

    private String formatHtmlData(String htmlData, String styles) {
        return String.format(DIV_TAG, StringUtils.trimToEmpty(styles)) + htmlData + DIV_CLOSE_TAG;
    }
}
