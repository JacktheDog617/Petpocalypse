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
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String playerNameText;
    private int love;
    private int treats;
    private int petCount;
    private int currentProfilePictureIndex = 0;

    private String[] profilePictures = {"ic_profile_1", "ic_profile_2", "ic_profile_3"};

    //private ImageView profilePicture;
    private Button selectProfilePictureButton;

        //load saved players data 
        loadPlayerData();
        
        // assign data to variables for sake of testing
        playerNameText = "John Doe";
        love = 500;
        treats = 50;
        petCount = 3;

        // handle profile picture selection
        //selectProfilePictureButton.setOnClickListener(v -> selectNextProfilePicture());

        // set up shop button functionality
/*      Button shopButton = findViewById(R.id.shop_button);
        shopButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShopActivity.class); //shop activity does not exist yet
            startActivity(intent);
        });

        // set up edit profile button functionality
        Button editProfileButton = findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class); //edit profile activity does not exist yet
            startActivity(intent);
        });
    }
*/

    // method to switch profile picture and save data
/*    private void selectNextProfilePicture() {
        currentProfilePictureIndex = (currentProfilePictureIndex + 1) % profilePictures.length;
        int resId = getResources().getIdentifier(profilePictures[currentProfilePictureIndex], "drawable", getPackageName());
        profilePicture.setImageResource(resId);
        savePlayerData(); // Save the current profile picture index
    }
*/
    
    // save player data using SharedPreferences
    private void savePlayerData() {
        SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("playerName", playerNameText);  // save player name
        editor.putInt("love", love);  // save love currency
        editor.putInt("treats", treats);  // save treats currency
        editor.putInt("profilePictureIndex", currentProfilePictureIndex);  // save profile picture index
        editor.putInt("petCount", petCount);  // save pet count
        editor.apply();  // commit changes
    }

    // load player data from SharedPreferences
    private void loadPlayerData() {
        SharedPreferences preferences = getSharedPreferences("player_profile", MODE_PRIVATE);
        playerNameText = preferences.getString("playerName", "John Doe");  // load player name 
        love = preferences.getInt("love", 500);  // load love currency
        treats = preferences.getInt("treats", 50);  // load treats currency 
        currentProfilePictureIndex = preferences.getInt("profilePictureIndex", 0);  // load profile picture array index
        petCount = preferences.getInt("petCount", 3);  // load pet count 

        // set the profile picture based on saved index
   /*     int resId = getResources().getIdentifier(profilePictures[currentProfilePictureIndex], "drawable", getPackageName());
        profilePicture.setImageResource(resId);
    }

*/
}
