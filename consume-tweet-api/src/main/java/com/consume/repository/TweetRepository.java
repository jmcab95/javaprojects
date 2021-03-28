package com.consume.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.consume.entity.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	
	@Query("SELECT t FROM Tweet t WHERE t.validatedTweet = ?1")
	public List<Tweet> getValidatedTweet(boolean validated);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Tweet t SET t.validatedTweet=true where t.id =?1")
	public void validateTweet(Long tweetId);
}
