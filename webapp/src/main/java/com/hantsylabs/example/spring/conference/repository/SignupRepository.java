package com.hantsylabs.example.spring.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.spring.conference.model.Signup;

@Repository
public interface SignupRepository extends JpaRepository<Signup, Long> {
}
