package collegecatnip.petpocalypse.backend;
/**
 * Class to manage pet carrier lootboxes
 *
 * @author Jaime Lee
 * Date Created: 10/13/2024
 */
import java.util.Random;
import java.util.ArrayList;

public class LootBox
{
    Random rand = new Random();
    private String[] weights;
    // Types: common, rare, legendary, mystical, dev
    private final int[] base_weights = {60,31,5,3,1}; // common: 60%, rare: 31%, legendary: 5%, mystical: 3%, dev: 1%
    private final int[] silver_weight = {20, 50, 20, 8, 2}; // common: 20%, rare: 50%, legendary: 20%, mystical: 8%, dev: 2%
    private final int[] gold_weight = {10,40,25,15,10}; // common: 10%, rare: 40%, legendary: 25%, mystical: 15%, dev: 10%

    /**
     * Roll for a random pet
     * @param box_rarity
     * @return pet
     */
    public Pet openBox(int box_rarity)
    {
        ArrayList<Pet> pet_array = PlayerData.getPetsOwned();

        getWeights(box_rarity);

        int pet = rand.nextInt(101);

        switch(weights[pet])
        {
            case "common":
                return pet_array.get(rand.nextInt(8));
            case "rare":
                return pet_array.get(rand.nextInt(8+7));
            case "legendary":
                return pet_array.get(rand.nextInt(15+3));
            case "mystical":
                return pet_array.get(rand.nextInt(18+3));
            case "dev":
                return pet_array.get(rand.nextInt(21+3));
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
        weights = new String[101];
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

    private void generateWeights(int[] base_weights)
    {
        int index = 0;
        String[] rarity_type = {"common", "rare", "legendary", "mystical", "dev"};

        for(int i = 0; i < base_weights.length; i++) // for each rarity type
        {
            for(int j = index; j < base_weights[i]; j++); // for percentage of 100
            {
                weights[index] = rarity_type[i]; // set rarity type
                index++; // increment index independent of loops
            }
        }
    }
}
