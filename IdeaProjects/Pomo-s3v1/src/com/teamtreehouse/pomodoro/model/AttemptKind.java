package com.teamtreehouse.pomodoro.model;

/**
 * Created by Brandon on 3/12/2016.
 */
public enum AttemptKind {
    FOCUS(25 * 60),
    BREAK(5 * 60);

    private int mTotalSeconds;

    AttemptKind(int totalSeconds) {
        mTotalSeconds = totalSeconds;
    }

    public int getTotalSeconds() {
        return mTotalSeconds;
    }
}
