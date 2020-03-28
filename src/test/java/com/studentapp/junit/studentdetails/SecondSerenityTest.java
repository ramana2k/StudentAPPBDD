package com.studentapp.junit.studentdetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;

@RunWith(SerenityRunner.class)
public class SecondSerenityTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost:8899/student";
	}

	@Test
	public void getAllStudents() {
		SerenityRest.given().when().get("/list").then().log().all().statusCode(200);
	}

	@Test
	public void failtest() {
		SerenityRest.given().when().get("/list").then().log().all().statusCode(500);
	}

	@Pending
	@Test
	public void pendingTest() {

	}
	
	@Test
	public void errorTest() {
		System.out.println("Arthimetic exception"+5/0);

	}
	
	@Ignore
	@Test
	public void ignoreTest() {
	}
	
	@Test
	public void filedoesntexistTest() throws FileNotFoundException {
		File file= new File("/users/tmbadmin/test");
		FileInputStream fis= new FileInputStream(file);
	}
	
	@Test
	@Manual
	public void manualTest() {
		System.out.println("this is a manual test");

	}

}
