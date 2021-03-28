package com.consume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.consume.entity.Hashtag;
import com.consume.entity.HashtagNameOnly;
import com.consume.repository.HashtagRepository;

@Service
public class HashtagService {

@Autowired
HashtagRepository hashtagRepository;
	

public Hashtag save(Hashtag hashtag) {
	return hashtagRepository.save(hashtag);
	
}

public List<HashtagNameOnly> readTopNHashtag(int topN){
	return hashtagRepository.findTopNHashtags(PageRequest.of(0,topN));
}

}
