package com.teamtreehouse.pomodoro.model;

/**
 * Created by Brandon on 3/12/2016.
 */
public enum AttemptKind {
    FOCUS(3, "Focus Time"),
    BREAK(5 * 60, "Break Time");

    private int mTotalSeconds;
    private String mDisplayName;

    AttemptKind(int totalSeconds, String displayName) {
        mDisplayName = displayName;
        mTotalSeconds = totalSeconds;
    }

    public int getTotalSeconds() {
        return mTotalSeconds;
    }

    public String getDisplayName() {
        return null;
    }
}
