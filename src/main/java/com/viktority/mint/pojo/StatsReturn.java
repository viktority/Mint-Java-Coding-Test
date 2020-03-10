package com.viktority.mint.pojo;

import java.util.Map;

import lombok.Data;

@Data
public class StatsReturn {
	private boolean success;
	private int start;
	private int limit;
	private int size;
	private Map<String, Long> payload;
}
