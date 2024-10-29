package collegecatnip.petpocalypse.backend;
/**
 * Class to manage pet carrier lootboxes
 *      player buys lootbox
 *      program fills weighted rarities with rarity types
 *      rand int choose a random rarity
 *      rand int chooses a rand ID of chosen rarity
 *
 *      ex. Buy basic pet carrier -> fill weighted_rarities with base_weights for each rarity type
 *          -> get rand int for weighted_rarities -> rand int gives common rarity -> choose rand ID of common_ids
 *          -> return random common ID where shop gives player the pet with that ID
 *
 * @author Jaime Lee
 * Date Created: 10/13/2024
 *
 * Last Modified: 10/28/2024
 *      Fixed weights to actually work
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

    private int[] common_ids;
    private int[] rare_ids;
    private int[] legendary_ids;
    private int[] mystical_ids;
    private int[] dev_ids;

    /**
     * constructor
     */
    LootBox(ArrayList<Pet> pet_array)
    {
        int length = pet_array.size();
        common_ids = new int[length]; rare_ids = new int[length]; legendary_ids = new int[length]; mystical_ids = new int[length]; dev_ids = new int[length];
        int commons_index = 0, rares_index = 0, legendarys_index = 0, mysticals_index = 0, devs_index = 0;

        for(int i = 0; i < length; i++)
        {
            switch(pet_array.get(i).getRarity())
            {
                case 1:
                    common_ids[commons_index] = pet_array.get(i).getID();
                    break;
                case 2:
                    rare_ids[rares_index] = pet_array.get(i).getID();
                    break;
                case 3:
                    legendary_ids[legendarys_index] = pet_array.get(i).getID();
                    break;
                case 4:
                    mystical_ids[mysticals_index] = pet_array.get(i).getID();
                    break;
                case 5:
                    dev_ids[devs_index] = pet_array.get(i).getID();
                    break;
                default:
                    System.out.println("Error loading pet IDs to the lootboxes...");
                    break;
            }
        }
    }

    /**
     * Roll for a random pet
     * @param box_rarity
     * @return pet
     */
    public Pet openBox(int box_rarity)
    {
        ArrayList<Pet> pet_array = PlayerData.getPetsOwned();

        getWeights(box_rarity);

        int rarity = rand.nextInt(101); // bound does not include 101

        switch(weighted_rarities[rarity])
        {
            case "common":
                return pet_array.get(rand.nextInt(common_ids.length));
            case "rare":
                return pet_array.get(rand.nextInt(rare_ids.length));
            case "legendary":
                return pet_array.get(rand.nextInt(legendary_ids.length));
            case "mystical":
                return pet_array.get(rand.nextInt(mystical_ids.length));
            case "dev":
                return pet_array.get(rand.nextInt(dev_ids.length));
            default:
                return null;
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
}
