package com.hantsylabs.example.conference.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hantsylabs.example.conference.model.Signup;
import com.hantsylabs.example.conference.service.ConferenceService;

@RequestMapping("/signups")
@Controller
// @RooWebScaffold(path = "signups", formBackingObject = Signup.class)
public class SignupResource {

	@Autowired
	ConferenceService conferenceService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Signup> create(@Valid Signup signup) {
		conferenceService.saveSignup(signup);
		return new ResponseEntity<Signup>(signup, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Signup> show(
			@PathVariable("id") @Size(min = 1) @NotNull Long id) {

		Signup _signup = conferenceService.findSignup(id);

		if (_signup == null) {
			return new ResponseEntity<Signup>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Signup>(_signup, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Signup> update(@Valid Signup signup) {
		Signup _signup = conferenceService.updateSignup(signup);
		return new ResponseEntity<Signup>(_signup, HttpStatus.OK);
	}

}
