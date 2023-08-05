package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.fetch.Fetch;
import org.openqa.selenium.devtools.v110.network.model.Request;

import java.util.Optional;

public class NetworkMocking {
    public static void main(String args[]) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\GSHARATH\\Documents\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), Request -> {
            if(Request.getRequest().getUrl().contains("shetty")){
                String mockURL = Request.getRequest().getUrl().replace("=shetty","=BadGuy");
                System.out.println(mockURL);
                devTools.send(Fetch.continueRequest(Request.getRequestId(), Optional.of(mockURL), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty()));
            }
            else {
                devTools.send(Fetch.continueRequest(Request.getRequestId(), Optional.of(Request.getRequest().getUrl()), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty()));
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[normalize-space()='Virtual Library']")).click();
        Thread.sleep(2000);
        String message = driver.findElement(By.cssSelector("p")).getText();
        System.out.println(message);


    }
}
