package com.yomounew.posts.domain.repository;

import com.yomounew.posts.domain.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByPostedAtDesc(Pageable pageable);
}
