package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;
import org.openqa.selenium.devtools.v110.network.model.Request;
import org.openqa.selenium.devtools.v110.network.model.Response;

import java.util.Optional;

public class NetworkLogActivity {
    public static void main(String args[]) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\GSHARATH\\Documents\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestWillBeSent ->
        {
            Request req = requestWillBeSent.getRequest();
            System.out.println("req url: "+req.getUrl());
        });

        devTools.addListener(Network.responseReceived(), responseReceived ->
        {
           Response res = responseReceived.getResponse();

            if(res.getStatus().toString().startsWith("4")){
                System.out.println("URL: "+res.getUrl()+" URL is failing with status code:"+
                        res.getStatus());
            }
//            System.out.println("response status: "+responseReceived.getResponse().getStatus());
//            System.out.println("URL: "+responseReceived.getResponse().getUrl());
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[normalize-space()='Virtual Library']")).click();
        Thread.sleep(2000);


    }
}
