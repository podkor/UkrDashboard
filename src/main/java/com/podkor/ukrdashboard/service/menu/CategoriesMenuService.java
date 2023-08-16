package com.podkor.ukrdashboard.service.menu;

import com.podkor.ukrdashboard.dto.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.capitalize;

@Service
@RequiredArgsConstructor
public class CategoriesMenuService {

    private static final String CATEGORY_LINK_FORMAT ="        <a href=\"?category=%s\" %s>%s</a>\n";

    public String createCategoriesMenuDiv(Category activeCategory) {
        return "<div class=\"categories\">\n" +
            Arrays.stream(Category.values())
                  .map(c -> createCategoryLink(c, activeCategory == c))
                  .collect(Collectors.joining()) +
            "</div>";
    }

    private String createCategoryLink(Category category, boolean isActive){
        return String.format(
            CATEGORY_LINK_FORMAT,
            category.name().toLowerCase(),
            isActive ? "class=\"active\"" : EMPTY,
            capitalize(category.name().toLowerCase())
        );
    }
}
