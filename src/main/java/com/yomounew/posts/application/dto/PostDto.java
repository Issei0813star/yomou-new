package com.yomounew.posts.application.dto;

import com.yomounew.books.application.dto.BookDto;
import com.yomounew.users.application.dto.UserDto;
import lombok.Data;

@Data
public class PostDto {

    private final Long id;
    private final UserDto author;
    private final int reviewPoint;
    private final String bookTitle;
    private final String header;
    private final String content;
    private final String postedAt;
    private final BookDto book;
}
