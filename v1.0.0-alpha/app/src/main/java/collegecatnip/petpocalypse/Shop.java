package collegecatnip.petpocalypse;

/*
 * Class to manage the players profile for the player class
 *
 * @author Madison Ridore
 * Date Created: 9/25/2024
 *
 * Last modified: 9/25/2024
 * Patch Notes:
 *      import android.widget.Button is commented off right now for simplicity. The overall class is
 *      a stand alone class right not but could be switched to a class that extends the PlayerProfile
 *      class later on.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShopActivity extends AppCompatActivity {

    private int love;
    private int treats;
    private ArrayList<String> petsOwned;

    private static final int BASIC_PET_COST = 100; // cost of basic pet in love

    @Override //overrides the parent method (AppCompatActivity) to ensure you can set up your activity with custom logic (initializing the UI, buttons, etc.)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    // load the player data
    loadPlayerData();

    // set up the buy button for the basic pet
   /* Button buyBasicPetButton = findViewById(R.id.buy_basic_pet);
        buyBasicPetButton.setOnClickListener(v -> buyBasicPet());
    */
}

// logic for buying a basic pet
private void buyBasicPet() {
    if (love >= BASIC_PET_COST) {
        // deduct the love currency and add the pet to player's owned pets
        love -= BASIC_PET_COST;
        petsOwned.add("Basic Pet");

        // save the updated data
        savePlayerData();

        // notify the player
        Toast.makeText(this, "You bought a Basic Pet!", Toast.LENGTH_SHORT).show();
    } else {
        // not enough currency
        Toast.makeText(this, "Not enough Love to buy this pet!", Toast.LENGTH_SHORT).show();
    }
}

// save updated player data
private void savePlayerData() {
    SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt("love", love);  // save updated love currency

    // save updated pets owned
    Set<String> petsSet = new HashSet<>(petsOwned);
    editor.putStringSet("petsOwned", petsSet);  // save pets as a Set

    editor.apply();  // commit changes
}

// load player data from SharedPreferences
private void loadPlayerData() {
    SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
    love = preferences.getInt("love", love);  // load love currency
    treats = preferences.getInt("treats", treats);  // load treats currency

    // load pets owned
    Set<String> petsSet = preferences.getStringSet("petsOwned", new HashSet<>());
    petsOwned = new ArrayList<>(petsSet);  // convert Set back to ArrayList
}
}
