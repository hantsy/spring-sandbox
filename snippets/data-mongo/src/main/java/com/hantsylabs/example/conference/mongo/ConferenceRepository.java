package com.hantsylabs.example.conference.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.conference.model.Conference;

@Repository
public interface ConferenceRepository extends MongoRepository<Conference, String>,
		QueryDslPredicateExecutor<Conference> {

}
