package com.podkor.ukrdashboard.controller;

import com.podkor.ukrdashboard.service.data.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @GetMapping("/{tabId}")
    public String showData(
        @PathVariable Long tabId,
        Model model
    ) {
        model.addAttribute("htmlData", dataService.getHtmlData(tabId));
        return "index";
    }
}