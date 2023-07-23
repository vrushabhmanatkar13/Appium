package com.Appium.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Appium.testcase.Setup_Teardown;

import comAppium.uitility.Baseclass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;


public class RegisterPage extends Baseclass{

	AndroidDriver driver;
	public RegisterPage() {
		this.driver=Setup_Teardown.driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "(//android.widget.EditText)[2]" )
	private WebElement firstname;
	
	@FindBy(xpath = "(//android.widget.EditText)[3]" )
	private WebElement lastname;
	
	@FindBy(xpath = "(//android.widget.EditText)[4]" )
	private WebElement securityquestion;
	
	@FindBy(xpath = "(//android.widget.EditText)[5]" )
	private WebElement securityans;
	
	@FindBy(xpath = "(//android.widget.EditText)[6]" )
	private WebElement emailaddress;
	
	@FindBy(xpath = "(//android.widget.EditText)[7]" )
	private WebElement password;
	
	@FindBy(xpath = "(//android.widget.EditText)[8]" )
	private WebElement conformpassword;
	
	@FindBy(xpath = "(//android.widget.EditText)[9]" )
	private WebElement spamprotection;
	
	@FindBy(xpath = "//div[@class='v-input--selection-controls__input']")
	private WebElement checkbox;
	
	@FindBy(xpath = "//div[@style='width: 55%;']//div[@class='v-alert__content']")
	private WebElement free14dayalert;
	
	@FindBy(xpath = "(//android.widget.Button[text(),'Sign Up'])[3]")
	private WebElement submitbutton;
	
	public void registerUser(String firstName, String lastName, String securityQuestion, String securityAns, String emailId, String Password, String conformPassword, String spam) {
		
		sendKey(firstname, firstName);
		sendKey(lastname, lastName);
		sendKey(securityquestion, securityQuestion);
		swipeGesture(driver, password, "down");
		sendKey(securityans, securityAns);
		sendKey(emailaddress, emailId);
		sendKey(password, Password);
		sendKey(conformpassword, conformPassword);
		sendKey(spamprotection, spam);
		
	}
	
	public boolean clickcheckBox() {
		click(checkbox);
		return free14dayalert.isDisplayed();
	}
	
	public void clickSubmit() {
		click(submitbutton);
	}
	
}
