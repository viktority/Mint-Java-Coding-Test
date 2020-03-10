package com.viktority.mint.pojo;

import lombok.Data;

@Data
public class RestResponseObject {
	private String scheme;
	private String type;
	private String brand;
	private Country country;
	private Bank bank;
	private Number number;
}
