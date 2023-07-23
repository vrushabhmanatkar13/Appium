package com.Appium.testcase;

import org.testng.annotations.Test;

import com.Appium.pageobject.LoginPage;
import com.Appium.pageobject.RegisterPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;



public class TestCases extends Setup_Teardown{

	@Feature("Register User")
	@Story("Registerition Page")
	@Test(priority = 1)
	public void Test() throws InterruptedException {
	
		LoginPage loginpage = new LoginPage();
		loginpage.clickAllowLocation();
		Allure.step("Click Allow");
		String text=loginpage.clickRegisterNow();
		Allure.step("Click "+text);
		RegisterPage registerpage=new RegisterPage();
		Thread.sleep(3000);
		registerpage.registerUser("Automation", "Test", "What is your Name?", "Automation", "test.mobile@mailinator.com", "Digital@123", "Digital@123", "h61Q2!x3PH~nhIn0iP&fg");
		registerpage.clickSubmit();
		
		//loginpage.login("complete.user@iccsafe.info", "Digital@123");
		//Allure.step("Enter username and password");
	}
	
}
