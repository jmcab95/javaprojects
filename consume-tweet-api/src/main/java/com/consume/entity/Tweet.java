package com.consume.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tweet implements Serializable {

	private static final long serialVersionUID = 7289706368558389633L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String user;
	
	@Lob
	private String text;
	private String location;
	private String language;
	private boolean validatedTweet;
	private int numberFollowers;

	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.REFRESH}, orphanRemoval = true)
	@JoinColumn(name = "tweetId")
	private List<Hashtag> hashtags;
	
	public Tweet() {}
	
	public Tweet(String user, String text, String location, String language, boolean validatedTweet,
			int numberFollowers) {
		
		this.user = user;
		this.text = text;
		this.location = location;
		this.language = language;
		this.validatedTweet = validatedTweet;
		this.numberFollowers = numberFollowers;
		
	}

	

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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
		return "Tweet [id=" + id + ", user=" + user + ", text=" + text + ", location=" + location + ", language="
				+ language + ", validatedTweet=" + validatedTweet + ", numberFollowers=" + numberFollowers
				+ ", hashtags=" + hashtags + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
