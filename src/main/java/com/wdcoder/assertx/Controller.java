package com.wdcoder.assertx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    public Button driverGetUrl;
    @FXML
    public TextArea consoleOutput;
    @FXML
    public Button copyConsole;
    @FXML
    public Button clearConsole;
    @FXML
    public ToggleGroup toggleGroup1;
    @FXML
    public TextField elementLocator;
    @FXML
    public Button executeAction;
    @FXML
    public TextField textToSend;
    @FXML
    public ComboBox<String> webDriverActions;
    @FXML
    public Button webDriverActionsExecute;
    @FXML
    public Button webElementActionExecute;
    @FXML
    public Label driverLabel;
    @FXML
    public TextField webDriverEnterUrl;
    @FXML
    public RadioButton chromeRadioButton;
    @FXML
    public RadioButton firefoxRadioButton;
    @FXML
    public RadioButton edgeRadioButton;
    public ToggleButton darkLightMode;

    String selectedBrowser = "Chrome";

    @FXML
    private StackPane stackPane;
    @FXML
    private VBox root;
    @FXML
    private Button openBrowserButton;
    @FXML
    private Button closeBrowserButton;

    WebDriverTools tools = new WebDriverTools();
    @FXML
    public ComboBox<String> webElementAction;
    String[] webElementActionChoices = {"click()",
            "clickAll()",
            "sendKeys(value)",
            "clear()",
            "submit()",
            "getText()",
            "isDisplayed()",
            "isSelected()",
            "isEnabled()",
            "getAttribute(value)",
            "getCssValue(value)",
            "getCssProperties()",
            //"findElement()",
            //"findElements()",
            "getTagName()",
            "getLocation()",
            "getSize()",
            "getRect()",
            "getAriaRole()",
            "getAccessibleName()",
            "getScreenshotAs()",
            "getDomAttribute()",
            "getDomProperty()",
            "getShadowRoot()",

            // Actions
            "actionClick()",
            "actionSendKeys(value)",
            "contextClick()",
            "doubleClick()",
            "moveToElement()",
            "clickAndHold()",
            "dragAndDrop(src,target)",
            "moveByOffset(x,y)",
            "moveToLocation(x,y)",
            "pause(time)",

            //Javascript executor
            "executeScript(script)",
            "jsClick()",
            "jsSendKeys(value)",
            "jsHighlight()",
            "jsScrollIntoView()",
            "jsScrollBy(pixels)",
            "jsScrollTo(pixels)",
            "jsScrollTop()",
            "jsScrollBottom()",
            "jsScrollLeft()",
            "jsScrollRight()",
            "jsGetBorderColor()",
            "jsGetBackgroundColor()",
            "jsGetColor()",
            "jsGetComputedStyle()",
            "jsGetInnerText()",
            "jsGetOuterText()",
            "jsGetFontFamily()",
            "jsGetFontSize()"};
    String[] webDriverActionChoices = {
            "get(url)",
            "navigate().to(url)",
            "navigate().forward()",
            "navigate().back()",
            "navigate().refresh()",
            "getTitle()",
            "getCurrentUrl()"/*,"getPageSource()"*/,
            "getWindowHandle()",
            "getWindowHandles()",
            "fullScreen()",
            "maximize()",
            "minimize()",
            "getSize()",
            "getPosition()",
            "getCookies()",
            "addCookie(key,value)",
            "deleteCookie(key,value)",
            "getCookieNamed(value)",
            "deleteAllCookies()",
            "switchToWindow(value)",
            "switchNextWindow()",
            "switchToFrame(value)",
            "switchToDefaultContent()",
            "switchToActiveElement()",
            "switchToParentFrame()",
            "switchToNewWindow(WindowType.valueOf(window))",
            "alert().accept()",
            "alert().getText()",
            "alert().sendKeys(value)",
            "alert().dismiss()",
            "alert().equals(value)"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.prefWidthProperty().bind(stackPane.widthProperty());
        root.prefHeightProperty().bind(stackPane.heightProperty());
        webElementAction.getItems().addAll(webElementActionChoices);
        webElementAction.getSelectionModel().selectFirst();
        webDriverActions.getItems().addAll(webDriverActionChoices);
        webDriverActions.getSelectionModel().selectFirst();
        webElementAction.disableProperty().bind(elementLocator.textProperty().isEmpty());
        webElementActionExecute.disableProperty().bind(elementLocator.textProperty().isEmpty());



        darkLightMode.selectedProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println("Dark mode selected: " + newValue);
            if (newValue) {
                switchToLightMode();
            } else {
                switchToDarkMode();
            }
        });

        darkLightMode.setSelected(true);

        webElementAction.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("sendKeys(value)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Enter Text to Send");
            } else if (newValue.equalsIgnoreCase("dragAndDrop(src,target)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Please enter the target element locator");
            } else if (newValue.equalsIgnoreCase("moveByOffset(x,y)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                elementLocator.clear();
                elementLocator.setPromptText("Please enter the x coordinate to move the element by");
                textToSend.setPromptText("Please enter the x coordinate to move the element by");
            } else if (newValue.equalsIgnoreCase("moveToLocation(x,y)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Please enter the x and y coordinates (comma-separated) to move the element to");
            } else if (newValue.equalsIgnoreCase("jsSendKeys(value)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Please enter the value to send to the element using JavaScript");
            } else if (newValue.equalsIgnoreCase("executeScript(script)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Please enter the JavaScript script to execute");
            } else if (newValue.equalsIgnoreCase("jsScrollBy(pixels)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Please enter the number of pixels to scroll by");
            } else if (newValue.equalsIgnoreCase("jsScrollTo(pixels)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Please enter the number of pixels to scroll to");
            } else if (newValue.equalsIgnoreCase("getAttribute(value)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                System.out.println("Please provide any of these attribute : \nclass, id, name, href, src, style, title, value, placeholder, type, checked, disabled, selected, data-*, aria-*, *");
                textToSend.setPromptText("Please provide any of these attribute : class, id, name, href, src, style, title, value, placeholder, type, checked, disabled, selected, data-*, aria-*, *");
            } else if (newValue.equalsIgnoreCase("getCssValue(value)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                System.out.println("Please provide any of these css property : \nbackground-color, color, display, font-size, font-weight, height, width, margin, padding, border, border-color, border-width, border-style, border-radius, opacity, visibility, position, top, bottom, left, right, z-index, overflow, text-align, text-decoration, text-transform, line-height, letter-spacing, word-spacing, white-space, text-overflow, list-style-type, list-style-position, list-style-image");
                textToSend.setPromptText("Please provide any of these css property : background-color, color, display, font-size, font-weight, height, width, margin, padding, border, border-color, border-width, border-style, border-radius, opacity, visibility, position, top, bottom, left, right, z-index, overflow, text-align, text-decoration, text-transform, line-height, letter-spacing, word-spacing, white-space, text-overflow, list-style-type, list-style-position, list-style-image");
            } else if (newValue.equalsIgnoreCase("getCssProperties()")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Css property : border-top-color, border-right-color, border-bottom-color, border-left-color, border-right, border-bottom, border-left, border-top-right-radius, border-bottom-right-radius, border-width, border-top, border-left-color, border-right-color, padding, padding-bottom, padding-top, padding-left, padding-right, margin, margin-top, margin-bottom, margin-left, margin-right, margin-size, max-height, min-height, min-width, width, height, line-height, font-family, font-weight, font-size, text-align, color, fill, background-color, background-image");
            } else if (newValue.equalsIgnoreCase("pause(time)")) {
                textToSend.clear();
                textToSend.setDisable(false);
                textToSend.setPromptText("Please enter the time in milliseconds to pause");
            } else {
                textToSend.setDisable(true);
            }
        });

        webDriverActions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("get(url)") || newValue.equalsIgnoreCase("navigate().to(url)")) {
                webDriverEnterUrl.setDisable(false);
                webDriverEnterUrl.setPromptText("Please enter URL with http or https or just domain name");
            } else if (newValue.equalsIgnoreCase("deleteCookie(key,value)") || newValue.equalsIgnoreCase("addCookie(key,value)")) {
                webDriverEnterUrl.setPromptText("Please enter the cookie key and value in key,value format");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("getCookieNamed(value)")) {
                webDriverEnterUrl.setPromptText("Please enter the cookie key");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("switchToWindow(value)")) {
                webDriverEnterUrl.setPromptText("Please enter the window name or handle from getWindowHandle() method");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("switchNextWindow()")) {
                webDriverEnterUrl.setDisable(true);
            } else if (newValue.equalsIgnoreCase("switchToFrame(value)")) {
                webDriverEnterUrl.setPromptText("Please enter the frame name or id");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("switchToNewWindow(WindowType.valueOf(window))")) {
                webDriverEnterUrl.setPromptText("Please enter the WindowType as window or tab");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("alert().sendKeys(value)")) {
                webDriverEnterUrl.setPromptText("Please enter value to be sent to alert()");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("alert().equals(value)")) {
                webDriverEnterUrl.setPromptText("Please enter the value to compare with alert().equals()");
                webDriverEnterUrl.setDisable(false);
            } else {
                webDriverEnterUrl.setPromptText("");
                webDriverEnterUrl.setDisable(true);
            }
        });
    }

    @FXML
    private void setBrowser(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) event.getSource();
        tools.setBrowser(selectedRadioButton.getText());
    }

    @FXML
    private void openBrowser(ActionEvent event) throws InterruptedException {
        tools.initializeBrowser();
        if (chromeRadioButton.isSelected()) {
            firefoxRadioButton.setDisable(true);
            edgeRadioButton.setDisable(true);
        } else if (firefoxRadioButton.isSelected()) {
            chromeRadioButton.setDisable(true);
            edgeRadioButton.setDisable(true);
        } else {
            firefoxRadioButton.setDisable(true);
            chromeRadioButton.setDisable(true);
        }
        openBrowserButton.setDisable(true);
        closeBrowserButton.setDisable(false);
        driverLabel.setDisable(false);
        webDriverActions.setDisable(false);
        webDriverActionsExecute.setDisable(false);
        webDriverEnterUrl.setDisable(false);
        elementLocator.setDisable(false);
        webDriverActions.getSelectionModel().selectNext();
        webDriverActions.getSelectionModel().selectPrevious();
        //webElementAction.setDisable(false);
        //webElementActionExecute.setDisable(false);
    }

    @FXML
    private void closeBrowser(ActionEvent event) throws IOException {
        try {

            if (chromeRadioButton.isSelected()) {
                firefoxRadioButton.setDisable(false);
                edgeRadioButton.setDisable(false);
            } else if (firefoxRadioButton.isSelected()) {
                chromeRadioButton.setDisable(false);
                edgeRadioButton.setDisable(false);
            } else {
                firefoxRadioButton.setDisable(false);
                chromeRadioButton.setDisable(false);
            }
            openBrowserButton.setDisable(false);
            closeBrowserButton.setDisable(true);
            driverLabel.setDisable(true);
            webDriverActions.setDisable(true);
            webDriverActionsExecute.setDisable(true);
            webDriverEnterUrl.setText("");
            elementLocator.setText("");
            textToSend.setText("");
            webDriverEnterUrl.setDisable(true);
            elementLocator.setDisable(true);
            webElementAction.getSelectionModel().selectFirst();
            //webElementAction.setDisable(true);
            //webElementActionExecute.setDisable(true);
            tools.closeBrowser();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void webDriverActionExecute(ActionEvent actionEvent) {
        String webDriverAction = webDriverActions.getSelectionModel().getSelectedItem();
        String url = this.webDriverEnterUrl.getText();

        tools.webDriverAction(webDriverAction, url);
    }

    public String getEnteredUrlFromInputBox() {
        return webDriverEnterUrl.getText();
    }

    public void consoleCopyButtonSetOpacityToHalf(MouseEvent mouseEvent) {
        copyConsole.setOpacity(0.5);
    }

    public void consoleCopyButtonSetOpacityToFull(MouseEvent mouseEvent) {
        copyConsole.setOpacity(1.0);
    }

    public void consoleClearButtonSetOpacityToHalf(MouseEvent mouseEvent) {
        clearConsole.setOpacity(0.5);
    }

    public void consoleClearButtonSetOpacityToFull(MouseEvent mouseEvent) {
        clearConsole.setOpacity(1.0);
    }

    public void executeWebElementAction(ActionEvent actionEvent) {
        String selectAction = webElementAction.getSelectionModel().getSelectedItem();
        String elementLocator = this.elementLocator.getText();
        String textToEnter = this.textToSend.getText();


        tools.performWebElementActions(selectAction, elementLocator, textToEnter);
    }

    public void copyConsoleTextToClipboard(ActionEvent actionEvent) {
        String consoleText;
        try {
            consoleText = consoleOutput.getText();
            if (!consoleText.isEmpty()) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection stringSelection = new StringSelection(consoleText);
                clipboard.setContents(stringSelection, null);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Console is empty");
                alert.setContentText("Nothing to copy !!!");
                alert.showAndWait();
            }
        } finally {
            // No additional code beyond the immediate scope of the try-with-resources statement
        }
    }

    public void ClearConsoleOutput(ActionEvent actionEvent) {
        consoleOutput.setText("");
    }


    private void switchToDarkMode() {
        stackPane.setStyle("-fx-background-color: #212121;");
        System.out.println("Switching to dark mode...");
        // Modify other UI components' style properties to match dark mode
    }

    private void switchToLightMode() {
        stackPane.setStyle("-fx-background-color: white;");
        System.out.println("Switching to dark mode...");
        // Modify other UI components' style properties to match light mode
    }
}