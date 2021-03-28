package com.consume.twitter;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import com.consume.entity.Hashtag;
import com.consume.entity.Tweet;
import com.consume.service.HashtagService;
import com.consume.service.TweetService;

import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterConsumingConfig {

	private TwitterStream twitterStream;

	public void createSession() {
		this.twitterStream = new TwitterStreamFactory().getInstance();
	}

	public TwitterConsumingConfig() {

	}

	public void deleteSession() {
		this.twitterStream.shutdown();
	}

	public TwitterStream getTwitterStream() {
		return this.twitterStream;
	}

	public void setTwitterStream(TwitterStream twitterStream) {
		this.twitterStream = twitterStream;
	}

	public void saveTweets(List<String> language, int numberFollowers, TweetService tweetService, HashtagService hashtagService) throws TwitterException {

		
		this.twitterStream = new TwitterStreamFactory().getInstance();

		StatusListener listener = new StatusListener() {
			
			public void onStatus(Status status) {
				List<Hashtag> hashtags = new ArrayList<Hashtag>();
				
				if (status.getUser().getFollowersCount() >= numberFollowers) {
					
					for (HashtagEntity hash : status.getHashtagEntities()) {
						
						Hashtag tempHashtag = new Hashtag(hash.getText());
						Hashtag hashEnt=hashtagService.save(tempHashtag);

						hashtags.add(hashEnt);

					}
					
					
					System.out.println(hashtags.toString());
					Tweet tweet = new Tweet(status.getUser().getName(), status.getText(),
							status.getUser().getLocation(), status.getLang(), false,
							status.getUser().getFollowersCount());
					
					
					tweetService.save(tweet);

				}
				

			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onStallWarning(StallWarning sw) {
				System.out.println(sw.getMessage());

			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub

			}
		};

		FilterQuery fq = new FilterQuery();
		String[] keywords = { "a" };
		String[] lang = new String[(language.size())];
		
		for(int index=0; index<language.size(); index++) {
			lang[index]=language.get(index);
		}
		
		fq.language(lang);
		fq.track(keywords);

		twitterStream.addListener(listener);
		twitterStream.filter(fq);

	}

}
