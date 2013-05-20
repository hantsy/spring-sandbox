package com.hantsylabs.example.spring.conference.repository;

import com.hantsylabs.example.spring.conference.model.Conference;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Conference.class)
public interface ConferenceRepository {
}
