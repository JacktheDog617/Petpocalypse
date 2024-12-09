package collegecatnip.petpocalypse.backend;
/**
 * PlayerData is the central part where the player can hold and display their pets
 * save their name and userID into a save array, which will be transported to the PlayerSave.
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 * 
 * 
 * Date Last Modified: 11/29/2024
 *      moved multiplier array to PlayerData
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;

public class PlayerData {

    // player variables
    private static String name;
    private static int user_id;
    private static long love;
    private static int treats;
    private static LocalDateTime player_logoff;
    private static ArrayList<Pet> petsOwned;
    private static double multipliers[][];
    // The multipliers are in an array in the event of needing to assign
    // different multipliers to different room items via room item IDs

    /**
     * temp print
     */
    public void printInfo()
    {
        System.out.println("Player Name: " + name);
        System.out.println("User ID: " + user_id);
        System.out.println("Love: " + love);
        System.out.println("Treats: " + treats);
        System.out.println("Room Items:" + Arrays.toString(multipliers));
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
        multipliers = new double[6][3];
        for(int i = 0; i < 6; i++)
        {
            multipliers[i][0] = i; // set ID
            multipliers[i][1] = 0.5;   // set multiplier amount
            multipliers[i][2] = 0;     // set unowned
        }
    }

    public PlayerData(String name, int user_id, long love, int treats, LocalDateTime player_logoff, ArrayList<Pet> petsOwned, double[][] multipliers)
    {
        this.name = name;
        this.user_id = user_id;
        this.love = love;
        this.treats = treats;
        this.player_logoff = player_logoff;
        this.petsOwned = petsOwned;
        this.multipliers = multipliers;
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
    
    public Pet getPet(int pettionary_id)
    {
        for(Pet current: petsOwned)
        {
            if(current.getPettionaryID() == pettionary_id)
            {
                return current;
            }
        }
        return null;
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
    
    /**
     * toggleMultiplier will take in a given item_id and change it's toggle to 1 or 0 according to
     * activate parameter
     * @param item_id - this parameter requires an int or double of the ID of the item to be toggled
     * @param on_off - 1 to toggle on or 0 to toggle off, toggle changes whether or not the item
     *                 is used in income calculations
     * @return 0 if successful, 1 if failed
     */
    public int toggleMultiplier(double item_id, double on_off)
    {
        // do not toggle if on_off is not 1 or 0
        if(on_off!= 1 && on_off != 0)
        {
            System.out.println("Item failed to toggle on/off. on_off = " + on_off);
            return -1; // return failure flag
        }
        // boolean to exit for loop if intended action is completed
        boolean toggle_unfinished = true;
        // loop through array until array end OR multiplier is toggled
        for(int i = 0; (i < multipliers.length && toggle_unfinished); i++)
        {
            if(multipliers[i][0] == item_id) // check against item ID
            {
                multipliers[i][2] = on_off; // switch to 1 or 0
                toggle_unfinished = false; // exit loop
            }
        }
        return 0;// return successful execution flag
    }
    // get multipliers
    public double[][] getMultipliers()
    {
        return multipliers;
    }
}
