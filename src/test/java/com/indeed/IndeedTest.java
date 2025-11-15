package com.indeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndeedTest {

	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver wd = new ChromeDriver(options);
		wd.get("https://in.indeed.com/jobs?q=QA&l=&from=searchOnHP&vjk=9d0fed554e3f373d");

		By copyLinkLocator = By.xpath("//button[@aria-label='Copy Link']");

		WebElement copyLinkElement = wd.findElement(copyLinkLocator);
		copyLinkElement.click();
		String url = wd.findElement(By.cssSelector("link[rel='canonical']")).getAttribute("href");
		System.out.println(url);
		By nextIconLocator = By.xpath("//a[@aria-label='Next Page']");
		WebElement nextIconElement = wd.findElement(nextIconLocator);
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", nextIconElement);
		nextIconElement.click();

		String parentWindow = wd.getWindowHandle();
		System.out.println("Parent is :" + parentWindow);
		js.executeScript("window.open();");
		List<String> windows = new ArrayList<>(wd.getWindowHandles());
		for (String w : windows) {
			System.out.println(w);
		}
		wd.switchTo().window(windows.get(1));
		wd.get(url);

	}

}
