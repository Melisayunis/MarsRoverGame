package com.example.MarsRover.Service.Enums;

public enum LevelDifficult {
    EASY("easy", 4), MEDIUM("medium", 6), HARD("hard", 10);

    public final String difficult;
    public final int amountObjects;

    LevelDifficult(String difficult, int amountObjects) {
        this.difficult = difficult;
        this.amountObjects = amountObjects;
    }
}
