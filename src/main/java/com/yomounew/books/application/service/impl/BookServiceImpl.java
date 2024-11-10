package com.yomounew.books.application.service.impl;

import com.yomounew.books.application.dto.BookDto;
import com.yomounew.books.application.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    public BookDto searchBookByKeyword(String keyword) {
        WebClient webClient = WebClient.create("https://www.googleapis.com");

        String url = "/books/v1/volumes?q=" + keyword + "&maxResults=1&startIndex=0&key=AIzaSyDsjLIOuCNKJNdPkRv83UNtI9meoB1NhPM";

        ResponseEntity<String> res = webClient.get()
                .uri(url)
                .retrieve()
                .toEntity(String.class)
                .block();

        if (res == null || res.getBody() == null) {
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(res.getBody());
            JsonNode volumeInfoNode = rootNode.path("items").get(0).path("volumeInfo");

            List<String> authors = new ArrayList<>();
            JsonNode authorsNode = volumeInfoNode.path("authors");
            for (JsonNode authorNode : authorsNode) {
                authors.add(authorNode.asText());
            }

            List<String> categories = new ArrayList<>();
            JsonNode categoriesNode = volumeInfoNode.path("categories");
            for (JsonNode categoryNode : categoriesNode) {
                categories.add(categoryNode.asText());
            }

            return new BookDto(
                    volumeInfoNode.path("title").asText(),
                    volumeInfoNode.path("subtitle").asText(),
                    authors,
                    volumeInfoNode.path("publisher").asText(),
                    categories,
                    volumeInfoNode.path("imageLinks").path("smallThumbnail").asText(),
                    volumeInfoNode.path("imageLinks").path("thumbnail").asText()
            );

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
