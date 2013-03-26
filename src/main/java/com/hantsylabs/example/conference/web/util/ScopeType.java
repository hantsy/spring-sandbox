package com.hantsylabs.example.conference.web.util;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.web.context.WebApplicationContext;

public class ScopeType {
	public static final String VIEW = "view";
	public static final String SESSION = WebApplicationContext.SCOPE_SESSION;
	public static final String REQUEST = WebApplicationContext.SCOPE_REQUEST;
	public static final String PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
	public static final String SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
	public static final String APPLICATION = WebApplicationContext.SCOPE_APPLICATION;
	public static final String GLOBAL_SESSION = WebApplicationContext.SCOPE_GLOBAL_SESSION;
}
