package com.hantsylabs.example.conference.repository.specs;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hantsylabs.example.conference.model.Conference;

public class UpcomingConferences implements Specification<Conference> {

	@Override
	public Predicate toPredicate(Root<Conference> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		return cb.greaterThan(root.get("startedDate").as(Date.class),
				cb.currentTimestamp());
	}
}
