package com.TestNG;

import org.testng.annotations.Test;

public class GroupExe1_Test {
	
	
	@Test(groups="smoke")
	public void test1() {
		System.out.println("-----TEST1-------");
	}
	
	
	@Test(groups="regression")
	public void test2() {
		System.out.println("-----TEST2----------");
	}
	
	@Test(groups= {"smoke", "regression"})
	public void test3() {
		System.out.println("----TEST3-----");
	}

}
