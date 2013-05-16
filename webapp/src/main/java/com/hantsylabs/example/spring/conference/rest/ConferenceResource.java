package com.hantsylabs.example.spring.conference.rest;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hantsylabs.example.spring.conference.model.Conference;
import com.hantsylabs.example.spring.conference.service.ConferenceService;

@RequestMapping("/conferences")
@Controller
// @RooWebScaffold(path = "conferences", formBackingObject = Conference.class)
public class ConferenceResource {

	@Autowired
	ConferenceService conferenceService;

	@RequestMapping(method = RequestMethod.GET, value = "/upcoming")
	public ResponseEntity<List<Conference>> upcoming() {
		List<Conference> result = conferenceService.findUpcomingConferences();
		return new ResponseEntity<List<Conference>>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/today")
	public ResponseEntity<List<Conference>> today() {
		List<Conference> result = conferenceService.findTodayConferences();
		return new ResponseEntity<List<Conference>>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/past/{pastDate}")
	public ResponseEntity<List<Conference>> past(
			@PathVariable("pastDate") @DateTimeFormat(style = "M-") @Past Date past) {
		List<Conference> result = conferenceService.findPastConferences(past);
		return new ResponseEntity<List<Conference>>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{slug}")
	public ResponseEntity<Conference> getBySlug(
			@PathVariable("slug") @Size(min = 1) @NotNull String id) {

		Conference conference = conferenceService.findConferenceBySlug(id);

		if (conference == null) {
			return new ResponseEntity<Conference>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Conference>(conference, HttpStatus.OK);
	}

}
