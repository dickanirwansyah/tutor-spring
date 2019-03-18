package com.dicka.backendandroid.repo;

import com.dicka.backendandroid.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long>{
}
