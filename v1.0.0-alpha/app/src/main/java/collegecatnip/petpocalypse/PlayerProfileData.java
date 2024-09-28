package collegecatnip.petpocalypse;
/**
 * a class that acquires players data that is kept in their profile
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 *
 * @author Madison Ridore
 * Last Modified: 9/27/2024
 * Patch Notes:
 *		Will import the pet data from a json file in the future. 
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
    private ArrayList<Pet> petsOwned;


    public PlayerProfileData() {
        petsOwned = new ArrayList<>();
        this.love = 0;
        this.treats = 0;
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

    public ArrayList<Pet> getPetsOwned() {
        return petsOwned;
    }

    public void addPet(Pet pet) {
        petsOwned.add(pet);
    }

    // method to update monies
    public void updateMonies(long additionalMonies) {
        this.monies += additionalMonies;
    }
}
