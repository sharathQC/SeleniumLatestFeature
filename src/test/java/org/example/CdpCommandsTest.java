package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;

public class CdpCommandsTest {
    public static void main(String args[]) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\GSHARATH\\Documents\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        HashMap map = new HashMap();
        map.put("width", 600);
        map.put("height",1000);
        map.put("deviceScaleFactor", 100);
        map.put("mobile", true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",map);

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href='/angularAppdemo/library']")).click();


    }
}
