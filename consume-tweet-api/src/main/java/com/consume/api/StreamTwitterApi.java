package com.consume.api;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.Status;
import twitter4j.TwitterException;
import com.consume.entity.Tweet;
import com.consume.service.TweetService;
import com.consume.twitter.TwitterConsumingConfig;

@RestController
@RequestMapping(value = "/twitterOperations")
public class StreamTwitterApi {
	@Autowired
	TweetService tweetService;
	
	Tweet tweet = new Tweet();
	TwitterConsumingConfig twitterIni = new TwitterConsumingConfig();
	
	
	
		
		@GetMapping(value="/dbPopulation")
		public void insertInDatabase(@RequestParam(defaultValue="1500") int numberFollowers, @RequestParam(defaultValue="es") List<String> language){
			
			
			twitterIni.createSession();
		    try {
				twitterIni.saveTweets(language, numberFollowers,tweetService);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		@GetMapping(value="/stopPopulation")
		public String stopStreamTwitter(){
			
			twitterIni.getTwitterStream();
			twitterIni.deleteSession();
			
			return "Twitter Stream is off";
		}

}
