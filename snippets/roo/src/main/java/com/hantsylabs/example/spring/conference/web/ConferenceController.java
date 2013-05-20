package com.hantsylabs.example.spring.conference.web;

import com.hantsylabs.example.spring.conference.model.Conference;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/conferences")
@Controller
@RooWebScaffold(path = "conferences", formBackingObject = Conference.class)
public class ConferenceController {
}
