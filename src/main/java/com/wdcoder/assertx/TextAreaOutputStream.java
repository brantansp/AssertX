package com.wdcoder.assertx;

import javafx.scene.control.TextArea;

import java.io.OutputStream;

public class TextAreaOutputStream extends OutputStream {
    private final TextArea textArea;

    public TextAreaOutputStream(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        // Append the character to the TextArea
        textArea.appendText(String.valueOf((char) b));
    }
}
