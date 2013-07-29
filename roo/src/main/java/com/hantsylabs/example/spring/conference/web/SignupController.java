package com.hantsylabs.example.spring.conference.web;

import com.hantsylabs.example.spring.conference.model.Signup;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/signups")
@Controller
@RooWebScaffold(path = "signups", formBackingObject = Signup.class)
public class SignupController {
}
