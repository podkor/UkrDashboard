package com.podkor.ukrdashboard.service.data;

import com.podkor.ukrdashboard.dto.Category;
import com.podkor.ukrdashboard.dto.CountryCoefficients;
import com.podkor.ukrdashboard.dto.FeedType;
import com.podkor.ukrdashboard.repository.CountryCoefficientsRepository;
import com.podkor.ukrdashboard.repository.DataTabRepository;
import com.podkor.ukrdashboard.service.feed.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@RequiredArgsConstructor
public class DataService {

    private static final String currentSeason = "2022/23";

    private final CountryCoefficientsRepository countryCoefficientsRepository;
    private final DataTabRepository dataTabRepository;
    private final List<FeedService> feedServices;

    public Set<CountryCoefficients> getCoefficients() {
        return countryCoefficientsRepository.findAllBySeason(currentSeason);
    }

    public String getHtmlData(Long tabId) {
        FeedType feedType = dataTabRepository.getDataTabById(tabId).getFeedType();
        return getFeedServiceByFeedType(feedType).getHtmlData(tabId);
    }

    private FeedService getFeedServiceByFeedType(FeedType feedType) {
        return feedServices
            .stream()
            .filter(s -> s.canHandle(feedType))
            .findAny()
            .get();
    }

    public String collectHtmlData(Category category) {
        List<String> htmlDataList = dataTabRepository
            .getAllByCategory(category)
            .stream()
            .map(dt -> getFeedServiceByFeedType(dt.getFeedType()).getHtmlData(dt.getId()))
            .collect(Collectors.toList());

        return StringUtils.join(htmlDataList, EMPTY);
    }
}
