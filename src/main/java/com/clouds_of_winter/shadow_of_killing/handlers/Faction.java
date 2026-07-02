package com.clouds_of_winter.shadow_of_killing.handlers;

public enum Faction {
    MONSTERS,
    PLAYERS,
    WITHER;

    public boolean isEnemy(Faction other) {
        return switch (this) {
            case MONSTERS -> other == PLAYERS;
            case WITHER -> true; // 敌对所有阵营
            default -> false;
        };
    }
}
