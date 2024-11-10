package com.yomounew.posts.application.dto.responses;

import com.yomounew.posts.application.dto.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class GetTimelineResponse {

    private final List<PostDto> posts;
    private final Integer pageNumber;
    private final Integer postCount;
}
