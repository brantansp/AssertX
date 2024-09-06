package com.wdcoder.assertx;

//import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WebDriverTools {

    WebDriver driver;
    Iterator<String> windows;
    private String browser = "chrome";

    public void initializeBrowser() throws InterruptedException {
        browser = browser.toLowerCase();
        switch (browser) {
            case "chrome":
                //WebDriverManager.chromedriver().setup();
                ChromeOptions ch = new ChromeOptions();
                ch.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(ch);
                break;
            case "firefox":
                //WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                //WebDriverManager.iedriver().setup();
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
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            if (element.isEnabled()) {
                System.out.println("The element : " + xpath + " is enabled");
            } else {
                System.out.println("The element : " + xpath + " is disabled");
            }
        } catch (NoSuchElementException e) {
            System.out.println("No element found at XPath: " + xpath);
        }
    }

    public void isSelected(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            if (element.isSelected()) {
                System.out.println("The element : " + xpath + " is selected");
            } else {
                System.out.println("The element : " + xpath + " is not selected");
            }
        } catch (NoSuchElementException e) {
            System.out.println("No element found at XPath: " + xpath);
        }
    }

    public void isDisplayed(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            if (element.isDisplayed()) {
                System.out.println("The element : " + xpath + " is displayed");
            } else {
                System.out.println("The element : " + xpath + " is not displayed");
            }
        } catch (NoSuchElementException e) {
            System.out.println("No element found at XPath: " + xpath);
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

    public void test() {
        WebElement element = driver.findElement(By.xpath("//input[@name='q']"));

        Actions actions = new Actions(driver);
        WebElement elementx = driver.findElement(By.xpath("//element/xpath"));
        actions.moveToElement(elementx).perform();

        actions.contextClick(elementx).perform();
        actions.doubleClick(elementx).perform();
        actions.clickAndHold(elementx).release().perform();
        actions.moveByOffset(10, 20).perform();
        actions.getActionDuration().toString();
        actions.getActivePointer();
        //actions.getActiveWheel().createScroll(10, 20);
        actions.tick();

    }

    public void performWebElementActions(String selectAction, String elementLocator, String textToEnter) {

        //"getAttribute()","getCssValue()", "getTagName()", "getLocation()", "getSize()",
        // "getRect()", , "getAriaRole()", "getAccessibleName()","getScreenshotAs()", "getDomAttribute"
        // "getDomProperty()", "getShadowRoot()", "findElement()", "findElements()"

        //"hover()", "dragAndDrop()","doubleClick()","contextClick()",
        // "moveToElement()"

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
            case "isEnabled()":
                isEnabled(elementLocator);
                break;
            case "getAttribute(value)":
                getAttribute(elementLocator, textToEnter);
                break;
            case "getCssValue(value)":
                getCssValue(elementLocator, textToEnter);
                break;
            case "getCssProperties()":
                getCssProperties(elementLocator);
                break;
            case "getTagName()":
                getTagName(elementLocator);
                break;
            case "getLocation()":
                getLocation(elementLocator);
                break;
            case "getSize()":
                getSize(elementLocator);
                break;
            case "getRect()":
                getRect(elementLocator);
                break;
            case "getAriaRole()":
                getAriaRole(elementLocator);
                break;
            case "getAccessibleName()":
                getAccessibleName(elementLocator);
                break;
            case "getScreenshotAs()":
                getScreenshotAs(elementLocator);
                break;
            case "getDomAttribute()":
                getDomAttribute(elementLocator);
                break;
            case "getDomProperty()":
                getDomProperty(elementLocator);
                break;
            case "getShadowRoot()":
                getShadowRoot(elementLocator);
                break;
            case "findElement()":
                findElement(elementLocator);
                break;
            case "findElements()":
                findElements(elementLocator);
                break;

            case "moveToElement()":
                moveToElement(elementLocator);
                break;
            case "actionClick()":
                actionClick(elementLocator);
                break;
            case "clickAndHold()":
                clickAndHold(elementLocator);
                break;
            case "contextClick()":
                contextClick(elementLocator);
                break;
            case "doubleClick()":
                doubleClick(elementLocator);
                break;
            case "dragAndDrop(src,target)":
                dragAndDrop(elementLocator, textToEnter);
                break;
            case "release()":
                release(elementLocator);
                break;
            case "build()":
                build();
                break;
            case "perform()":
                perform();
                break;
            case "moveByOffset(x,y)":
                moveByOffset(Integer.parseInt(elementLocator), Integer.parseInt(textToEnter));
                break;
            case "pause(time)":
                pause(Integer.parseInt(textToEnter));
                break;
            case "moveToLocation(x,y)":
                moveToLocation(Integer.parseInt(elementLocator), Integer.parseInt(textToEnter));
                break;
            case "actionSendKeys(value)":
                actionSendKeys(elementLocator, textToEnter);
                break;

            case "executeScript(script)":
                executeScript(elementLocator, textToEnter);
                break;
            case "jsClick()":
                jsClick(elementLocator);
                break;
            case "jsSendKeys(value)":
                jsSendKeys(elementLocator, textToEnter);
                break;
            case "jsHighlight()":
                jsHighlight(elementLocator);
                break;
            case "jsScrollIntoView()":
                jsScrollIntoView(elementLocator);
                break;
            case "jsScrollBy(pixels)":
                jsScrollBy(elementLocator, textToEnter);
                break;
            case "jsScrollTo(pixels)":
                jsScrollTo(elementLocator, textToEnter);
                break;
            case "jsScrollTop()":
                jsScrollTop(elementLocator);
                break;
            case "jsScrollBottom()":
                jsScrollBottom(elementLocator);
                break;
            case "jsScrollLeft()":
                jsScrollLeft(elementLocator);
                break;
            case "jsScrollRight()":
                jsScrollRight(elementLocator);
                break;
            case "jsGetBorderColor()":
                jsGetBorderColor(elementLocator);
                break;
            case "jsGetBackgroundColor()":
                jsGetBackgroundColor(elementLocator);
                break;
            case "jsGetColor()":
                jsGetColor(elementLocator);
                break;
            case "jsGetComputedStyle()":
                jsGetComputedStyle(elementLocator);
                break;
            case "jsGetInnerText()":
                jsGetInnerText(elementLocator);
                break;
            case "jsGetOuterText()":
                jsGetOuterText(elementLocator);
                break;
            case "jsGetFontFamily()":
                jsGetFontFamily(elementLocator);
                break;
            case "jsGetFontSize()":
                jsGetFontSize(elementLocator);
                break;
            /*
            case "moveToElementWithOffset()":
                moveToElementWithOffset(elementLocator, Integer.parseInt(textToEnter));
                break;
            case "keyDown()":
                keyDown();
                break;
            case "keyUp()":
                keyUp(textToEnter);
                break;
            case "keyDownOnElement()":
                keyDownOnElement(elementLocator, textToEnter);
                break;
            case "keyUpOnElement()":
                keyUpOnElement(elementLocator, textToEnter);
                break;
            case "pauseWithDuration()":
                pauseWithDuration(textToEnter);
                break;*/
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
            case "switchNextWindow()":
                driverSwitchNextWindow();
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
        try {
            String alertText = driver.switchTo().alert().getText();
            System.out.println("The alert text - " + alertText);
        } catch (Exception e) {
            System.out.println("The alert not present");
        }

    }

    private void driverAlertAccept() {
        driver.switchTo().alert().accept();
        System.out.println("Accepted the alert");
    }

    private void driverSwitchToWindow(String value) {
        driver.switchTo().window(value);
        System.out.println("Switched to window of - " + value);
    }

    private void driverSwitchNextWindow() {

        if (windows == null){
            windows = driver.getWindowHandles().iterator();
        }

        if(windows.hasNext()){
            driver.switchTo().window(windows.next());
            System.out.println("Switched to next window");
        } else {
            System.out.println("No more windows to switch to");
            windows = null;
        }
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
        System.out.println("Switched to active element which contains - " + element.getText());
    }

    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("argument[0].setAttribute(‘style’, ’background: yellow; border: 2px solid red; ’);", element);
    }

    private void driverSwitchToDefaultContent() {
        driver.switchTo().defaultContent();
        System.out.println("Switched to default content");
    }

    private void driverSwitchToFrame(String value) {
        driver.switchTo().frame(value);
        System.out.println("Switched to frame - " + value);
    }

    private void driverDeleteAllCookies() {
        driver.manage().deleteAllCookies();
        System.out.println("All cookies deleted successfully");
    }

    private void driverGetCookieNamed(String value) {
        Cookie cookie = driver.manage().getCookieNamed(value);
        System.out.println("Cookie with name - " + cookie.getName() + "and value - " + cookie.getValue() + " returned for the key - " + value);
    }

    private void driverDeleteCookie(String value) {
        Cookie cookie = new Cookie(value.split(",")[0], value.split(",")[1]);
        driver.manage().deleteCookie(cookie);
        System.out.println("Cookie with name - " + cookie.getName() + "and value - " + cookie.getValue() + " deleted successfully");
    }

    private void driverAddCookie(String value) {
        Cookie cookie = new Cookie(value.split(",")[0], value.split(",")[1]);
        driver.manage().addCookie(cookie);
        System.out.println("Cookie with name - " + cookie.getName() + "and value - " + cookie.getValue() + " added successfully");
    }

    private void driverGetCookies() {
        String c = driver.manage().getCookies().toString();

        if (c == null) {
            System.out.println("No cookies found");
            return;
        }
        for (Cookie cookie : driver.manage().getCookies()) {
            System.out.println("getDomain() - " + cookie.getDomain());
            System.out.println("getExpiry() - " + cookie.getExpiry());
            System.out.println("getName() - " + cookie.getName());
            System.out.println("getPath() - " + cookie.getPath());
            System.out.println("getSameSite() - " + cookie.getSameSite());
            System.out.println("getValue() - " + cookie.getValue());
            System.out.println("isHttpOnly() - " + cookie.isHttpOnly());
            System.out.println("isSecure() - " + cookie.isSecure());
            System.out.println("toJson() - " + cookie.toJson());
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
        for (String windowHandle : driver.getWindowHandles()) {
            System.out.println(windowHandle + "\n");
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

    public void getAttribute(String xpath, String attributeName) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String attributeValue = element.getAttribute(attributeName);
            System.out.println("Attribute " + attributeName + "'s value: " + attributeValue);
        } catch (NullPointerException e) {
            System.out.println("Element does not have the '" + attributeName + "' attribute");
        }
    }

    public void getCssValue(String xpath, String cssPropertyName) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String cssValue = element.getCssValue(cssPropertyName);
            System.out.println("CSS " + cssPropertyName + "'s value : " + cssValue);
        } catch (NullPointerException e) {
            System.out.println("Element does not have the '" + cssPropertyName + "' CSS property");
        }
    }

