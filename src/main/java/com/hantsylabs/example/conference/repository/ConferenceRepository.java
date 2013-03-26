package com.hantsylabs.example.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.conference.model.Conference;

@Repository
// @RooJpaRepository(domainType = Conference.class)
public interface ConferenceRepository extends JpaRepository<Conference, Long>,
		JpaSpecificationExecutor<Conference> {

	Conference findBySlug(String id);

	// @Query("from Conference where name=?")
	// public Conference searchByConferenceName(String name);
	//
	// @Query("from Conference where name=:name")
	// public Conference searchByNamedConferenceName(@Param("name") String
	// name);
	//
	// @Query
	// public Conference searchByMyNamedQuery(String name);

}
