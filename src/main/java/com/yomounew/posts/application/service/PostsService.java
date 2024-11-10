package com.yomounew.posts.application.service;

import com.yomounew.posts.application.dto.requests.GetTimelineRequest;
import com.yomounew.posts.application.dto.responses.GetTimelineResponse;

public interface PostsService {

    GetTimelineResponse getTimeline(GetTimelineRequest req);
}
