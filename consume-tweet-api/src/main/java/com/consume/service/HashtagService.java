package com.consume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consume.entity.Hashtag;
import com.consume.repository.HashtagRepository;

@Service
public class HashtagService {

@Autowired
HashtagRepository hashtagRepository;
	

public Hashtag save(Hashtag hashtag) {
	return hashtagRepository.save(hashtag);
}

public List<Hashtag> readAll(){
	return hashtagRepository.findAll();
}
}
