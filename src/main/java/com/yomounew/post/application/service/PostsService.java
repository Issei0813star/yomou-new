package com.yomounew.post.application.service;

import com.yomounew.post.application.dto.PostDto;
import com.yomounew.post.application.dto.requests.GetPostsRequest;
import com.yomounew.post.application.dto.responses.GetPostsResponse;

import java.util.List;

public interface PostsService {

    public GetPostsResponse getPosts(GetPostsRequest req);
}
