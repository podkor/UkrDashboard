package com.podkor.ukrdashboard.controller;

import com.podkor.ukrdashboard.service.feed.DataFeedService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final DataFeedService dataFeedService;

    @PostMapping("/updateFeed")
    @ApiOperation("Update feed manually")
    public void updateFeedManually() {
        dataFeedService.updateFeed();
    }
}