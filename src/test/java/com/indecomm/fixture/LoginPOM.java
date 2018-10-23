package com.indecomm.fixture;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.indecomm.api.AppiumDriverContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPOM {

	AppiumDriver driver;
	
	@AndroidFindBy(xpath = ".//android.view.View[@content-desc=\"CONTINUE\"]")
	MobileElement btnCont;

	public LoginPOM() throws IOException {

		driver = new AppiumDriverContext().getDriver("Android", "Android_virtualdevice");
		PageFactory.initElements(driver, this);
	}

	public void clicktoCont() throws InterruptedException
	{
		Thread.sleep(4000);
		this.driver.findElement(By.xpath("//android.view.View[@content-desc=\"CONTINUE\"]")).click();
	
	}
}
