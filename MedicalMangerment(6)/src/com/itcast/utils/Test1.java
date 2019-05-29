package com.itcast.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.entity.Good;

public class Test1 {
	public static void main(String[] args) {
		
		
		
		Good oneGood = new Good();
		oneGood.setGname("小米");
		oneGood.setPrice(123.1);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(oneGood);
			System.out.println(json);
			
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
