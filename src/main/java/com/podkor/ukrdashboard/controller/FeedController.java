package com.podkor.ukrdashboard.controller;

import com.podkor.ukrdashboard.service.feed.FeedService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final List<FeedService> feedServices;

    @PostMapping("/updateFeed")
    @ApiOperation("Update feed manually")
    public void updateFeedManually() {
        feedServices.forEach(FeedService::updateFeed);
    }
}