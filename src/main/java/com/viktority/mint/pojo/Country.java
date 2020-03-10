package com.viktority.mint.pojo;

import lombok.Data;

@Data
class Country {

	private String numeric;
	private String alpha2;
	private String name;
	private String emoji;
	private String currency;
	private int latitude;
	private int longitude;
}
