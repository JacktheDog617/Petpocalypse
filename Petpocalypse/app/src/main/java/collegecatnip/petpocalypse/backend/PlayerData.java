package collegecatnip.petpocalypse.backend;
/**
 * a class that acquires players data that is kept in their profile
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 */

import java.util.ArrayList;

public class PlayerData {

    // player variables
    private static String playerName;
    private static int user_id;
    private static int love;
    private static int treats;
    private static long monies;
    private static long player_logoff;
    private static ArrayList<Pet> petsOwned;


    public PlayerData() {
        petsOwned = new ArrayList<>();
        this.love = 0;
        this.treats = 0;
    }

    public PlayerData(int love, int treats)
    {
        this.love = love;
        this.treats = treats;
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
