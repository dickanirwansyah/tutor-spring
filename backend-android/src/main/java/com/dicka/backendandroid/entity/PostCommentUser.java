package com.dicka.backendandroid.entity;

import javax.persistence.*;

@Entity
@Table(name = "post_comment_user")
public class PostCommentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public PostCommentUser(){}

    public PostCommentUser(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
