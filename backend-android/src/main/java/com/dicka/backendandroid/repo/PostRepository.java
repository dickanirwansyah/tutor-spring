package com.dicka.backendandroid.repo;

import com.dicka.backendandroid.entity.Post;
import com.dicka.backendandroid.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


}
