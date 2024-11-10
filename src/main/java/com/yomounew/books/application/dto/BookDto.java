package com.yomounew.books.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private final String title;
    private final String subTitle;
    private final List<String> authors;
    private final String publisher;
    private final List<String> categories;
    private final String smallThumbnail;
    private final String thumbnail;
}
