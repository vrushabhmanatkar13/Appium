package com.Appium.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Appium.testcase.Setup_Teardown;

import comAppium.uitility.Baseclass;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;


public class LoginPage extends Baseclass {
   AndroidDriver driver;
   
	public LoginPage() {
		this.driver=Setup_Teardown.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "android.widget.LinearLayout")
	private WebElement location;
	
	@FindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
	private WebElement allow;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='username']")
	private WebElement emailtextbox;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='password']")
	private WebElement passwordtextbox;
	
	@FindBy(xpath = "//android.widget.Button[@text='Login']")
	private WebElement loginbutton;
	
	@FindBy(xpath = "//android.view.View[8]")
	private WebElement registernow;
	
	public void clickAllowLocation() {
		if (isDisplayed(location)) {
			click(allow);
		}
		else {
			System.out.println("Location Alert not Displayed");
		}
	}
	
	
	public void login(String username, String password) {
		sendKey(emailtextbox, username);
		sendKey(passwordtextbox, password);
		click(loginbutton);
	}
	
	public String clickRegisterNow() {
		String text=getText(registernow);
		click(registernow);
		return text;
	}
}
