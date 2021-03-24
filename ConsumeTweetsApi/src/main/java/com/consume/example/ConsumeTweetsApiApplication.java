package com.consume.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.consume.twitter.TwitterStreamGenerate;

import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@SpringBootApplication
public class ConsumeTweetsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumeTweetsApiApplication.class, args);
		StatusListener listener = new StatusListener(){
	        public void onStatus(Status status) {
	        	if(status.getUser().getFollowersCount()>=1500) {
	        	
	        	System.out.println(status.getUser().getName() + " : " + status.getText() +"Location: "+ status.getUser().getLocation() +" Followers:"+ status.getUser().getFollowersCount());
	        	for(HashtagEntity Hash : status.getHashtagEntities()) {
	        		System.out.println("Hashtag:" +  Hash.getText());
	        	}
	        	}
	        }
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }
			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}
	    };
	    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	    FilterQuery fq = new FilterQuery();
	    String[] lang= {"es,it,fr"};
	    String[] trackword= {"a"};
	    fq.language(lang);
	    fq.track(trackword);
	    twitterStream.addListener(listener);
	    
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
	    twitterStream.filter(fq);
	}

}
