package com.hantsylabs.example.conference.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.conference.model.Signup;

@Repository
public interface SignupRepository extends MongoRepository<Signup, String>,
		QueryDslPredicateExecutor<Signup> {

}
