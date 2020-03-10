package com.viktority.mint.services;

import org.springframework.stereotype.Service;


import com.viktority.mint.pojo.ReturnObject;
import com.viktority.mint.pojo.StatsReturn;

@Service
public interface CardDetailsService {

	ReturnObject verifyCard(String cardNumber);

	StatsReturn getStats(int start, int limit);

}
