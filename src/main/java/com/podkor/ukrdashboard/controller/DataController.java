package com.podkor.ukrdashboard.controller;

import com.podkor.ukrdashboard.dto.Category;
import com.podkor.ukrdashboard.service.data.DataService;
import com.podkor.ukrdashboard.service.menu.CategoriesMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.podkor.ukrdashboard.dto.Category.FOOTBALL;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Controller
@RequestMapping("/app/data")
@RequiredArgsConstructor
public class DataController {

    private static final Category DEFAULT_CATEGORY = FOOTBALL;

    private final DataService dataService;
    private final CategoriesMenuService categoriesMenuService;

    @GetMapping("/{tabId}")
    public String getTabData(
        @PathVariable Long tabId,
        Model model
    ) {
        model.addAttribute("htmlData", dataService.getHtmlData(tabId));
        return "index";
    }

    @GetMapping
    public String getData(
        Model model,
        @RequestParam(required = false) String category
    ) {
        Category dataCategory = isEmpty(category) ? DEFAULT_CATEGORY : Category.valueOf(category.toUpperCase());
        model.addAttribute("categoriesMenu", categoriesMenuService.createCategoriesMenuDiv(dataCategory));
        model.addAttribute("htmlData", dataService.collectHtmlData(dataCategory));
        return "index";
    }

    @GetMapping("new")
    public String addNewData(
        Model model
    ) {
//        Category dataCategory = isEmpty(category) ? DEFAULT_CATEGORY : Category.valueOf(category.toUpperCase());
//        model.addAttribute("categoriesMenu", categoriesMenuService.createCategoriesMenuDiv(dataCategory));
//        model.addAttribute("htmlData", dataService.collectHtmlData(dataCategory));
        return "newData";
    }
}
