package com.consume.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.consume.entity.Hashtag;
import com.consume.entity.HashtagNameOnly;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>{

	@Query("SELECT h.hashtagName as hashtagName, count(*) as number FROM Hashtag h GROUP BY hashtagName ORDER BY number DESC")
	List<HashtagNameOnly> findTopNHashtags(Pageable pageable);

}
