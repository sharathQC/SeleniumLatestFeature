package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.emulation.Emulation;

import java.util.HashMap;
import java.util.Optional;

public class SetGeoLocation {
    public static void main(String args[]) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\GSHARATH\\Documents\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(25.07602319),
                Optional.of(55.22748507),
                Optional.of(1)));

        driver.get("https://mycurrentlocation.net/");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.google.com/");
        Thread.sleep(3000);
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        Thread.sleep(3000);
        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
        String title_card = driver.findElement(By.xpath("//div[@class='default-ltr-cache-1dvfrvc ejqbulh5']//h1")).getText();
        System.out.println(title_card);








    }
}
