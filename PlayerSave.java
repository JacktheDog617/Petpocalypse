/**
 * Manage the saving of PlayerData to and from json
 * @author Madison Ridore
 * Date Created: 11/18/2024
 * 
 * @author Jaime Lee
 * Last Modified: 11/26/2024
 * Patch Notes:
 *      Implementing the type adapter needed for the Pet interface
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PlayerSave {
    
    private static String catsJson = "cat.json";
    private static final String fileName = "playerdata.json";
    
    private static final Gson petGson = new GsonBuilder().registerTypeAdapter(new TypeToken<ArrayList<Pet>>(){}.getType(), new PetTypeAdapter()).setPrettyPrinting().create();
    
    public PlayerSave()
    {
        File json = new File(fileName);
        if(!json.exists())
        {
            try
            {
                if(json.createNewFile())
                {
                    newPlayerData(loadCatsFromJson());
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
    
    private ArrayList<Pet> loadCatsFromJson() {
        try (FileReader reader = new FileReader(catsJson)) {
            System.out.println("Reading cat.json");
            ArrayList<Pet> pets = petGson.fromJson(reader, new TypeToken<ArrayList<Pet>>(){}.getType());
            
            for (Pet pet: pets) {
                System.out.println("Loaded Pet:" + pet.getBreed());
            }
            System.out.println("Finished reading cat.json");
            return pets;
        } catch (IOException e) {
            System.err.println("Error loading cats from JSON: " + e.getMessage());
        }catch (JsonSyntaxException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
        return null;
    }
    
    public static void newPlayerData(ArrayList<Pet> loadedPets)
    {
        // make new player
        PlayerData newPlayer = new PlayerData(loadedPets);
        // give player random pet to start with
        Random rand = new Random();
        int defaultPet = rand.nextInt(3) + 1;
        // give a random default pet with ID 1,2, or 3
        // for Cats this would be either the Tuxedo, Silver Tabby, or Orange domestic
        newPlayer.givePet(defaultPet);
        
        // make new json save
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(PlayerData.class, new PlayerTypeAdapter(petGson))
                    .setPrettyPrinting()
                    .create();
            gson.toJson(newPlayer, writer);
            System.out.println("Player data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }
    
    // Save PlayerData to a JSON file
    public static void savePlayerData(PlayerData playerData) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(PlayerData.class, new PlayerTypeAdapter(petGson))
                    .setPrettyPrinting()
                    .create();
            gson.toJson(playerData, writer);
            System.out.println("Player data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }
    
    // Load PlayerData from a JSON file
    public static PlayerData loadPlayerData() {
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(PlayerData.class, new PlayerTypeAdapter(petGson))
                    .setPrettyPrinting()
                    .create();
            return gson.fromJson(reader, PlayerData.class);
        } catch (IOException e) {
            System.err.println("Error loading player data: " + e.getMessage());
            return null;
        }
    }
}