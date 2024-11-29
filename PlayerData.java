/**
 * a class that acquires players data that is kept in their profile
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 * 
 * Date Last Modified: 11/27/2024
 *      updated to work with TypeAdapter
 */

import java.util.ArrayList;
import java.time.LocalDateTime;


public class PlayerData {

    // player variables
    private static String name;
    private static int user_id;
    private static long love;
    private static int treats;
    private static LocalDateTime player_logoff;
    private static ArrayList<Pet> petsOwned;

    /**
     * temp print
     */
    public void printInfo()
    {
        System.out.println("Player Name: " + name);
        System.out.println("User ID: " + user_id);
        System.out.println("Love: " + love);
        System.out.println("Treats: " + treats);
        System.out.println("Pets Owned:" + petsOwned.toString());
    }
    
    // for json builder
    public PlayerData(){}
    
    public PlayerData(ArrayList<Pet> loadedPets) {
        name = "Player";
        user_id = 0;
        love = 0;
        treats = 0;
        player_logoff = LocalDateTime.now();
        petsOwned = loadedPets;
    }

    public PlayerData(String name, int user_id, long love, int treats, LocalDateTime player_logoff, ArrayList<Pet> petsOwned)
    {
        this.name = name;
        this.user_id = user_id;
        this.love = love;
        this.treats = treats;
        this.player_logoff = player_logoff;
        this.petsOwned = petsOwned;
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

    public String getName() {
        return name;
    }

    public void setName(String new_name) {
        name = new_name;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getPlayerLogoff() {
        return player_logoff;
    }

    public void setPlayerLogoff() {
        this.player_logoff = LocalDateTime.now();
    }

    public static ArrayList<Pet> getPetsOwned() {
//        System.out.print("[");
//        for(Pet thing: petsOwned)
//        {
//            System.out.print(thing + ",");
//        }
//        System.out.println("]");
        return petsOwned;
    }

    public void addPet(Pet pet) {
        petsOwned.add(pet);
    }

    public void givePet(int pet_id)
    {
        for(Pet current: petsOwned)
        {
            if (current.getPetID() == pet_id)
            {
                if(current.isOwned())
                {
                    current.addDuplicate();
                }
                else
                {
                    current.changedOwned(true);
                }
                return;
            }
        }
        System.out.println("Could not give pet, pet does not exist.");
    }
    // method to update monies
    public void updateMonies(long added_love, int added_treats) {
        love += added_love;
        treats += added_treats;
    }
    public void updateMonies(long added_love) {
        love += added_love;
    }
}