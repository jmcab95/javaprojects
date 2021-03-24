package com.consume.entity;


import org.springframework.data.annotation.Id;

public class Hashtag {
	
	@Id
	private String id;
	private String hashtagName;
	
	public Hashtag() {}
	
	public Hashtag(String id,String hashtagName) {
		this.id=id;
		this.hashtagName=hashtagName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHashtagName() {
		return hashtagName;
	}

	public void setHashtagName(String hashtagName) {
		this.hashtagName = hashtagName;
	}

	@Override
	public String toString() {
		return "Hashtag [id=" + id + ", hashtagName=" + hashtagName + "]";
	}
	
	

}
