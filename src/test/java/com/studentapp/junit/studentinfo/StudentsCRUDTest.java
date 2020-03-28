package com.studentapp.junit.studentinfo;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {
	
	static String firstName="SMOKEUSER_FN"+TestUtils.getRandomValue();
	static String lastName="SMOKEUSER_LN"+TestUtils.getRandomValue();
	static String programme="computerScience";
	static String email=TestUtils.getRandomValue()+firstName+lastName+"@gmail.com";


	@Test
	@Title("This test will create a new student")
	public void test001()
	{
		ArrayList<String> courses= new ArrayList<String>();
		courses.add("Java");
		courses.add("C++");
		StudentClass student= new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setProgramme(programme);
		student.setEmail(email);
		student.setCourses(courses);
		
		SerenityRest
		.given()
		.contentType(ContentType.JSON)
		.when()
		.body(student)
		.post()
		.then()
		.statusCode(201);
		
	}
	
@Test
@Title("Verify the newly created student is added or not")
public void test002()
{
	SerenityRest
	.given()
	.when()
	.get("/list")
	.then()
    .log()
    .all()
    .statusCode(200);
}

}
