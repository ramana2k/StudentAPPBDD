package com.studentapp.junit.studentinfo;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {
	
	static String firstName="SMOKEUSER_FN"+TestUtils.getRandomValue();
	static String lastName="SMOKEUSER_LN"+TestUtils.getRandomValue();
	static String programme="computerScience";
	static String email=TestUtils.getRandomValue()+firstName+lastName+"@gmail.com";
    static int studentId;

	@Steps
	StudentSerenitySteps steps;
	@Test
	@Title("This test will create a new student")
	public void test001()
	{
		ArrayList<String> courses= new ArrayList<String>();
		courses.add("Java");
		courses.add("C++");
		steps.createStudent(firstName, lastName, email, programme, courses).
		statusCode(201)
		.spec(ReusableSpecifications.getGenericResponseSpec());
		
	}

@Test
@Title("Verify the newly created student is added or not")
public void test002()

{

	HashMap<String,Object> map= steps.getStudentinfoByFirstName(firstName);
	assertThat(map,hasValue(firstName));
	studentId =(int) map.get("id");
}


@Test
@Title("Update the newly created student with _updated")
public void test003()


{
	
	String p1="findAll{it.firstName=='";
	String p2="'}.get(0)";
	ArrayList<String> courses= new ArrayList<String>();
	courses.add("Java");
	courses.add("C++");
	firstName=firstName+"_updated";

	steps.updateStudent(firstName,lastName,email,programme,courses,studentId).statusCode(200);
	HashMap<String,Object> map= steps.getStudentinfoByFirstName(firstName);
	assertThat(map,hasValue(firstName));
}

@Test
@Title("Delete the newly created student")
public void test004()

{
	steps.deleteStudent(studentId).statusCode(204);
	steps.getStudentinfoById(studentId).statusCode(404);

}
}
