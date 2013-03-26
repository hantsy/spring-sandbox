package com.hantsylabs.example.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hantsylabs.example.conference.model.Signup;

@Repository
public interface SignupRepository extends JpaRepository<Signup, Long> {
}
