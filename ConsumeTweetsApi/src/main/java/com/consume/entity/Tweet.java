package com.consume.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Tweet {
	
	@Id
	private String id;
	
	private String user;
	private String text;
	private String location;
	private List<Hashtag> hashtagList;
	private boolean validatedTweet;
	private int numberFollowers;
	
	public Tweet() {}
	
	public Tweet(String id, String user, String text, String location, List<Hashtag> hashtagList,
			boolean validatedTweet,int numberFollowers) {
		
		this.id = id;
		this.user = user;
		this.text = text;
		this.location = location;
		this.hashtagList = hashtagList;
		this.validatedTweet = validatedTweet;
		this.numberFollowers=numberFollowers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Hashtag> getHashtagList() {
		return hashtagList;
	}

	public void setHashtagList(List<Hashtag> hashtagList) {
		this.hashtagList = hashtagList;
	}

	public boolean isValidatedTweet() {
		return validatedTweet;
	}

	public void setValidatedTweet(boolean validatedTweet) {
		this.validatedTweet = validatedTweet;
	}

	public int getNumberFollowers() {
		return numberFollowers;
	}

	public void setNumberFollowers(int numberFollowers) {
		this.numberFollowers = numberFollowers;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", user=" + user + ", text=" + text + ", location=" + location + ", hashtagList="
				+ hashtagList + ", validatedTweet=" + validatedTweet + ", numberFollowers=" + numberFollowers + "]";
	}

	
	
	

}
