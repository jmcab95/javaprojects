package com.example.demo;

public class Greeting {

	
	private final long id;
	private final String content;
	private final String personalNumber;
	
	public Greeting(long id, String content, String personalNumber) {
		this.id=id;
		this.content=content;
		this.personalNumber=personalNumber;
	}
	
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	
	public String getPersonalNumber() {
		return personalNumber;
	}
}
