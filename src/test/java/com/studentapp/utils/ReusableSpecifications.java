package com.studentapp.utils;

import com.ibm.icu.impl.duration.TimeUnit;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder requestspec;
	public static ResponseSpecBuilder responsespec;

	public static RequestSpecification requestSpecification;
	public static ResponseSpecification responseSpecification;

	
	public static RequestSpecification getGenericRequestSpec()
	{
		requestspec= new RequestSpecBuilder();
		requestspec.setContentType(ContentType.JSON);
		requestSpecification=requestspec.build();
		return requestSpecification;
		
	}
	
	public static ResponseSpecification getGenericResponseSpec()
	{
		responsespec= new ResponseSpecBuilder();
		responsespec.expectHeader("Content-Type","application/json;charset=UTF-8");
		responsespec.expectHeader("Transfer-Encoding","Chunked");
		responsespec.expectResponseTime(lessThan(5L));
		responseSpecification=responsespec.build();
		return responseSpecification;
	}

}
