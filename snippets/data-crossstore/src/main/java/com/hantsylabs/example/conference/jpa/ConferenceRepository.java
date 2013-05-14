package com.hantsylabs.example.conference.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.conference.model.Conference;


@Repository
public interface ConferenceRepository extends 
		JpaRepository<Conference, Long> {

}
