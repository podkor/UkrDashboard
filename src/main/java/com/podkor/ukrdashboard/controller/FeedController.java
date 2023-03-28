package com.podkor.ukrdashboard.controller;

import com.podkor.ukrdashboard.service.feed.FeedService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping("/updateFeed")
    @ApiOperation("Update feed manually")
    public void updateFeedManually() {
        feedService.updateFeed();
    }
}