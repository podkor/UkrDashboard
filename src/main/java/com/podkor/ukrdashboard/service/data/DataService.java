package com.podkor.ukrdashboard.service.data;

import com.podkor.ukrdashboard.dto.Category;
import com.podkor.ukrdashboard.repository.DataTabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@RequiredArgsConstructor
public class DataService {

    private final DataTabRepository dataTabRepository;

    public String collectHtmlData(Category category) {
        List<String> htmlDataList = dataTabRepository
            .getAllByCategory(category)
            .stream()
            .map(dt -> dt.getHtmlData())
            .collect(Collectors.toList());

        return StringUtils.join(htmlDataList, EMPTY);
    }
}
