package com.TestNG;

import org.testng.annotations.Test;

public class PracticeTestNG_Test {
	
	@Test(enabled = false)
	public void createTest() {
		System.out.println("-----create test-------");
	}
	
	@Test(dependsOnMethods= "createTest")
	public void editTest() {
		System.out.println("------edit test-------");
	}
	
	
	@Test
	public void deleteTest() {
		System.out.println("-----delete test-------");
	}

}