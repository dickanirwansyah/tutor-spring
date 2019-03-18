package com.dicka.backendandroid.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post_comment")
public class PostComment {


    @Id
    @GeneratedValue
    private Long id;

    private String review;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PostCommentUser> postCommentUsers;

    public PostComment(){}

    public PostComment(String review){
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<PostCommentUser> getPostCommentUsers() {
        return postCommentUsers;
    }

    public void setPostCommentUsers(List<PostCommentUser> postCommentUsers) {
        this.postCommentUsers = postCommentUsers;
    }
}
