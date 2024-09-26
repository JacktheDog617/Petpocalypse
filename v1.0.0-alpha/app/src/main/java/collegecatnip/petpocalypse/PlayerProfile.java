package com.mygame.petpocalypse;

/*
 * Class to manage the players profile for the player class
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 *
 * Last modified: 9/23/2024
 * Patch Notes:
 *      ShopActivity is not yet openable, but the functionality is there. Comments
 *      are staggered throughout the code to make it more understandable. Sample numbers and names
 *      are used in order to make the code more fleshed out. SharedPreferences is implemented to
 *      save player settings and stats so if game is logged out then the game data will be saved.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayerProfileActivity extends AppCompatActivity {

    // declare variables to hold player data
    private String playerNameText;
    private int love;
    private int treats;
    private int currentProfilePictureIndex = 0;

    private String[] profilePictures = {"ic_profile_1", "ic_profile_2", "ic_profile_3"};

    private ArrayList<String> petsOwned; // arrayList to hold pets owned by the player

    @Override  //overrides the parent method (AppCompatActivity) to ensure you can set up your activity with custom logic (initializing the UI, buttons, etc.)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializing the shop button so its linked to the shop class
        /*
        Button shopButton = findViewById(R.id.shop_button);
        shopButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerProfileActivity.this, ShopActivity.class);
            startActivity(intent);
        });
         */

        // load saved player data
        loadPlayerData();

        // assign data to variables for testing purposes
        playerNameText = "John Doe";
        love = 500;
        treats = 50;

        // example of changing profile picture and saving data
        selectNextProfilePicture();

        // example of adding a pet to the list
        petsOwned.add("Fuz"); // this could be a pet name or ID
        savePlayerData(); // save the new pet data
    }

    // method to switch profile picture and save data
    private void selectNextProfilePicture() {
        currentProfilePictureIndex = (currentProfilePictureIndex + 1) % profilePictures.length;
        savePlayerData();  // save the current profile picture index
    }


    // save player data using SharedPreferences
    private void savePlayerData() {
        SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("playerName", playerNameText);  // save player name
        editor.putInt("love", love);  // save love currency
        editor.putInt("treats", treats);  // save treats currency
        editor.putInt("profilePictureIndex", currentProfilePictureIndex);  // save profile picture index

        // save pets owned
        Set<String> petsSet = new HashSet<>(petsOwned);  // convert ArrayList to Set
        editor.putStringSet("petsOwned", petsSet);  // save pets as a Set

        editor.apply();  // commit changes
    }

    // load player data from SharedPreferences
    private void loadPlayerData() {
        SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
        playerNameText = preferences.getString("playerName", "John Doe");  // load player name
        love = preferences.getInt("love", 500);  // load love currency
        treats = preferences.getInt("treats", 50);  // load treats currency
        currentProfilePictureIndex = preferences.getInt("profilePictureIndex", 0);  // load profile picture index

        // load pets owned
        Set<String> petsSet = preferences.getStringSet("petsOwned", new HashSet<>());  // load pets as a Set
        petsOwned = new ArrayList<>(petsSet);  // convert Set back to ArrayList
    }
}
