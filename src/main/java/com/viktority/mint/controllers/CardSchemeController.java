package com.viktority.mint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.viktority.mint.pojo.ReturnObject;
import com.viktority.mint.services.CardDetailsService;

@RestController
@RequestMapping("/card-scheme")
public class CardSchemeController {
	// card-scheme/verify/
	@Autowired
	CardDetailsService cds;

	@GetMapping(value = "/verify/{cardNumber}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReturnObject> verify(@PathVariable("cardNumber") String cardNumber) {
		ReturnObject prp = cds.verifyCard(cardNumber);

		return ResponseEntity.status(HttpStatus.OK).body(prp);
	}
}
