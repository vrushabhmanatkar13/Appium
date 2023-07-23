package comAppium.uitility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Appium.testcase.Setup_Teardown;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class Baseclass {
	
	private static WebDriverWait wait;
	private static JsonNode node;
	
public AppiumDriverLocalService startAppiumServer(String ipaddress, String port, String main_js,String node_js) {
		AppiumDriverLocalService service = new AppiumServiceBuilder()
		.withAppiumJS(new File(main_js))
		       .usingDriverExecutable(new File(node_js))
		       .withIPAddress(ipaddress)
		       .usingPort(Integer.valueOf(port))
		       .withArgument(GeneralServerFlag.BASEPATH,"wd/hub")
		       .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
		       .withArgument(GeneralServerFlag.LOG_LEVEL,"error").build();
		   
		    service.start();
		return service;
	}

public void stopAppiumServer(AppiumDriverLocalService service) {
	service.stop();
}

public URL getURL(AppiumDriverLocalService service) {
	return service.getUrl();
}

public UiAutomator2Options setUiAutomator2Options(String devicename, String platformversion,String packagename,String apppath) {
	UiAutomator2Options option = new UiAutomator2Options();
	option.setDeviceName(devicename);
	option.setPlatformVersion(platformversion);
	option.setAppPackage(packagename);
	option.setApp(apppath);
	return option;
}

public AndroidDriver AndroidDriver(URL url,UiAutomator2Options option ) {
	AndroidDriver driver=new AndroidDriver(url, option);
	return driver;
}

public static void switchToContex(AndroidDriver driver, String contex) throws Exception {
	Set<String> context = driver.getContextHandles();
	for (String string : context) {
		if (string.equalsIgnoreCase(contex)) {
			driver.context(string);
			break;
		}
		else {
			throw new Exception("Contex Handles Name not available");
		}
	}
}

public void implicitWait(AndroidDriver driver,long sec) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));

}

public void pageLoadTimeout(AndroidDriver driver, long sec) {
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
}

public WebDriverWait webdriverWait(AndroidDriver driver, long sec) {
	wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
	return wait;
}

public static void click(WebElement e) {
	wait.until(ExpectedConditions.elementToBeClickable(e)).click();
	
}

public static String getText(WebElement e) {
	return wait.until(ExpectedConditions.visibilityOf(e)).getText();
	 
}
public static boolean isDisplayed(WebElement e) {
	return	wait.until(ExpectedConditions.visibilityOf(e)).isDisplayed();
	
}
public static void sendKey(WebElement e, String key) {
	wait.until(ExpectedConditions.elementToBeClickable(e)).clear();
	e.sendKeys(key);
}
public static void scrollUptoElement(WebElement element) {
	JavascriptExecutor js_exe = (JavascriptExecutor) Setup_Teardown.driver;
	js_exe.executeScript("arguments[0].scrollIntoView()", element);
	
}

public Properties readProperties(String path) {
	Properties prop=new Properties();
	try {
		prop.load(new FileInputStream(new File(path)));
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return prop;
}

public void readJSON(String path) {
	ObjectMapper mapper=new ObjectMapper();
	try {
		node=mapper.readTree(new File(path));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	
}

public static String jsonValue(String key) {
	return node.get(key).asText();
}

public static String jsonArrayValue(String parentkey, String childkey) {
	return node.get(parentkey).get(childkey).asText();
}

public static WebElement scrollGesture_upToElement(String text, AndroidDriver driver) {

	return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))"));
	
}

public static void swipeGesture(AndroidDriver driver,WebElement element, String direction) {
	
	((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
			((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75));
}

public static void doubleClickGesture(AndroidDriver driver,WebElement element) {
	wait.until(ExpectedConditions.visibilityOf(element));
	
	((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
}

public static void longClickGesture(AndroidDriver driver,WebElement element) {
	wait.until(ExpectedConditions.visibilityOf(element));
	
	((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
}

public static void dragGesture_ByWebelement(AndroidDriver driver,WebElement element, int x, int y) {
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", x, "endY", y));
}

public static boolean scrollGesture_uoToEnd(AndroidDriver driver,String direction) {
	boolean canScrollMore;
	do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
				.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", direction, "percent", 3.0));
	} while (canScrollMore);
	return canScrollMore;

}


}
