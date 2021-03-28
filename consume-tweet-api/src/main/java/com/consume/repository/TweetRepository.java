package com.consume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.consume.entity.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	
	@Query("SELECT t FROM Tweet t WHERE t.validatedTweet = :validatedStatus")
	public List<Tweet> getValidatedTweet(@Param("validatedStatus")boolean validatedStatus);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Tweet t SET t.validatedTweet=true where t.id = :tweetId")
	public void validateTweet(@Param("tweetId")Long tweetId);
}