private void getCssProperties(String elementLocator) {
    WebElement element = driver.findElement(By.xpath(elementLocator));
    Map<String, String> cssProperties = new HashMap<>();

    // List of CSS property keys
    List<String> cssPropertyKeys = Arrays.asList(
        "border-top-color", "border-right-color", "border-bottom-color", "border-left-color",
        "border-right", "border-bottom", "border-left", "border-top-right-radius",
        "border-bottom-right-radius", "border-width", "border-top", "border-left-color",
        "border-right-color", "padding", "padding-bottom", "padding-top", "padding-left",
        "padding-right", "margin", "margin-top", "margin-bottom", "margin-left",
        "margin-right", "margin-size", "max-height", "min-height", "min-width",
        "width", "height", "line-height", "font-family", "font-weight", "font-size",
        "text-align", "color", "fill", "background-color", "background-image"
    );

    for (String key : cssPropertyKeys) {
        String value = element.getCssValue(key);
        System.out.println(key + " : " + value);
    }
}

    public void getTagName(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        String tagName = element.getTagName();
        System.out.println("Tag name: " + tagName);
    }

    public void getLocation(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Point location = element.getLocation();
        System.out.println("Location: " + location);
    }

    public void getSize(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Dimension size = element.getSize();
        System.out.println("Size: " + size);
    }

    public void getRect(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Rectangle rect = element.getRect();
        System.out.println("Rectangle Height: " + rect.height);
        System.out.println("Rectangle Width: " + rect.width);
        System.out.println("Rectangle X: " + rect.x);
        System.out.println("Rectangle Y: " + rect.y);
    }

    public void getAriaRole(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String ariaRole = element.getAriaRole();
            System.out.println("Aria role: " + ariaRole);
        } catch (UnsupportedOperationException e) {
            System.out.println("Element does not support ARIA roles");
        }
    }

    public void getAccessibleName(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String accessibleName = element.getAccessibleName();
            System.out.println("Accessible name: " + accessibleName);
        } catch (UnsupportedOperationException e) {
            System.out.println("Element does not support accessible names");
        }
    }

    public void getScreenshotAs(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            File screenshotFile = element.getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());
        } catch (WebDriverException e) {
            System.out.println("Failed to capture screenshot");
        }
    }

    public void getDomAttribute(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String domAttribute = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('value')", element);
            System.out.println("DOM attribute value: " + domAttribute);
        } catch (NullPointerException e) {
            System.out.println("Element does not have the 'value' attribute");
        }
    }

    public void getDomProperty(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String domProperty = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value", element);
            System.out.println("DOM property value: " + domProperty);
        } catch (NullPointerException e) {
            System.out.println("Element does not have the 'value' property");
        }
    }

    public void getShadowRoot(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            Object shadowRoot = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
            System.out.println("Shadow root (JSway - \"return arguments[0].shadowRoot\"): " + shadowRoot);
            System.out.println("Shadow root (Selenium in-built): " + element.getShadowRoot());
        } catch (Exception e) {
            System.out.println("Element does not have a shadow root");
        }
    }

    public void findElement(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        System.out.println("Found element: " + element);
    }

    public void findElements(String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        System.out.println("Found elements: " + elements);
    }

    public void moveToElement(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Failed to move to element: " + e.getMessage());
        }
    }

    public void actionClick(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions searchBoxAction = new Actions(driver);
        try {
            searchBoxAction.click(element).build().perform();
            System.out.println("The element was Clicked for : " + xpath);
        } catch (Exception e) {
            System.out.println("Failed to click element: " + e.getMessage());
        }
    }

    public void clickAndHold(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        try {
            actions.clickAndHold(element).perform();
            System.out.println("The element was Clicked and held for : " + xpath);
        } catch (Exception e) {
            System.out.println("Failed to click and hold element: " + e.getMessage());
        }
    }

    public void contextClick(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        try {
            actions.contextClick(element).perform();
            System.out.println("The element was Context Clicked for : " + xpath);
        } catch (Exception e) {
            System.out.println("Failed to context click element: " + e.getMessage());
        }
    }

    public void doubleClick(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        try {
            actions.doubleClick(element).perform();
            System.out.println("The element was Double Clicked for : " + xpath);
        } catch (Exception e) {
            System.out.println("Failed to double click element: " + e.getMessage());
        }
    }

    public void dragAndDrop(String sourceXpath, String targetXpath) {
        WebElement sourceElement = driver.findElement(By.xpath(sourceXpath));
        WebElement targetElement = driver.findElement(By.xpath(targetXpath));
        Actions actions = new Actions(driver);
        try {
            actions.dragAndDrop(sourceElement, targetElement).perform();
            System.out.println("The element was Dragged and Dropped for : " + sourceXpath + " to " + targetXpath);
        } catch (Exception e) {
            System.out.println("Failed to drag and drop elements: " + e.getMessage());
        }
    }

    public void actionSendKeys(String xpath, String value) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions searchBoxAction = new Actions(driver);
        try {
            searchBoxAction.sendKeys(element, value).build().perform();
            System.out.println("Keys sent to element: " + value + " for : " + xpath);
        } catch (Exception e) {
            System.out.println("Failed to send keys to element: " + e.getMessage());
        }
    }

    public void release(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        try {
            actions.release(element).perform();
            System.out.println("The element was Released for : " + xpath);
        } catch (Exception e) {
            System.out.println("Failed to release element: " + e.getMessage());
        }
    }

    public void build() {
        Actions actions = new Actions(driver);
        try {
            actions.build();
            System.out.println("Actions were built successfully");
        } catch (Exception e) {
            System.out.println("Failed to build actions: " + e.getMessage());
        }
    }

    public void perform() {
        Actions actions = new Actions(driver);
        try {
            actions.perform();
            System.out.println("Actions were performed successfully");
        } catch (Exception e) {
            System.out.println("Failed to perform actions: " + e.getMessage());
        }
    }

    public void moveByOffset(int xOffset, int yOffset) {
        Actions actions = new Actions(driver);
        try {
            actions.moveByOffset(xOffset, yOffset).perform();
            System.out.println("Moved by offset: " + xOffset + ", " + yOffset);
        } catch (Exception e) {
            System.out.println("Failed to move by offset: " + e.getMessage());
        }
    }

    public void pause(long duration) {
        try {
            Thread.sleep(duration);
            System.out.println("Paused for: " + duration + " milliseconds");
        } catch (InterruptedException e) {
            System.out.println("Failed to pause: " + e.getMessage());
        }
    }

    public void moveToLocation(int xLocation, int yLocation) {
        Actions actions = new Actions(driver);
        try {
            actions.moveToLocation(xLocation, yLocation).perform();
            System.out.println("Moved to location: " + xLocation + ", " + yLocation);
        } catch (Exception e) {
            System.out.println("Failed to move to location: " + e.getMessage());
        }
    }

    public void moveToElementWithOffset(WebElement element, int xOffset, int yOffset) {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(element).moveByOffset(xOffset, yOffset).perform();
            System.out.println("Moved to element with offset: " + xOffset + ", " + yOffset);
        } catch (Exception e) {
            System.out.println("Failed to move to element with offset: " + e.getMessage());
        }
    }

    public void release(WebElement element) {
        Actions actions = new Actions(driver);
        try {
            actions.release(element).perform();
            System.out.println("The element was released for : " + element);
        } catch (Exception e) {
            System.out.println("Failed to release element: " + e.getMessage());
        }
    }

    public void sendKeys(WebElement element, Keys keys) {
        try {
            element.sendKeys(keys);
            System.out.println("Keys sent to element: " + keys);
        } catch (Exception e) {
            System.out.println("Failed to send keys to element: " + e.getMessage());
        }
    }

    public void keyDown(Keys keys) {
        Actions actions = new Actions(driver);
        try {
            actions.keyDown(keys).perform();
            System.out.println("Key pressed down: " + keys);
        } catch (Exception e) {
            System.out.println("Failed to press key down: " + e.getMessage());
        }
    }

    public void keyUp(Keys keys) {
        Actions actions = new Actions(driver);
        try {
            actions.keyUp(keys).perform();
            System.out.println("Key released: " + keys);
        } catch (Exception e) {
            System.out.println("Failed to release key: " + e.getMessage());
        }
    }

    public void keyDownOnElement(WebElement element, Keys keys) {
        Actions actions = new Actions(driver);
        try {
            actions.keyDown(element, keys).perform();
            System.out.println("Key pressed down on element: " + keys);
        } catch (Exception e) {
            System.out.println("Failed to press key down on element: " + e.getMessage());
        }
    }

    public void keyUpOnElement(WebElement element, Keys keys) {
        Actions actions = new Actions(driver);
        try {
            actions.keyUp(element, keys).perform();
            System.out.println("Key released on element: " + keys);
        } catch (Exception e) {
            System.out.println("Failed to release key on element: " + e.getMessage());
        }
    }

    public void pauseWithDuration(long duration) {
        try {
            Thread.sleep(duration);
            System.out.println("Paused with duration: " + duration + " milliseconds");
        } catch (InterruptedException e) {
            System.out.println("Failed to pause with duration: " + e.getMessage());
        }
    }

    public void executeScript(String elementLocator, String script) {
        WebElement element = driver.findElement(By.xpath(elementLocator));
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script, element);
            System.out.println("JavaScript script executed successfully");
        } catch (Exception e) {
            System.out.println("Failed to pause with duration: " + e.getMessage());
        }
    }

    public void jsClick(String elementLocator) {
        WebElement element = driver.findElement(By.xpath(elementLocator));
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            System.out.println("JavaScript click executed successfully");
        } catch (Exception e) {
            System.out.println("Failed to pause with duration: " + e.getMessage());
        }
    }

    private void jsSendKeys(String elementLocator, String textToEnter) {
        WebElement element = driver.findElement(By.xpath(elementLocator));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + textToEnter + "';", element);
            System.out.println("JavaScript send keys executed successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to send keys to element: " + e.getMessage());
        }
    }

    private void jsHighlight(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '2px solid red'", element);
            System.out.println("Highlighted element successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to highlight element: " + e.getMessage());
        }
    }

    private void jsScrollIntoView(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
            System.out.println("Scrolled into view successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to scroll into view");
        }
    }

    private void jsScrollBy(String xpath, String value) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + value + ")", element);
            System.out.println("Scrolled by " + value + " pixels successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to scroll by " + value + " pixels");
        }
    }

    private void jsScrollTo(String xpath, String value) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + value + ")", element);
            System.out.println("Scrolled to " + value + " pixels successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to scroll to " + value + " pixels");
        }
    }

    private void jsScrollTop(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)", element);
            System.out.println("Scrolled to the top successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to scroll to the top");
        }
    }

    private void jsScrollBottom(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)", element);
            System.out.println("Scrolled to the bottom successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to scroll to the bottom");
        }
    }

    private void jsScrollLeft(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, window.scrollX)", element);
            System.out.println("Scrolled to the left successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to scroll to the left");
        }
    }

    private void jsScrollRight(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollWidth, window.scrollY)", element);
            System.out.println("Scrolled to the right successfully");
        } catch (WebDriverException e) {
            System.out.println("Failed to scroll to the right");
        }
    }

    private void jsGetBorderColor(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String borderColor = (String) ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0]).borderColor", element);
            System.out.println("Border color: " + borderColor);
        } catch (WebDriverException e) {
            System.out.println("Failed to get border color");
        }
    }

    private void jsGetBackgroundColor(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String backgroundColor = (String) ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0]).backgroundColor", element);
            System.out.println("Background color: " + backgroundColor);
        } catch (WebDriverException e) {
            System.out.println("Failed to get background color");
        }
    }

    private void jsGetColor(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String color = (String) ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0]).color", element);
            System.out.println("Color: " + color);
        } catch (WebDriverException e) {
            System.out.println("Failed to get color");
        }
    }

    private void jsGetComputedStyle(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            Map<String, String> computedStyle = (Map<String, String>) ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0])", element);
            System.out.println("Computed style: " + computedStyle);
        } catch (WebDriverException e) {
            System.out.println("Failed to get computed style");
        }
    }

    private void jsGetInnerText(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String innerText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText", element);
            System.out.println("Inner text: " + innerText);
        } catch (WebDriverException e) {
            System.out.println("Failed to get inner text");
        }
    }

    private void jsGetOuterText(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String outerText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].outerText", element);
            System.out.println("Outer text: " + outerText);
        } catch (WebDriverException e) {
            System.out.println("Failed to get outer text");
        }
    }

    private void jsGetFontFamily(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String fontFamily = (String) ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0]).fontFamily", element);
            System.out.println("Font family: " + fontFamily);
        } catch (WebDriverException e) {
            System.out.println("Failed to get font family");
        }
    }

    private void jsGetFontSize(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try {
            String fontSize = (String) ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0]).fontSize", element);
            System.out.println("Font size: " + fontSize);
        } catch (WebDriverException e) {
            System.out.println("Failed to get font size");
        }
    }


// ...


    public static void main(String[] args) {
        WebDriverTools webDriverTools = new WebDriverTools();
        try {
            webDriverTools.initializeBrowser();
            webDriverTools.driverGetUrl("https://www.google.com");
            webDriverTools.sendKeys("//input[@name='q']", "Selenium");
            webDriverTools.click("//input[@name='btnK']");
            Thread.sleep(5000);
            webDriverTools.closeBrowser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
