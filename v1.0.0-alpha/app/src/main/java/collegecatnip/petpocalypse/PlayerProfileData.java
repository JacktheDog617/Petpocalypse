package com.mygame.petpocalypse;

/*
 * Class to manage the players profile data for the player class
 *
 * @author Madison Ridore
 * Date Created: 9/26/2024
 *
 * Last modified: 9/26/2024
 * Patch Notes:
 *      a json will be added to make import all the pets into an array of objects in the pettionary
 */
import java.util.ArrayList;

public class PlayerProfileData {

    // player variables
    private String playerName;
    private int user_id;
    private int love;
    private int treats;
    private long monies;
    private long player_logoff;


    public PlayerProfileData() {
        love = 0;
        treats = 0;
    }

    // getters and setters

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getTreats() {
        return treats;
    }

    public void setTreats(int treats) {
        this.treats = treats;
    }

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

    // method to update monies
    public void updateMonies(long additionalMonies) {
        this.monies += additionalMonies;
    }
}
