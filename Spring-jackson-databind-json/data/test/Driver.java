package com.spring.jackson.json.test;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String args[])
	{
		
		try {
			
			//Create object mapper
			
			ObjectMapper mapper=new ObjectMapper();
			
			//read JSON file and Map/Convert to java pojo
			// Data/Sample-lite.json
			
			Student obj=mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			System.out.println("First name :=" + obj.getFirstname());
			System.out.println("Last name = " + obj.getLastname());		

			// get nested object: array
			Address tempAddress = obj.getAddress();
			System.out.println("Street = " + tempAddress.getStreet());		
			System.out.println("City = " + tempAddress.getCity());		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
