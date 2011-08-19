package com.openshop.beans;

public enum UserLevel {

    USER(0),
    MODERATOR(1),
    ADMIN(2);

    private final int level;

    UserLevel(int level) {

        this.level = level;

    }

    public int getLevel() {
        return level;
    }
}
