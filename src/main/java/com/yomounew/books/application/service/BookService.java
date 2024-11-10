package com.yomounew.books.application.service;

import com.yomounew.books.application.dto.BookDto;

public interface BookService {
    BookDto searchBookByKeyword(String keyword);
}
