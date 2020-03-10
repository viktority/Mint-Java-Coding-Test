package com.viktority.mint.services.impls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.viktority.mint.models.CardDetails;
import com.viktority.mint.pojo.Payload;
import com.viktority.mint.pojo.RestResponseObject;
import com.viktority.mint.pojo.ReturnObject;
import com.viktority.mint.pojo.StatsReturn;
import com.viktority.mint.repositories.CardDetailsRepository;
import com.viktority.mint.services.CardDetailsService;
import static java.util.stream.Collectors.*;

@Service
public class CardDetailsServiceImpl implements CardDetailsService {

	@Autowired
	private Environment env;

	@Autowired
	CardDetailsRepository cdr;

	@Autowired
	RestTemplate restTemplate;

	@Value("${binlist.data.url}")
	private String URI;

	@Override
	public ReturnObject verifyCard(String cardNumber) {
		ReturnObject returnVal = new ReturnObject();
		String URL = URI + cardNumber;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, String> params = new HashMap<>();

		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestResponseObject r = restTemplate.exchange(URL, HttpMethod.GET, entity, RestResponseObject.class, params)
				.getBody();

		returnVal.setSuccess(r != null);
		Payload p = new ModelMapper().map(r, Payload.class);
		p.setBank(r.getBank().getName());
		returnVal.setPayload(p);
		CardDetails cd = new CardDetails(cardNumber);
		cdr.save(cd);
		return returnVal;
	}

	@Override
	public StatsReturn getStats(int start, int limit) {
		Pageable p = PageRequest.of(start, limit);
		Page<CardDetails> pcds = cdr.findAll(p);

		List<CardDetails> cds = pcds.getContent();

		StatsReturn returnVal = new StatsReturn();
		returnVal.setLimit(limit);
		returnVal.setStart(start);
		returnVal.setSize(cds.size());
		returnVal.setSuccess(!cds.isEmpty());
		Map<String, Long> m = cds.stream().collect(groupingBy(CardDetails::getCardNumber, counting()));
		returnVal.setPayload(m);

		return returnVal;
	}

}
