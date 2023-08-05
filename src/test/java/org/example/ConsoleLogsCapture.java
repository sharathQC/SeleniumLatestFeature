package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class ConsoleLogsCapture {
    public static void main(String args[]){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\GSHARATH\\Documents\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        WebElement quantity  = driver.findElement(By.id("exampleInputEmail1"));
        quantity.clear();
        quantity.sendKeys("2");

        LogEntries entries  = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logEntry = entries.getAll();

        for (LogEntry le: logEntry){
            System.out.println(le.getMessage()); // use log4j to do some actions with logs, will convert logs to files
        }


    }
}
