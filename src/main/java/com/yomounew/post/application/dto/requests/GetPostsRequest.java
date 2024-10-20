package com.yomounew.post.application.dto.requests;

import lombok.Data;

@Data
public class GetPostsRequest {

    private final Integer pageNumber;
    private final Integer pageSize;

    //TODO condtiionちゃんとクラス作る
    private final Object condition;
}
