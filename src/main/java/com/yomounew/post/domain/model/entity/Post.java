package com.yomounew.post.domain.model.entity;

import com.yomounew.user.domain.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "review_point", unique = true)
    private Integer reviewPoint;

    @Column(name = "header")
    private String header;

    @Column(name = "content")
    private String content;

    @Column(name = "posted_at")
    private Timestamp postedAt;
}
