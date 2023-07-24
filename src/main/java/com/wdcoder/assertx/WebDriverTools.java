package com.wdcoder.assertx;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.util.List;

public class WebDriverTools {

    WebDriver driver;
    private String browser = "chrome";

    public void initializeBrowser() throws InterruptedException {
        browser = browser.toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions ch = new ChromeOptions();
                ch.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(ch);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
        }
    }

    public void closeBrowser() throws IOException {
        try {
            assert driver != null;
            driver.close();
            driver.quit();

            Runtime.getRuntime().exec("taskkill /f /im chromedriver* /T");
            Runtime.getRuntime().exec("taskkill /f /im geckodriver* /T");
        } catch (IOException e) {
            System.out.println("in the close browser method");
            e.printStackTrace();
        }
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void driverGetUrl(String url) {
        if (!(url.contains("http://") || (url.contains("https://")))) {
            url = "https://" + url;
        }

        driver.get(url);
        System.out.printf("Opened %s%n", url);
    }

    public void clear(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).clear();
            System.out.println("The field was cleared for : " + xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).click();
            System.out.println("The element was Clicked for : " + xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickAll(String xpath) {
        try {
            List<WebElement> wb = driver.findElements(By.xpath(xpath));
            for (WebElement w : wb) {
                w.click();
            }
            System.out.println("The element was Clicked for : " + xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendKeys(String xpath, String text) {
        try {
            driver.findElement(By.xpath(xpath)).sendKeys(text);
            System.out.println("Text : " + text + " send to the element : " + xpath);
        } catch (Exception e) {
            System.out.println("Text : " + text + " send to the element : " + xpath);
            e.printStackTrace();
        }
    }

    public void getText(String xpath) {
        try {
            System.out.println(
                    "Text for the element : " + xpath + " is " + driver.findElement(By.xpath(xpath)).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void isEnabled(String xpath) {
        if (driver.findElement(By.xpath(xpath)).isEnabled()) {
            System.out.println("The element : " + xpath + " is enabled");
        }
    }

    public void isSelected(String xpath) {
        if (driver.findElement(By.xpath(xpath)).isSelected()) {
            System.out.println("The element : " + xpath + " is selected");
        }
    }

    public void isDisplayed(String xpath) {
        if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
            System.out.println("The element : " + xpath + " is displayed");
        }
    }

    public void submit(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).submit();
            System.out.println("Submitted to the element : " + xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToFrame(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            driver.switchTo().frame(element);
            System.out.println("Submitted to the element : " + xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performWebElementActions(String selectAction, String elementLocator, String textToEnter) {
        switch (selectAction) {
            case "click()":
                click(elementLocator);
                break;
            case "clickAll()":
                clickAll(elementLocator);
                break;
            case "sendKeys(value)":
                sendKeys(elementLocator, textToEnter);
                break;
            case "clear()":
                clear(elementLocator);
                break;
            case "submit()":
                submit(elementLocator);
                break;
            case "getText()":
                getText(elementLocator);
                break;
            case "isDisplayed()":
                isDisplayed(elementLocator);
                break;
            case "isSelected()":
                isSelected(elementLocator);
                break;
            case "isVisible()":
                isEnabled(elementLocator);
                break;
            default:
                System.out.println("Please choose the valid selection");
                break;
        }
    }

    public void webDriverAction(String webDriverAction, String value) {
        switch (webDriverAction) {
            case "get(url)":
                driverGetUrl(value);
                break;
            case "navigate().to(url)":
                driverNavigateToUrl(value);
                break;
            case "navigate().forward()":
                driverNavigateForward();
                break;
            case "navigate().back()":
                driverNavigateBack();
                break;
            case "navigate().refresh()":
                driverNavigateRefresh();
                break;
            case "getTitle()":
                driverGetTitle();
                break;
            case "getCurrentUrl()":
                driverGetCurrentUrl();
                break;
            case "getPageSource()":
                driverGetPageSource();
                break;
            case "getWindowHandle()":
                driverGetWindowHandle();
                break;
            case "getWindowHandles()":
                driverGetWindowHandles();
                break;
            case "fullScreen()":
                driverFullScreen();
                break;
            case "maximize()":
                driverMaximize();
                break;
            case "minimize()":
                driverMinimize();
                break;
            case "getSize()":
                driverGetSize();
                break;
            case "getPosition()":
                driverGetPosition();
                break;
            case "getCookies()":
                driverGetCookies();
                break;
            case "addCookie(key,value)":
                driverAddCookie(value);
                break;
            case "deleteCookie(key,value)":
                driverDeleteCookie(value);
                break;
            case "getCookieNamed(value)":
                driverGetCookieNamed(value);
                break;
            case "deleteAllCookies()":
                driverDeleteAllCookies();
                break;
            case "switchToFrame(value)":
                driverSwitchToFrame(value);
                break;
            case "switchToDefaultContent()":
                driverSwitchToDefaultContent();
                break;
            case "switchToActiveElement()":
                driverSwitchToActiveElement();
                break;
            case "switchToParentFrame()":
                driverSwitchToParentFrame();
                break;
            case "switchToNewWindow(WindowType.valueOf(window))":
                driverSwitchToNewWindow(value);
                break;
            case "switchToWindow(value)":
                driverSwitchToWindow(value);
                break;
            case "alert().accept()":
                driverAlertAccept();
                break;
            case "alert().getText()":
                driverAlertGetText();
                break;
            case "alert().sendKeys(value)":
                driverAlertSendKeys(value);
                break;
            case "alert().dismiss()":
                driverAlertDismiss();
                break;
            case "alert().equals(value)":
                driverAlertEquals(value);
                break;
            default:
                System.out.println("Please choose the valid selection");
                break;
        }
    }

    private void driverAlertEquals(Object value) {
        boolean alertEquals = driver.switchTo().alert().equals(value);
        System.out.println("Alert value equals is - " + alertEquals);
    }

    private void driverAlertDismiss() {
        driver.switchTo().alert().dismiss();
        System.out.println("Dismissed the alert successfully");
    }

    private void driverAlertSendKeys(String value) {
        driver.switchTo().alert().sendKeys(value);
        System.out.println("Value is send to the alert input " + value);
    }

    private void driverAlertGetText() {
        String alertText = driver.switchTo().alert().getText();
        System.out.println("The alert text - " + alertText);
    }

    private void driverAlertAccept() {
        driver.switchTo().alert().accept();
        System.out.println("Accepted the alert");
    }

    private void driverSwitchToWindow(String value) {
        driver.switchTo().window(value);
        System.out.println("Switched to window of - " + value);
    }

    private void driverSwitchToNewWindow(String value) {
        driver.switchTo().newWindow(WindowType.valueOf(value));
        System.out.println("Switched to new window of type - " + value);
    }

    private void driverSwitchToParentFrame() {
        driver.switchTo().parentFrame();
        System.out.println("Switched to parent frame");
    }

    private void driverSwitchToActiveElement() {
        WebElement element = driver.switchTo().activeElement();
        highlightElement(element);
        System.out.println("Switched to active element which contains - "+ element.getText());
    }

    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("argument[0].setAttribute(‘style’, ’background: yellow; border: 2px solid red; ’);", element);
    }

    private void driverSwitchToDefaultContent() {
        driver.switchTo().defaultContent();
        System.out.println("Switched to default content");
    }

    private void driverSwitchToFrame(String value) {
        driver.switchTo().frame(value);
        System.out.println("Switched to frame - "+value);
    }

    private void driverDeleteAllCookies() {
        driver.manage().deleteAllCookies();
        System.out.println("All cookies deleted successfully");
    }

    private void driverGetCookieNamed(String value) {
        Cookie cookie = driver.manage().getCookieNamed(value);
        System.out.println("Cookie with name - "+cookie.getName() + "and value - " + cookie.getValue()+" returned for the key - " + value);
    }

    private void driverDeleteCookie(String value) {
        Cookie cookie = new Cookie(value.split(",")[0],value.split(",")[1]);
        driver.manage().deleteCookie(cookie);
        System.out.println("Cookie with name - "+cookie.getName() + "and value - " + cookie.getValue()+" deleted successfully");
    }

    private void driverAddCookie(String value) {
        Cookie cookie = new Cookie(value.split(",")[0],value.split(",")[1]);
        driver.manage().addCookie(cookie);
        System.out.println("Cookie with name - "+cookie.getName() + "and value - " + cookie.getValue()+" added successfully");
    }

    private void driverGetCookies() {
        for(Cookie cookie : driver.manage().getCookies()){
            System.out.println("getDomain() - "+cookie.getDomain());
            System.out.println("getExpiry() - "+cookie.getExpiry());
            System.out.println("getName() - "+cookie.getName());
            System.out.println("getPath() - "+cookie.getPath());
            System.out.println("getSameSite() - "+cookie.getSameSite());
            System.out.println("getValue() - "+cookie.getValue());
            System.out.println("isHttpOnly() - "+cookie.isHttpOnly());
            System.out.println("isSecure() - "+cookie.isSecure());
            System.out.println("toJson() - "+cookie.toJson());
            System.out.println("-------------------------------------------------");
        }
    }

    private void driverGetPosition() {
        System.out.println(driver.manage().window().getPosition());
    }

    private void driverGetSize() {
        System.out.println(driver.manage().window().getSize());
    }

    private void driverMinimize() {
        driver.manage().window().minimize();
    }

    private void driverMaximize() {
        driver.manage().window().maximize();
    }

    private void driverFullScreen() {
        driver.manage().window().fullscreen();
    }

    private void driverGetWindowHandles() {
        for (String windowHandle : driver.getWindowHandles()){
            System.out.println(windowHandle+"\n");
        }
    }

    private void driverGetWindowHandle() {
        System.out.println(driver.getWindowHandle());
    }

    private void driverGetPageSource() {
        System.out.println(driver.getPageSource());
    }

    private void driverGetCurrentUrl() {
        System.out.println(driver.getCurrentUrl());
    }

    private void driverGetTitle() {
        System.out.println(driver.getTitle());
    }

    private void driverNavigateRefresh() {
        driver.navigate().refresh();
    }

    private void driverNavigateBack() {
        driver.navigate().back();
    }

    private void driverNavigateForward() {
        driver.navigate().forward();
    }

    private void driverNavigateToUrl(String url) {
        if (!(url.contains("http://") || (url.contains("https://")))) {
            url = "https://" + url;
        }

        driver.navigate().to(url);
        System.out.printf("Navigated to %s%n", url);
    }
}
