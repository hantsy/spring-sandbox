package com.hantsylabs.example.conference.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.conference.model.Contact;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

}
