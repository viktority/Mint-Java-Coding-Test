package com.viktority.mint.services.impls;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.viktority.mint.pojo.Payload;
import com.viktority.mint.pojo.RestResponseObject;
import com.viktority.mint.pojo.ReturnObject;
import com.viktority.mint.services.CardDetailsService;

@Service
public class CardDetailsServiceImpl implements CardDetailsService {

	@Autowired
	private Environment env;

	@Autowired
	RestTemplate restTemplate;

	@Value("${binlist.data.url}")
	private String URI;

	@Override
	public ReturnObject verifyCard(String cardNumber) {
		// environment.getProperty("email.verification.token.expiration_time");
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
		return returnVal;
	}

}
