package com.mygame.petpocalypse;

import java.util.ArrayList;

public class PlayerProfileData {

    // player variables
    private String playerName;
    private int user_id;
    private long monies;
    private long player_logoff;
    private int[] pettionary_unlock;
    private short room_color;

    public PlayerProfileData() {
        pettionary_unlock = new int[100]; // example for 100 potential pets
    }

    // getters and setters

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerNickname(String playerName) {
        this.playerName = playerName;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public long getMonies() {
        return monies;
    }

    public void setMonies(long monies) {
        this.monies = monies;
    }

    public long getPlayerLogoff() {
        return player_logoff;
    }

    public void setPlayerLogoff(long player_logoff) {
        this.player_logoff = player_logoff;
    }

    public int[] getPettionaryUnlock() {
        return pettionary_unlock;
    }

    public void setPettionaryUnlock(int[] pettionary_unlock) {
        this.pettionary_unlock = pettionary_unlock;
    }

    public short getRoomColor() {
        return room_color;
    }

    public void setRoomColor(short room_color) {
        this.room_color = room_color;
    }

    // method to update monies
    public void updateMonies(long additionalMonies) {
        this.monies += additionalMonies;
    }
}
