package com.teamtreehouse.pomodoro.model;

/**
 * Created by Brandon on 3/12/2016.
 */
public class Attempt {
    private String mMessage;
    private int mRemainingSeconds;
    private AttemptKind mKind;

    public Attempt(String message, AttemptKind kind) {
        mMessage = message;
        mKind = kind;
        mRemainingSeconds = kind.getTotalSeconds();
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public int getRemainingSeconds() {
        return mRemainingSeconds;
    }

    public AttemptKind getKind() {
        return mKind;
    }

    public void tick() {
        mRemainingSeconds--;
    }

    public void save() {
        System.out.printf("Saving: %s %n", this);
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "mMessage='" + mMessage + '\'' +
                ", mRemainingSeconds=" + mRemainingSeconds +
                ", mKind=" + mKind +
                '}';
    }
}
