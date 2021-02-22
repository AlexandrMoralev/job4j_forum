package ru.job4j.forum.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class Comment implements Comparable<Comment> {

    private Long id;
    private String text;
    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    private Integer postId;
//    private Integer authorId;
    private User author;

    public static Comment of(String text,
                             Integer postId,
//                             Integer userId
                             User user
    ) {
        Comment comment = new Comment();
        comment.text = text;
        comment.createdAt = LocalDateTime.now();
        comment.postId = postId;
//        comment.authorId = userId;
        comment.author = user;
        return comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
//
//    public Integer getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(Integer authorId) {
//        this.authorId = authorId;
//    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return text.equals(comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("text='" + text + "'")
                .add("createdAt=" + createdAt)
//                .add("updatedAt=" + updatedAt)
                .add("postId=" + postId)
//                .add("authorId=" + authorId)
                .add("author=" + author)
                .toString();
    }

    @Override
    public int compareTo(Comment o) {
        int comparedByCreationDate = this.createdAt.compareTo(o.createdAt);
        if (comparedByCreationDate == 0) {
            return this.text.compareTo(o.text);
        }
        return comparedByCreationDate;
    }
}
