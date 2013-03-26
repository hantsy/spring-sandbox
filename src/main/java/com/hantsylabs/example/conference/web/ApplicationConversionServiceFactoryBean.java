package com.hantsylabs.example.conference.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.hantsylabs.example.conference.model.Conference;
import com.hantsylabs.example.conference.model.Signup;
import com.hantsylabs.example.conference.service.ConferenceService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
// @RooConversionService
public class ApplicationConversionServiceFactoryBean extends
		FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	@Autowired
	ConferenceService conferenceService;

	public Converter<Conference, String> getConferenceToStringConverter() {
		return new org.springframework.core.convert.converter.Converter<com.hantsylabs.example.conference.model.Conference, java.lang.String>() {
			@Override
			public String convert(Conference conference) {
				return new StringBuilder().append(conference.getName())
						.append(' ').append(conference.getDescription())
						.append(' ').append(conference.getStartedDate())
						.append(' ').append(conference.getEndedDate())
						.toString();
			}
		};
	}

	public Converter<Long, Conference> getIdToConferenceConverter() {
		return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.hantsylabs.example.conference.model.Conference>() {
			@Override
			public com.hantsylabs.example.conference.model.Conference convert(
					java.lang.Long id) {
				return conferenceService.findConference(id);
			}
		};
	}

	public Converter<String, Conference> getStringToConferenceConverter() {
		return new org.springframework.core.convert.converter.Converter<java.lang.String, com.hantsylabs.example.conference.model.Conference>() {
			@Override
			public com.hantsylabs.example.conference.model.Conference convert(
					String id) {
				return getObject().convert(getObject().convert(id, Long.class),
						Conference.class);
			}
		};
	}

	public Converter<Signup, String> getSignupToStringConverter() {
		return new org.springframework.core.convert.converter.Converter<com.hantsylabs.example.conference.model.Signup, java.lang.String>() {
			@Override
			public String convert(Signup signup) {
				return new StringBuilder().append(signup.getFirstName())
						.append(' ').append(signup.getLastName()).append(' ')
						.append(signup.getEmail()).append(' ')
						.append(signup.getPhone()).toString();
			}
		};
	}

	public Converter<Long, Signup> getIdToSignupConverter() {
		return new org.springframework.core.convert.converter.Converter<Long, com.hantsylabs.example.conference.model.Signup>() {
			@Override
			public com.hantsylabs.example.conference.model.Signup convert(
					Long id) {
				return conferenceService.findSignup(id);
			}
		};
	}

	public Converter<String, Signup> getStringToSignupConverter() {
		return new org.springframework.core.convert.converter.Converter<java.lang.String, com.hantsylabs.example.conference.model.Signup>() {
			@Override
			public com.hantsylabs.example.conference.model.Signup convert(
					String id) {
				return getObject().convert(getObject().convert(id, Long.class),
						Signup.class);
			}
		};
	}

	public void installLabelConverters(FormatterRegistry registry) {
		registry.addConverter(getConferenceToStringConverter());
		registry.addConverter(getIdToConferenceConverter());
		registry.addConverter(getStringToConferenceConverter());
		registry.addConverter(getSignupToStringConverter());
		registry.addConverter(getIdToSignupConverter());
		registry.addConverter(getStringToSignupConverter());
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		installLabelConverters(getObject());
	}
}
