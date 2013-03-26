package com.hantsylabs.example.conference.mongo;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.mysema.query.types.Predicate;

@Repository
public interface SignupRepository extends MongoRepository<SignupObject, String>,
		QueryDslPredicateExecutor<SignupObject> {
	
	public List<SignupObject> findAll(Predicate p);

}
