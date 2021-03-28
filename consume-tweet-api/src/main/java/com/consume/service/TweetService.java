package com.consume.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consume.entity.Tweet;
import com.consume.repository.HashtagRepository;
import com.consume.repository.TweetRepository;

@Service
public class TweetService{
@Autowired
TweetRepository repository;
@Autowired 
HashtagRepository repositoryHashtag;

public Tweet save(Tweet tweet) {
	return repository.saveAndFlush(tweet);
}

public List<Tweet> readAll() {
	return repository.findAll();
}

public List<Tweet> getValidatedTweets(){
	return repository.getValidatedTweet(true);
}

public Optional<Tweet> getTweetById(Long tweetId) {
	return repository.findById(tweetId);
}

public void validateTweet(Long tweetId) {
	repository.validateTweet(tweetId);
	
}
}
