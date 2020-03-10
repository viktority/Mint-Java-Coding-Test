package com.viktority.mint.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.viktority.mint.models.CardDetails;

@Repository
public interface CardDetailsRepository extends PagingAndSortingRepository<CardDetails, Long> {

}
