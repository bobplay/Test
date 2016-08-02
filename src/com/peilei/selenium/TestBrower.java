package com.peilei.selenium;
import java.io.File;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBrower {

	private static final int CHROM_BROWER = 0;
	private static final int FIREFOX_BROWER = 1;
	private static final int IE_BROWER = 2;
	public static void main(String[] args) {
		openWeb(getWebDriver(CHROM_BROWER));
	}
	
	private static RemoteWebDriver getWebDriver(int type) {
		RemoteWebDriver driver = null;
		switch(type){
		case CHROM_BROWER:
			System.setProperty("webdriver.chrome.driver", "./src/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case FIREFOX_BROWER:
			FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
			driver = new FirefoxDriver(binary,null);
			break;
		case IE_BROWER:
			System.setProperty("webdriver.ie.driver", "./src/IEDriverServer.exe");
			driver = new InternetExplorerDriver(5790);
			break;
		}
		
		
		return driver;
	}

	/**
	 * 打开网页
	 * @param webDriver  各个浏览器driver
	 */
	public static void openWeb(RemoteWebDriver webDriver){
		try {
			long time = 2000;
			webDriver.get("http://www.shuangtv.com/");	
			System.out.println("打开页面: "+webDriver.getWindowHandle().toString());
//		chromeDriver.findElement(By.id("kw")).sendKeys("自动化测试");
//		chromeDriver.findElement(By.id("su")).click();
//			webDriver.navigate().to("http://www.youku.com/");
//			webDriver.executeScript("alert(\"huile\")");
//			webDriver.getKeyboard().pressKey(Keys.F5);
			webDriver.findElementByPartialLinkText("最新动作片").click();
			System.out.println("最新: "+webDriver.getWindowHandle().toString());
			sleep(2000);
			Object[] windowHandles = webDriver.getWindowHandles().toArray();
			for (Object object : windowHandles) {
				System.out.println(object);
			}
			webDriver.switchTo().window(windowHandles[windowHandles.length-1].toString());
			System.out.println("xinyemian: "+webDriver.getWindowHandle().toString());
			sleep(time);
			webDriver.findElement(By.xpath("//input[@name='keyword']")).sendKeys("哪里");
			sleep(time);
//			webDriver.navigate().back();
	//		sleep(time);
//			webDriver.navigate().forward();
//			sleep(time);
//			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			webDriver.quit();
		}
	}

	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
