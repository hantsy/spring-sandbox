package com.hantsylabs.example.conference.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.conference.model.Conference;
import com.hantsylabs.example.conference.model.Signup;

@Repository
public interface SignupRepository extends MongoRepository<Signup, String>
,QueryDslPredicateExecutor<Signup>
{
	
	List<Signup> findByConference(Conference con);

}
