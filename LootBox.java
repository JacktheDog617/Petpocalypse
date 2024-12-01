/**
 * Class to manage pet carrier lootboxes
 *      player buys lootbox
 *      program fills weighted rarities array with rarity types aka ex. String "Common"
 *      rand int choose a random rarity
 *      rand int chooses a rand Pet of chosen rarity
 *      return pet's ID
 * 
 *      ex. Buy basic pet carrier -> fill weighted_rarities with base_weights for each rarity type
 *          -> get rand int for weighted_rarities -> rand int gives common rarity -> choose rand Pet ID of commons
 *          -> return random common pet's ID where shop gives player the pet
 *
 * @author Jaime Lee
 * Date Created: 10/13/2024
 * 
 * Last Modified: 11/29/2024
 *      Edited to use PlayerData array to read in rarity indices
 */
import java.util.Random;
import java.util.ArrayList;

public class LootBox
{
    private Random rand = new Random();
    private String[] weighted_rarities = new String[100];
    // Types: common, rare, legendary, mystical, dev
    private final int[] base_weights = {60,31,5,3,1}; // common: 60%, rare: 31%, legendary: 5%, mystical: 3%, dev: 1%
    private final int[] silver_weight = {20, 50, 20, 8, 2}; // common: 20%, rare: 50%, legendary: 20%, mystical: 8%, dev: 2%
    private final int[] gold_weight = {10,40,25,15,10}; // common: 10%, rare: 40%, legendary: 25%, mystical: 15%, dev: 10%
    
    private ArrayList<Integer> commons;
    private ArrayList<Integer> rares;
    private ArrayList<Integer> legendarys;
    private ArrayList<Integer> mysticals;
    private ArrayList<Integer> devs;
    
    // print array for testing
    private void print(Pet[] array)
    {
        System.out.print("[");
        for(Pet thing: array)
        {
            System.out.print(thing + ", ");
        }
        System.out.print("]\n");
    }
    
    /**
     * constructor
     */
    LootBox()
    {
        commons = new ArrayList<>(); rares = new ArrayList<>(); legendarys = new ArrayList<>(); mysticals = new ArrayList<>(); devs = new ArrayList<>();
        
        loadRarityIndices(new PlayerData().getPetsOwned());
    }
    
    /**
     * Roll for a random pet
     * @param box_rarity
     * @return pet
     */
    public int openBox(int box_rarity)
    {
        getWeights(box_rarity);

        int rarity = rand.nextInt(100); // bound gives rand 0-99

        switch(weighted_rarities[rarity])
        {
            case "common":
                return rand.nextInt(commons.size()) + 11;
            case "rare":
                return rand.nextInt(rares.size()) + 1;
            case "legendary":
                return rand.nextInt(legendarys.size())+1;
            case "mystical":
                return rand.nextInt(mysticals.size()) + 1;
            case "dev":
                return rand.nextInt(devs.size()) + 1;
            default:
                System.out.println("Error: Invalid rarity...");
                return -1;
        }
    }

    /**
     * get weights for given box rarity
     * @param box_rarity
     */
    private void getWeights(int box_rarity)
    {
        switch (box_rarity)
        {
            case 1:
                generateWeights(base_weights);
                break;
            case 2:
                generateWeights(silver_weight);
                break;
            case 3:
                generateWeights(gold_weight);
                break;
            default:
                break;
        }
    }

    private void generateWeights(int[] rates)
    {
        int index = 0; // keep track of index for weights
        String[] rarity_type = {"common", "rare", "legendary", "mystical", "dev"};

        for(int i = 0; i < rates.length; i++) // for each rarity type
        {
            for(int j = 0; j < rates[i]; j++) // for percentage of 100
            {
                weighted_rarities[index] = rarity_type[i]; // set rarity type
                index++; // increment index independent of loops
            }
        }
    }
    
    private void loadRarityIndices(ArrayList<Pet> pets)
    {        
        // load pet data
        for(Pet pet: pets)
        {
            switch(pet.getRarity())
            {
                case 1:
                    commons.add(pet.getPetID());
                    break;
                case 2:
                    rares.add(pet.getPetID());
                    break;
                case 3:
                    legendarys.add(pet.getPetID());
                    break;
                case 4:
                    mysticals.add(pet.getPetID());
                    break;
                case 5:
                    devs.add(pet.getPetID());
                    break;
                default:
                    System.out.println("Error loading pet IDs to the lootboxes...");
                    break;
            }
        }
    }
}
