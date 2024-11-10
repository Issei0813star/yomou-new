package com.yomounew.posts.application.dto.requests;

import lombok.Data;

@Data
public class GetTimelineRequest {

    private final Integer pageNumber;
    private final Integer pageSize;

    //TODO condtiionちゃんとクラス作る
    private final Object condition;
}
