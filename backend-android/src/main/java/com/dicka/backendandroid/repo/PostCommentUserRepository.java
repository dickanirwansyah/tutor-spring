package com.dicka.backendandroid.repo;

import com.dicka.backendandroid.entity.PostComment;
import com.dicka.backendandroid.entity.PostCommentUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentUserRepository extends JpaRepository<PostCommentUser, Long> {
}
