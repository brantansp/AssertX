package com.wdcoder.xhelper;

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
    String[] webElementActionChoices = {"click()", "clickAll()", "sendKeys(value)", "clear()", "submit()", "getText()", "isDisplayed()", "isSelected()"};
    String[] webDriverActionChoices = {"get(url)", "navigate().to(url)", "navigate().forward()", "navigate().back()", "navigate().refresh()", "getTitle()","getCurrentUrl()"/*,"getPageSource()"*/,"getWindowHandle()","getWindowHandles()","fullScreen()","maximize()","minimize()","getSize()","getPosition()","getCookies()","addCookie(key,value)","deleteCookie(key,value)","getCookieNamed(value)","deleteAllCookies()","switchToFrame(value)","switchToDefaultContent()","switchToActiveElement()","switchToParentFrame()","switchToNewWindow(WindowType.valueOf(window))","switchToWindow(value)","alert().accept()","alert().getText()","alert().sendKeys(value)","alert().dismiss()","alert().equals(value)"};


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

        webElementAction.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equalsIgnoreCase("sendKeys(value)")) {
                textToSend.setDisable(true);
                textToSend.setText("");
            } else {
                textToSend.setDisable(false);
            }
        });

        webDriverActions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("get(url)") || newValue.equalsIgnoreCase("navigate().to(url)")) {
                webDriverEnterUrl.setDisable(false);
                webDriverEnterUrl.setPromptText("Please enter URL with http or https or just domain name");
            } else if (newValue.equalsIgnoreCase("deleteCookie(key,value)") || newValue.equalsIgnoreCase("addCookie(key,value)")){
                webDriverEnterUrl.setPromptText("Please enter the cookie key and value in key,value format");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("getCookieNamed(value)")){
                webDriverEnterUrl.setPromptText("Please enter the cookie key");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("switchToWindow(value)")){
                webDriverEnterUrl.setPromptText("Please enter the window name or handle from getWindowHandle() method");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("switchToFrame(value)")){
                webDriverEnterUrl.setPromptText("Please enter the frame name or id");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("switchToNewWindow(WindowType.valueOf(window))")){
                webDriverEnterUrl.setPromptText("Please enter the WindowType as window or tab");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("alert().sendKeys(value)")){
                webDriverEnterUrl.setPromptText("Please enter value to be sent to alert()");
                webDriverEnterUrl.setDisable(false);
            } else if (newValue.equalsIgnoreCase("alert().equals(value)")){
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
        String consoleText = consoleOutput.getText();
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
    }

    public void ClearConsoleOutput(ActionEvent actionEvent) {
        consoleOutput.setText("");
    }


}