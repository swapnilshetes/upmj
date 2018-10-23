/**
 * 
 */
package com.indecomm.api;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.indecomm.pojo.CustomDesiredCapabilities;
import com.indecomm.util.AutomationUtility;
import com.mongodb.util.JSON;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumDriverContext {
	// public static IOSDriverContext driverContext;
	private AppiumDriver driver;
	JSONParser parser = new JSONParser();
	CustomDesiredCapabilities cDesiredCapabilites;
	
	@BeforeClass
	public AppiumDriver getDriver(String device, String OSwithDevice) throws IOException 
	{
		    JSONObject jsonObject = null;
            jsonObject=AutomationUtility.readJSON();// Reads the feedfile.
            System.out.println("JSON Object after parsing is ====>>>"+jsonObject);
            //Processes the JSON based on OSwithDevice and returns the DesiredCapabilities.
            cDesiredCapabilites=AutomationUtility.getDesiredCapabilities(jsonObject,OSwithDevice);
            System.out.println("JSON" + OSwithDevice);
		    if (device.equalsIgnoreCase("Android")) {		
			if (driver == null) {
				
				//System.out.println("JSON" + driver);
				this.driver = new AppiumDriver(new URL(cDesiredCapabilites.getAppiumServer() + ":"+ cDesiredCapabilites.getPort()+ "/wd/hub/"), cDesiredCapabilites.getDesiredCapabilities());
				this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}
		} else if (device.equalsIgnoreCase("iOS")) {
			this.driver = new IOSDriver(new URL(cDesiredCapabilites.getAppiumServer() + ":"+ cDesiredCapabilites.getPort()+ "/wd/hub/"),
					cDesiredCapabilites.getDesiredCapabilities());
			driver.switchTo().alert().accept();
			this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		return driver;

	}

	@AfterClass
	public void closeDriver() {
		if (null != this.driver) {
			// driver.resetApp();
			this.driver.closeApp();
			this.driver.launchApp();
			// driver = null;
		}
	}

}