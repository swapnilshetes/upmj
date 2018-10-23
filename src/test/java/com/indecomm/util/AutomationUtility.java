package com.indecomm.util;
//Coarse grained Utility class.
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.indecomm.pojo.CustomDesiredCapabilities;

public class AutomationUtility {

	
	public static JSONObject readJSON()
	{
		JSONParser parser=new JSONParser();
		JSONObject jsonObject=null;
		try
		{
		String cwd = System.getProperty("user.dir") + "/feedFiles/DeviceCababilities.json";
		Object obj = parser.parse(new FileReader(cwd));
		jsonObject = (JSONObject) obj;
		}catch(Exception exc)
		{
			System.out.println("Reading JSON ===>>>"+exc.getMessage());
		}
		return jsonObject;
	}
	
	public static CustomDesiredCapabilities getDesiredCapabilities(JSONObject jsonObject,String platform)
	{
		DesiredCapabilities desiredCapabilities=null;
		CustomDesiredCapabilities cDesiredCapabilities=new CustomDesiredCapabilities();
		
		JSONObject jsonObj=(JSONObject)jsonObject.get(platform);
		
		if(platform.contains("Android"))
		{
			desiredCapabilities=DesiredCapabilities.android();
		//	desiredCapabilities.setVersion("1.9.1");
			
			System.out.println("JSON is  Android=====>>>"+jsonObj.toJSONString());
			desiredCapabilities.setCapability("app",(String)(((JSONObject)jsonObj.get("capabilities")).get("app")));
			desiredCapabilities.setCapability("platformName",(String)(((JSONObject)jsonObj.get("capabilities")).get("platformName")));
			desiredCapabilities.setCapability("platformVersion",(String)(((JSONObject)jsonObj.get("capabilities")).get("platformVersion")));
			desiredCapabilities.setCapability("deviceName", (String)(((JSONObject)jsonObj.get("capabilities")).get("device")));
			desiredCapabilities.setCapability("appWaitActivity",(String)(((JSONObject)jsonObj.get("capabilities")).get("appWaitActivity")));
			desiredCapabilities.setCapability("udid",(String)(((JSONObject)jsonObj.get("capabilities")).get("udid")));
		}
		else if(platform.contains("iOS"))
		{
			desiredCapabilities=new DesiredCapabilities();
			System.out.println("JSON is iOS =====>>>"+jsonObj.toJSONString());
			desiredCapabilities.setCapability("udid",(String)(((JSONObject)jsonObj.get("capabilities")).get("deviceudid")));
			desiredCapabilities.setCapability("app",(String)(((JSONObject)jsonObj.get("capabilities")).get("app")));
			desiredCapabilities.setCapability("platformName",(String)(((JSONObject)jsonObj.get("capabilities")).get("platformName")));
			desiredCapabilities.setCapability("autoAcceptAlerts", true);
			//desiredCapabilities.setCapability("platformVersion",(String)(((JSONObject)jsonObj.get("capabilities")).get("platformVersion")));
			desiredCapabilities.setCapability("deviceName", (String)(((JSONObject)jsonObj.get("capabilities")).get("device")));	
			
			desiredCapabilities.setCapability("automationName", (String)(((JSONObject)jsonObj.get("capabilities")).get("automationName")));
			
		}
		cDesiredCapabilities.setDesiredCapabilities(desiredCapabilities);
		cDesiredCapabilities.setAppiumServer((String)(((JSONObject)jsonObj.get("capabilities")).get("appiumServer")));
		cDesiredCapabilities.setPort((String)(((JSONObject)jsonObj.get("capabilities")).get("port")));
		return cDesiredCapabilities;
		
	}
	
	
//	System.out.println("*****************INFO*******************");
//	System.out.println("platformName" + cDesiredCapabilites.getPlatformName());
//	System.out.println("platformVersion" + cDesiredCapabilites.getPlatformVersion());
//	System.out.println("deviceName" + cDesiredCapabilites.getDeviceName());
//	System.out.println("app" + cDesiredCapabilites.getApp());
//	
//	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//	/*desiredCapabilities.setCapability("udid","706d77f699a6a8ccc499a5b9a240d2d2fa06ab9f");
//	desiredCapabilities.setCapability("app","/Users/navjot/Downloads/Electrolux_Life (1).ipa");
//	//desiredCapabilities.setCapability("app","/src/test/resources/Electrolux_Life (1).ipa");
//	desiredCapabilities.setCapability("platformName","IOS");
//	desiredCapabilities.setCapability("deviceName", "Jiyu");
//	desiredCapabilities.setCapability("automationName", "XCUITest");
//	driver = new IOSDriver(new URL("http://127.0.0.1:4728/wd/hub"), desiredCapabilities);
//	*/
//
//	desiredCapabilities.setCapability("platformName", jsonObject2.get("platformName").toString());
//	//desiredCapabilities.setCapability("platformVersion", jsonObject2.get("version").toString());
//	desiredCapabilities.setCapability("deviceName", jsonObject2.get("device").toString());
//	//desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,jsonObject2.get("bundleid").toString());
//	desiredCapabilities.setCapability("app",jsonObject2.get("app").toString());
//	// desiredCapabilities.setCapability("deviceName",		// jsonObject2.get("device").toString());
//	desiredCapabilities.setCapability("udid", jsonObject2.get("deviceudid").toString());
//	desiredCapabilities.setCapability("automationName", "XCUITest");
//	//desiredCapabilities.setCapability("bundleID", "com.elux.electroluxlife");
//	//desiredCapabilities.setCapability("appPackage", jsonObject2.get("app_package").toString());
//	//desiredCapabilities.setCapability("appActivity", jsonObject2.get("activity").toString());
//	this.driver = new IOSDriver(new URL(
//			jsonObject2.get("appiumServer").toString() + ":" + jsonObject2.get("port").toString() + "/wd/hub/"),
//			desiredCapabilities);
//	this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

}
