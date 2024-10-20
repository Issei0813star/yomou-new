package com.yomounew.post.controller;

import com.yomounew.post.application.dto.requests.GetPostsRequest;
import com.yomounew.post.application.dto.responses.GetPostsResponse;
import com.yomounew.post.application.service.PostsService;
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

    @PostMapping("/posts")
    public GetPostsResponse getPosts(@RequestBody GetPostsRequest req) {
        return postsService.getPosts(req);
    }
}
