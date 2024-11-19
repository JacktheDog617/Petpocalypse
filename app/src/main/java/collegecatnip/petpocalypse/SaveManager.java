import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveManager {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Save PlayerData to a JSON file
    public static void savePlayerData(PlayerData playerData, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(playerData, writer);
            System.out.println("Player data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving player data: " + e.getMessage());
        }
    }

    // Load PlayerData from a JSON file
    public static PlayerData loadPlayerData(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, PlayerData.class);
        } catch (IOException e) {
            System.err.println("Error loading player data: " + e.getMessage());
            return null;
        }
    }
}
