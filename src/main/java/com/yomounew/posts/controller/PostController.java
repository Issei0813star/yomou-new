package com.yomounew.posts.controller;

import com.yomounew.posts.application.dto.requests.GetTimelineRequest;
import com.yomounew.posts.application.dto.responses.GetTimelineResponse;
import com.yomounew.posts.application.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostsService postsService;

    @PostMapping("/timeline")
    public GetTimelineResponse getTimeline(@RequestBody GetTimelineRequest req) {
        return postsService.getTimeline(req);
    }
}
