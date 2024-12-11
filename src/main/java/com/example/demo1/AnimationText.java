package com.example.demo1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class AnimationText {

    public Label test;

    public void myFunction() {
        Timer timer = new Timer();
        TimerTask ShowPasswordsDoNotMatch = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {             //Platform Run Later makes the Javafx utilize UI threads not the background threads
                    test.setText("Passwords do not match");
                    test.setVisible(true);
                });
            }
        };
        TimerTask HidePasswordsDoNotMatch = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {             //Platform Run Later makes the Javafx utilize UI threads not the background threads
                    test.setVisible(false);
                });
            }
        };
        timer.schedule(ShowPasswordsDoNotMatch, 0); // 3-second delay
        timer.schedule(HidePasswordsDoNotMatch, 5000);
    }
}
