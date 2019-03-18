package com.dicka.backendandroid;

import com.dicka.backendandroid.entity.Post;
import com.dicka.backendandroid.entity.PostComment;
import com.dicka.backendandroid.entity.PostCommentUser;
import com.dicka.backendandroid.entity.Product;
import com.dicka.backendandroid.repo.PostCommentRepository;
import com.dicka.backendandroid.repo.PostCommentUserRepository;
import com.dicka.backendandroid.repo.PostRepository;
import com.dicka.backendandroid.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BackendAndroidApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAndroidApplication.class, args);
	}

}

@Component
class CommandLineRunnerData implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostCommentRepository postCommentRepository;

	@Autowired
	private PostCommentUserRepository postCommentUserRepository;

	@Override
	public void run(String... args) throws Exception {

//		Post post = new Post();
//		post.setTitle("my first postingan");
//		post.getComments().add(new PostComment("first review"));
//		post.getComments().add(new PostComment("second review"));
//		post.getComments().add(new PostComment("third review"));
//		post.getComments().add(new PostComment("four review"));
//		post.getComments().add(new PostComment("five review"));
		//postRepository.save(post);

		Post post = new Post();
		post.setTitle("first title");
		PostComment postComment1 = new PostComment();
		PostComment postComment2 = new PostComment();
		postComment1.setReview("review first title");
		postComment2.setReview("review first title");


		post.getComments().add(postComment1);
		post.getComments().add(postComment2);
		postRepository.save(post);

	}
}
