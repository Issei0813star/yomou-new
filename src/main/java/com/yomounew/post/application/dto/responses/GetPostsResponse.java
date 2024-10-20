package com.yomounew.post.application.dto.responses;

import com.yomounew.post.application.dto.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class GetPostsResponse {

    private final List<PostDto> posts;
    private final Integer pageNumber;
    private final Integer postCount;
}
