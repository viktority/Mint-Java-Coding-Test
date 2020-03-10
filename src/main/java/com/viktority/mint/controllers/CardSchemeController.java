package com.viktority.mint.controllers;

import com.viktority.mint.pojo.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.viktority.mint.pojo.ReturnObject;
import com.viktority.mint.pojo.StatsReturn;
import com.viktority.mint.services.CardDetailsService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/card-scheme")
public class CardSchemeController {
	// card-scheme/verify/
	@Autowired
	CardDetailsService cds;

	@Autowired
	private KafkaTemplate<String, Payload> kafkaTemplate;

	@Autowired
	private Gson jsonConverter;

	@Value("${kafka.topic}")
	private String kafkaTopic;

	@GetMapping(value = "/verify/{cardNumber}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ReturnObject> verify(@PathVariable("cardNumber") String cardNumber) {
		ReturnObject prp = cds.verifyCard(cardNumber);

		kafkaTemplate.send(kafkaTopic, prp.getPayload());
		return ResponseEntity.status(HttpStatus.OK).body(prp);
	}

	@GetMapping(value = "/stats", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StatsReturn> getStat(@RequestParam(value = "start", defaultValue = "0") int start,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {
		StatsReturn str = cds.getStats(start, limit);

		return ResponseEntity.status(HttpStatus.OK).body(str);
	}

}
