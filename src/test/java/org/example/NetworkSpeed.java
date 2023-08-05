package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;
import org.openqa.selenium.devtools.v110.network.model.ConnectionType;

import java.util.Optional;

public class NetworkSpeed {
    public static void main(String args[]){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\GSHARATH\\Documents\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET)));
        devTools.addListener(Network.loadingFailed(), loadingFailed ->
        {
            System.out.println("Error text: "+loadingFailed.getErrorText());
            System.out.println("timestamp: "+loadingFailed.getTimestamp());
        });
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
        driver.quit();

    }
}
