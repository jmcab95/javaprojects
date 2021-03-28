package com.consume.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.TwitterException;
import com.consume.service.HashtagService;
import com.consume.service.TweetService;
import com.consume.twitter.TwitterConsumingConfig;

@RestController
@RequestMapping(value = "/TwitterOperations")
public class StreamTwitterApi {
	@Autowired
	TweetService tweetService;
	@Autowired
	HashtagService hashtagService;

	TwitterConsumingConfig twitterIni = new TwitterConsumingConfig();

	@GetMapping(value = "/PopulateDB")
	public void insertInDatabase(@RequestParam(defaultValue = "1500") int numberFollowers,
			@RequestParam(defaultValue = "es") List<String> language) {

		twitterIni.createSession();
		try {
			twitterIni.saveTweets(language, numberFollowers, tweetService, hashtagService);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@GetMapping(value = "/StopPopulateDB")
	public String stopStreamTwitter() {

		twitterIni.getTwitterStream();
		twitterIni.deleteSession();

		return "Twitter Stream is off";
	}

}
