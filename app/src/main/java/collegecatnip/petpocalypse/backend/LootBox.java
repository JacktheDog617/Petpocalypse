package collegecatnip.petpocalypse.backend;
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
 * Last Modified: 11/1/2024
 *      Edited to read in file
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class LootBox
{
    private Random rand = new Random();
    private String[] weighted_rarities = new String[100];
    // Types: common, rare, legendary, mystical, dev
    private final int[] base_weights = {60,31,5,3,1}; // common: 60%, rare: 31%, legendary: 5%, mystical: 3%, dev: 1%
    private final int[] silver_weight = {20, 50, 20, 8, 2}; // common: 20%, rare: 50%, legendary: 20%, mystical: 8%, dev: 2%
    private final int[] gold_weight = {10,40,25,15,10}; // common: 10%, rare: 40%, legendary: 25%, mystical: 15%, dev: 10%
    
    private int[] commons;
    private int[] rares;
    private int[] legendarys;
    private int[] mysticals;
    private int[] devs;
    
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
        int numOfPets = 23;
        commons = new int[numOfPets]; rares = new int[numOfPets]; legendarys = new int[numOfPets]; mysticals = new int[numOfPets]; devs = new int[numOfPets];
        
        readIn("cats.txt");
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
                return pet_array.get(rand.nextInt(commons.length));
            case "rare":
                return pet_array.get(rand.nextInt(rares.length));
            case "legendary":
                return pet_array.get(rand.nextInt(legendarys.length));
            case "mystical":
                return pet_array.get(rand.nextInt(mysticals.length));
            case "dev":
                return pet_array.get(rand.nextInt(devs.length));
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
    
    private void readIn(String file_name)
    {
        String file = "";
        try
        {
            File toRead = new File(file_name);
            Scanner fileReader = new Scanner(toRead);
            while(fileReader.hasNextLine())
            {
                file = file + fileReader.nextLine();
            }
            fileReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("An error ocurred.");
            e.printStackTrace();
        }
        
        int commons_index = 0, rares_index = 0, legendarys_index = 0, mysticals_index = 0, devs_index = 0;

        // load pet datax
        
        String[] pet;
        String[] pet_data = file.split("=");
        for(String data:pet_data)
        {
            pet = data.split(",");
            switch(Integer.parseInt(pet[1]))
            {
                case 1:
                    commons[commons_index++] = Integer.parseInt(pet[2]);
                    break;
                case 2:
                    rares[rares_index++] = Integer.parseInt(pet[2]);
                    break;
                case 3:
                    legendarys[legendarys_index++] = Integer.parseInt(pet[2]);
                    break;
                case 4:
                    mysticals[mysticals_index++] = Integer.parseInt(pet[2]);
                    break;
                case 5:
                    devs[devs_index++] = Integer.parseInt(pet[2]);
                    break;
                default:
                    System.out.println("Error loading pet IDs to the lootboxes...");
                    break;
            }
        }
    }
}
