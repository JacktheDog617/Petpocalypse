package collegecatnip.petpocalypse.backend;
/**
 * a class that acquires players data that is kept in their profile
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 * 
 * Date Last Modified: 10/24/2024
 *      removed unnecessary code
 *      modified givePet to work via ID not Pet object
 */

import java.util.ArrayList;

public class PlayerData {

    // player variables
    private static String playerName;
    private static int user_id;
    private static long love;
    private static int treats;
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
    public long getLove() {
        return love;
    }

    public void setLove(long love) {
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

    public long getPlayerLogoff() {
        return player_logoff;
    }

    public void setPlayerLogoff(long player_logoff) {
        this.player_logoff = player_logoff;
    }

    public static ArrayList<Pet> getPetsOwned() {
        System.out.print("[");
        for(Pet thing: petsOwned)
        {
            System.out.print(thing + ", ");
        }
        System.out.print("]\n");
        return petsOwned;
    }

    public void addPet(Pet pet) {
        petsOwned.add(pet);
    }

    public int givePet(Pet pet)
    {
        for(Pet current: petsOwned)
        {
            if (current.equals(pet))
            {
                if(current.isOwned())
                {
                    current.addDuplicate();
                    return 0;
                }
                else
                {
                    current.changedOwned(true);
                    return 0;
                }
            }
        }
        System.out.println("Could not give pet, pet does not exist.");
        return 1;
    }
    // method to update monies
    public void updateMonies(long additionalMonies) {
        this.love += additionalMonies;
    }
}