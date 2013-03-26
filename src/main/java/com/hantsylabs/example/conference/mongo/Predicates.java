package com.hantsylabs.example.conference.mongo;

import java.util.Calendar;

import com.mysema.query.types.Predicate;

public class Predicates {

	public Predicate expiredSignup() {
		QSignupObject _signup = QSignupObject.signupObject;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);

		return _signup.createdDate.before(cal.getTime());
	}

}
