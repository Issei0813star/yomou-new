package com.yomounew.posts.application.service.impl;

import com.yomounew.books.application.dto.BookDto;
import com.yomounew.books.application.service.BookService;
import com.yomounew.posts.application.dto.PostDto;
import com.yomounew.posts.application.dto.requests.GetTimelineRequest;
import com.yomounew.posts.application.dto.responses.GetTimelineResponse;
import com.yomounew.posts.application.service.PostsService;
import com.yomounew.posts.domain.model.entity.Post;
import com.yomounew.posts.domain.repository.PostRepository;
import com.yomounew.users.application.dto.UserDto;
import com.yomounew.users.domain.model.entity.User;
import com.yomounew.utils.ConvertTimestampUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final PostRepository postRepository;
    private final BookService bookService;

    public GetTimelineResponse getTimeline(GetTimelineRequest req) {
        int pageNumber = Optional.ofNullable(req.getPageNumber()).orElse(0);
        int pageSize = Optional.ofNullable(req.getPageSize()).orElse(10);

        Page<Post> page = postRepository.findAllByOrderByPostedAtDesc(PageRequest.of(pageNumber, pageSize));
        List<Post> postEntities = page.getContent();

        List<PostDto> posts = postEntities.stream()
                .map(e -> {
                    User user = e.getAuthor();
                    UserDto author = new UserDto(user.getId(), user.getUserName());
                    BookDto book = bookService.searchBookByKeyword(e.getBookTitle());
                    System.out.println(book);

                    return new PostDto(e.getId(), author, e.getReviewPoint(), e.getBookTitle(), e.getHeader(), e.getContent(), ConvertTimestampUtils.convertTimestampToString(e.getPostedAt()), book);
                })
                .toList();

        return new GetTimelineResponse(posts, pageNumber, posts.size());
    }

}
