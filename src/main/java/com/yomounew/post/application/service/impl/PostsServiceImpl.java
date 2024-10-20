package com.yomounew.post.application.service.impl;

import com.yomounew.post.application.dto.PostDto;
import com.yomounew.post.application.dto.requests.GetPostsRequest;
import com.yomounew.post.application.dto.responses.GetPostsResponse;
import com.yomounew.post.application.service.PostsService;
import com.yomounew.post.domain.model.entity.Post;
import com.yomounew.post.domain.repository.PostRepository;
import com.yomounew.user.application.dto.UserDto;
import com.yomounew.user.domain.model.entity.User;
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

    public GetPostsResponse getPosts(GetPostsRequest req) {
        int pageNumber = Optional.ofNullable(req.getPageNumber()).orElse(0);
        int pageSize = Optional.ofNullable(req.getPageSize()).orElse(10);

        Page<Post> page = postRepository.findAllByOrderByPostedAtDesc(PageRequest.of(pageNumber, pageSize));
        List<Post> postEntities = page.getContent();

        List<PostDto> posts = postEntities.stream()
                .map(e -> {
                    User user = e.getAuthor();
                    UserDto author = new UserDto(user.getId(), user.getUserName());
                    return new PostDto(e.getId(), author, e.getReviewPoint(), e.getBookTitle(), e.getHeader(), e.getContent(), ConvertTimestampUtils.convertTimestampToString(e.getPostedAt()));
                })
                .toList();

        return new GetPostsResponse(posts, pageNumber, posts.size());
    }

}
