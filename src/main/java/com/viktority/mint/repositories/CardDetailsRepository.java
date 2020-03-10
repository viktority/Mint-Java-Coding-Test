package com.viktority.mint.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viktority.mint.models.CardDetails;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {

}
