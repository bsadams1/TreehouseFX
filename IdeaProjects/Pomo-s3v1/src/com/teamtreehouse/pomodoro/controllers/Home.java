package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Created by Brandon on 3/12/2016.
 */
public class Home {
    @FXML
    private VBox container;

    @FXML
    private Label title;

    @FXML
    private TextArea message;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeline;

    public Home(){
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
    }

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    public void setTimerText(String timerText) {
        this.mTimerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds){
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    private void prepareAttempt(AttemptKind kind){
        reset();
        mCurrentAttempt = new Attempt("", kind);
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getRemainingSeconds());
        mTimeline = new Timeline();
        mTimeline.setCycleCount(kind.getTotalSeconds());
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getRemainingSeconds());
        }));
        mTimeline.setOnFinished(e -> {
            saveCurrentAttempt();
            prepareAttempt(mCurrentAttempt.getKind() == AttemptKind.FOCUS ?
                            AttemptKind.BREAK : AttemptKind.FOCUS);
        });
    }

    private void saveCurrentAttempt() {
        mCurrentAttempt.setMessage(message.getText());
        mCurrentAttempt.save();
    }

    private void reset() {
        clearAttemptStyles();
        if(mTimeline != null && mTimeline.getStatus() == Animation.Status.RUNNING){
            mTimeline.stop();
        }
    }

    public void playTimer(){
        mTimeline.play();
    }

    public void pauseTimer(){
        mTimeline.pause();
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles(){
        for (AttemptKind kind : AttemptKind.values()){
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void DEBUG(ActionEvent actionEvent) {
        System.out.println("Hello!");
    }

    public void handleRestart(ActionEvent actionEvent) {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }
}
