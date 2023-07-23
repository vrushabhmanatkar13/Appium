package com.Appium.testcase;

import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import comAppium.uitility.Baseclass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.qameta.allure.Allure;

public class Setup_Teardown {
	
	final long IMPLICITWAIT=20;
	final long WEBDRIVERWAIT=30;
	
	public static Baseclass baseclass;
	AppiumDriverLocalService service;
	UiAutomator2Options options;
	public static AndroidDriver driver;
	public static Properties prop;
	
	
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({"Ip Address","Port"})
	public void beforeSuite(String ipaddress, String port) {
		baseclass=new Baseclass();
		baseclass.readJSON(System.getProperty("user.dir")+"\\TestData\\testdata.json");
		prop=baseclass.readProperties(System.getProperty("user.dir")+"\\TestData\\config.properties");
		service=baseclass.startAppiumServer(ipaddress,port, prop.getProperty("AppiumJS"), prop.getProperty("NodeJS"));
	}
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"Device Name","Device Version"})
    public void beforeTest(String devicename, String version) {
		options=baseclass.setUiAutomator2Options(devicename,version, Baseclass.jsonValue("appPackage"), System.getProperty("user.dir")+"\\Apk\\"+Baseclass.jsonValue("app"));
		driver=baseclass.AndroidDriver(baseclass.getURL(service), options);
		baseclass.implicitWait(driver, IMPLICITWAIT);
		baseclass.webdriverWait(driver, WEBDRIVERWAIT);
		
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		baseclass.stopAppiumServer(service);
	}
}
