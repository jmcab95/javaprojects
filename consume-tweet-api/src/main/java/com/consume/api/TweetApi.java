package com.consume.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consume.entity.Hashtag;
import com.consume.entity.HashtagNameOnly;
import com.consume.entity.Tweet;
import com.consume.service.HashtagService;
import com.consume.service.TweetService;

@RestController
@RequestMapping(value = "/TwitterRepository")
public class TweetApi {

	@Autowired
	TweetService tweetService;
	@Autowired 
	HashtagService hashtagService;

	@GetMapping(value = "/getTweet/{tweetId}")
	public Tweet getOneTweet(@PathVariable long tweetId) {
		Tweet tweet = null;

		Optional<Tweet> OptionalTweet = tweetService.getTweetById(tweetId);

		if (OptionalTweet.isPresent()) {
			tweet = OptionalTweet.get();
		}

		return tweet;
	}

	@GetMapping(value = "/getAllTweets")
	public List<Tweet> getAllTweets() {
		return tweetService.readAll();
	}


	@PatchMapping(value = "/validateTweet/{tweetId}")
	public String validateTweet(@PathVariable long tweetId) {
		Tweet tweet;
		Optional<Tweet> tweetOptional = tweetService.getTweetById(tweetId);

		if (tweetOptional.isPresent()) {

			tweet = tweetOptional.get();
			if (!tweet.isValidatedTweet()) {
				tweetService.validateTweet(tweet.getId());
				return "Tweet with id: " + tweet.getId() + " has just been validated";
			} else {
				return "Tweet with id: " + tweet.getId() + " is already validated";
			}
		} else {
			return "Selected Tweet does not exist";
		}

	}

	@GetMapping(value = "/getAllValidatedTweets")
	public List<Tweet> getValidatedTweets() {
		return tweetService.getValidatedTweets();
	}
	
	@GetMapping(value="/getNTopHashtag/{nTop}")
	public List<HashtagNameOnly> getNTopHashtags(@PathVariable int nTop){
		return hashtagService.readTopNHashtag(nTop);
	}


}
