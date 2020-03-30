package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {

	@Step("Creating the User using the firstName : {0},lastName={1}, email= {2},programme={3}, courses={4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
			List<String> courses) {
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setProgramme(programme);
		student.setEmail(email);
		student.setCourses(courses);

		return SerenityRest.rest().given().spec(ReusableSpecifications.getGenericRequestSpec())
				.when().body(student).post().then();
	}

	@Step("Getting the student information with firstName : {0}")
	public HashMap<String, Object> getStudentinfoByFirstName(String firstName) {

		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";
		return SerenityRest.rest().given().when().get("/list").then().statusCode(200).extract()
				.path(p1 + firstName + p2);
	}

	@Step("Getting the student information with firstName : {0}")
	public ValidatableResponse getStudentinfoById(int studentId) {

		return SerenityRest.rest().given().when().get("/" + studentId).then().statusCode(404);
	}

	@Step("Updating the User using the firstName : {0},lastName={1}, email= {2},programme={3}, courses={4} ,studentid={5}")
	public ValidatableResponse updateStudent(String firstName, String lastName, String email, String programme,
			List<String> courses, int studentId) {

		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setProgramme(programme);
		student.setEmail(email);
		student.setCourses(courses);
		return SerenityRest.rest().given().spec(ReusableSpecifications.getGenericRequestSpec()).when().body(student).put("/" + studentId)
				.then();
	}

	@Step("Deleting the User using the studentid={0}")
	public ValidatableResponse deleteStudent(int studentId) {
		return SerenityRest.rest().given().when().delete("/" + studentId).then();

	}

}
