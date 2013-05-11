package com.hantsylabs.example.conference.repository.specs;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hantsylabs.example.conference.model.Conference;

public class InProgressConferences implements Specification<Conference> {

	@Override
	public Predicate toPredicate(Root<Conference> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<Timestamp> currentTimestamp = cb.currentTimestamp();
		return cb.and(cb.greaterThan(root.get("endedDate").as(Date.class),
				currentTimestamp), cb.lessThan(
				root.get("startedDate").as(Date.class), currentTimestamp));
	}
}
