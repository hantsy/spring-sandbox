package com.hantsylabs.example.spring.model;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditEntityListener {
	@PrePersist
	public void prePersist(Object o) {
		final Date _createdDate = new Date();

		if (o instanceof Signup) {
			Signup signup = (Signup) o;
			signup.setCreatedDate(_createdDate);
			signup.setModifiedDate(_createdDate);
			signup.setCreatedBy(SecurityUtils.getCurrentUser());
			signup.setModifiedBy(SecurityUtils.getCurrentUser());
		}

	}

	@PreUpdate
	public void preUpdate(Object o) {
		if (o instanceof Signup) {
			Signup signup = (Signup) o;
			signup.setModifiedDate(new Date());
			signup.setModifiedBy(SecurityUtils.getCurrentUser());
		}
	}
}
