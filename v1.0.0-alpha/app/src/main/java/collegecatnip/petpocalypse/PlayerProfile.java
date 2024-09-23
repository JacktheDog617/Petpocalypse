package collegecatnip.petpocalypse;

/**
 * Class to manage the players profile for the player class
 *
 * @author Madison Ridore
 * Date Created: 9/23/2024
 *
 * Last modified: 9/23/2024
 * Patch Notes:
 *      ShopActivity and EditProfileActivity are not yet openable, the functionality is there
 *      but with the advancments of the code the shop and edit profile will be expanded. Comments
 *      are staggered throughout the code to make it more understandable. Sample numbers and images
 *      are used in order to make the code more fleshed out. SharedPreferences is implemented to
 *      save player settings and stats so if game is logged out then the game data will be saved.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView playerName;
    private TextView currencyInfo;
    private TextView petsOwned;
    private ImageView profilePicture;
    private Button selectProfilePictureButton;

    private String[] profilePictures = {"ic_profile_1", "ic_profile_2", "ic_profile_3"};
    private int currentProfilePictureIndex = 0; // tracks which profile picture is selected

    private int love;   // love currency (free, earned passively)
    private int treats; // treats currency (earned via microtransactions)
    private int petCount; // number of pets owned

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find views by ID
        playerName = findViewById(R.id.player_name);
        currencyInfo = findViewById(R.id.currency_info);
        petsOwned = findViewById(R.id.pets_owned);
        profilePicture = findViewById(R.id.profile_picture);
        selectProfilePictureButton = findViewById(R.id.select_profile_picture_button);

        // load saved data
        loadPlayerData();

        playerName.setText("Player Name: John Doe");
        updateCurrencyInfo();
        petsOwned.setText("Pets Owned: " + petCount);

        // handle profile picture selection
        selectProfilePictureButton.setOnClickListener(v -> selectNextProfilePicture());

        // set up shop button functionality
        Button shopButton = findViewById(R.id.shop_button);
        shopButton.setOnClickListener(v -> {
            // open the shop activity (to be implemented soon)
            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            startActivity(intent);
        });

        // set up edit profile button functionality
        Button editProfileButton = findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(v -> {
            // open the edit profile activity (to be implemented soon)
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });
    }

    // logic to switch between predefined profile pictures
    private void selectNextProfilePicture() {
        currentProfilePictureIndex = (currentProfilePictureIndex + 1) % profilePictures.length;
        int resId = getResources().getIdentifier(profilePictures[currentProfilePictureIndex], "drawable", getPackageName());
        profilePicture.setImageResource(resId);
        savePlayerData(); // save the current profile picture index
    }

    // method to update the currency information text
    private void updateCurrencyInfo() {
        currencyInfo.setText("Love: " + love + "\nTreats: " + treats);
    }

    // save player data (including love, treats, and profile picture)
    private void savePlayerData() {
        SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("love", love);
        editor.putInt("treats", treats);
        editor.putInt("profilePictureIndex", currentProfilePictureIndex);
        editor.putInt("petCount", petCount);
        editor.apply();
    }

    // load player data from SharedPreferences
    private void loadPlayerData() {
        SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
        love = preferences.getInt("love", 500);  // default value 500
        treats = preferences.getInt("treats", 50); // default value 50
        currentProfilePictureIndex = preferences.getInt("profilePictureIndex", 0);
        petCount = preferences.getInt("petCount", 3); // default value 3

        // set the profile picture based on saved index
        int resId = getResources().getIdentifier(profilePictures[currentProfilePictureIndex], "drawable", getPackageName());
        profilePicture.setImageResource(resId);
    }
}
