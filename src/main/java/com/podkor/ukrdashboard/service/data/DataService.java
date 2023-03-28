package com.podkor.ukrdashboard.service.data;

import com.podkor.ukrdashboard.dto.CountryCoefficients;
import com.podkor.ukrdashboard.repository.CountryCoefficientsRepository;
import com.podkor.ukrdashboard.service.feed.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DataService {

    private final FeedService feedService;

    private static final String currentSeason = "2022/23";

    private final CountryCoefficientsRepository countryCoefficientsRepository;

    public Set<CountryCoefficients> getCoefficients() {
        return countryCoefficientsRepository.findAllBySeason(currentSeason);
    }

    public String getHtmlData(Long tabId) {
        return feedService.getHtmlData(tabId);
    }
}
