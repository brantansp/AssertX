package com.wdcoder.xhelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;

public class UIApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(UIApplication.class.getResource("ui-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 630, 420);
        stage.setTitle("XPath Validator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        TextArea consoleTextArea = (TextArea) fxmlLoader.getNamespace().get("consoleOutput");
        consoleTextArea.setEditable(true);

        PrintStream printStream = new PrintStream(new com.wdcoder.xhelper.TextAreaOutputStream(consoleTextArea));

        System.setOut(printStream);
        System.setErr(printStream);
    }

    public static void main(String[] args) {
        launch();
    }
}