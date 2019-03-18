package com.dicka.backendandroid.controller;

import com.dicka.backendandroid.entity.Post;
import com.dicka.backendandroid.entity.PostComment;
import com.dicka.backendandroid.repo.PostCommentRepository;
import com.dicka.backendandroid.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/post-comment")
public class PostCommentController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @GetMapping(value = "/posts")
    public List<Post> posts(){
        return postRepository.findAll();
    }

    @GetMapping
    public List<PostComment> postComments(){
        return postCommentRepository.findAll();
    }
}
