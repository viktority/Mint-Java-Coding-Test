package com.viktority.mint.services;

import org.springframework.stereotype.Service;

import com.viktority.mint.pojo.RestResponseObject;
import com.viktority.mint.pojo.ReturnObject;

@Service
public interface CardDetailsService {

	ReturnObject verifyCard(String cardNumber);

}
