package com.forum;

import com.forum.dao.*;
import com.forum.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
/*
	@Bean
	public CommandLineRunner commandLineRunner(UserDao userDao, TvshowDao tvshowDao, CategoryDao categoryDao, ReviewDao reviewDao, CommentDao commentDao){
		return runner ->
				//user(userDao);
				//tvshow(tvshowDao, categoryDao);
				//category(categoryDao);
				userTvshowReview(userDao,tvshowDao,reviewDao, commentDao, categoryDao);
	}

 */

	private void userTvshowReview(UserDao userDao, TvshowDao tvshowDao, ReviewDao reviewDao, CommentDao commentDao, CategoryDao categoryDao) {
		Tvshow tvshow = tvshowDao.findByIdWithEverything(7);
		System.out.println(tvshow.getReviews().size());
	}


}
