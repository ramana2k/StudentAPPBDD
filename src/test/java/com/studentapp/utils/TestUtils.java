package com.studentapp.utils;

import java.util.Random;

public class TestUtils {
	
	public static String getRandomValue()
	{
		Random random= new Random();
		
		return Integer.toString(random.nextInt());
	}

}
