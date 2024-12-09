package collegecatnip.petpocalypse.ui;

/**
 * MyPetsActivity for Petpocalypse
 * Dynamically loads cards based on owned pets
 *
 * @author Jaime Lee
 * @date 10/13/2024
 *
 * @author Jaime Lee
 * Date last modified: 12/06/2024
 * Patch Notes:
 *      Cleaned up code and added background music
 */

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import collegecatnip.petpocalypse.R
import java.util.Locale
import collegecatnip.petpocalypse.backend.Pet
import collegecatnip.petpocalypse.backend.PlayerData
import java.util.ArrayList;

class MyPetsActivity : AppCompatActivity() {

    private lateinit var musicPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pets)

        // play background music
        musicPlayer = MediaPlayer.create(this, R.raw.happy_farm_loop)
        musicPlayer.isLooping = true
        musicPlayer.start()

        // set up back button listener
        val backButton: ImageButton = findViewById(R.id.pets_exit_button)
        backButton.setOnClickListener {
            finish()
        }

        // initialize pet sprites into array for ease of access
        var petSprites = arrayOf(
            ContextCompat.getDrawable(this, R.drawable.tuxedo_card)!!,
            ContextCompat.getDrawable(this, R.drawable.tabby_card)!!,
            ContextCompat.getDrawable(this, R.drawable.orange_card)!!,
            ContextCompat.getDrawable(this, R.drawable.mainecoon_card)!!,
            ContextCompat.getDrawable(this, R.drawable.persian_card)!!,
            ContextCompat.getDrawable(this, R.drawable.russian_card)!!,
            ContextCompat.getDrawable(this, R.drawable.siamese_card)!!,
            ContextCompat.getDrawable(this, R.drawable.munchkin_card)!!,
            ContextCompat.getDrawable(this, R.drawable.scottfold_card)!!,
            ContextCompat.getDrawable(this, R.drawable.sphynx_card)!!,
            ContextCompat.getDrawable(this, R.drawable.thai_card)!!,
            ContextCompat.getDrawable(this, R.drawable.uklev_card)!!,
            ContextCompat.getDrawable(this, R.drawable.lykoi_card)!!,
            ContextCompat.getDrawable(this, R.drawable.norwegian_card)!!,
            ContextCompat.getDrawable(this, R.drawable.selkirk_card)!!,
            ContextCompat.getDrawable(this, R.drawable.bread_cat_card)!!,
            ContextCompat.getDrawable(this, R.drawable.taco_card)!!,
            ContextCompat.getDrawable(this, R.drawable.plastic_boi_card)!!,
            ContextCompat.getDrawable(this, R.drawable.keyboard_card)!!,
            ContextCompat.getDrawable(this, R.drawable.slipper_card)!!,
            ContextCompat.getDrawable(this, R.drawable.aj_card)!!,
            ContextCompat.getDrawable(this, R.drawable.noodles_card)!!,
            ContextCompat.getDrawable(this, R.drawable.marley_card)!!
        )

        var rarities = mapOf(1 to "Common", 2 to "Rare", 3 to "Legendary", 4 to "Mystery", 5 to "Dev");

        // prepare to add cards
        val cardContainer = findViewById<LinearLayout>(R.id.myPetsCardContainer)
        val inflater = LayoutInflater.from(this)

        // initialize draw card func
        fun addCard(pettionaryID: Int, sprite: Drawable?, name: String, breed: String, level: Int, rarity: Int, duplicatesRequired: Int, duplicatesOwned: Int) {
            val cardView = inflater.inflate(R.layout.custom_card_template, cardContainer, false) as CardView

            cardView.findViewById<ImageView>(R.id.SpritePic).setImageDrawable(sprite)
            cardView.findViewById<TextView>(R.id.PetName).text = name
            cardView.findViewById<TextView>(R.id.PetBreed).text = breed
            cardView.findViewById<TextView>(R.id.PetLevel).text = String.format(Locale.ENGLISH,"Lvl %d", level)
            // give rarity correct name and not display int
            cardView.findViewById<TextView>(R.id.PetRarity).text = String.format(Locale.ENGLISH,"Rarity: %s", rarities[rarity])
            // cal income amount and display
            cardView.findViewById<TextView>(R.id.EarningRate).text = String.format(Locale.ENGLISH,"Earning Rate: %d/sec", rarity * level)

            cardView.findViewById<ProgressBar>(R.id.PetProgress).max = duplicatesRequired
            cardView.findViewById<ProgressBar>(R.id.PetProgress).progress = duplicatesOwned

            cardView.findViewById<TextView>(R.id.ProgressText).text = String.format(Locale.ENGLISH,"%d/%d", duplicatesOwned, duplicatesRequired)

            cardContainer.addView(cardView)
        }
        // load owned pets
        for(pet in PlayerData.getPetsOwned())
        {
            if(pet.isOwned())
            {
                var dupRequirements = duplicateRequirements[pet.getRarity()]?.get(pet.getLevel())
                if (dupRequirements != null) {
                    addCard(pet.getPettionaryID(), petSprites[pet.getPettionaryID()-1], pet.getName(), pet.getBreed(), pet.getLevel(), pet.getRarity(), dupRequirements, pet.getDuplicates())
                }
            }
        }
    }

    // map rarity to map of level to duplicates required
    private val duplicateRequirements: Map<Int, Map<Int, Int>> = mapOf(
        1 to mapOf(
            1 to 3,
            2 to 10,
            3 to 20,
            4 to 50,
            5 to 100),
        2 to mapOf(
            1 to 3,
            2 to 10,
            3 to 15,
            4 to 30,
            5 to 50
        ),
        3 to mapOf(
            1 to 3,
            2 to 9,
            3 to 12,
            4 to 24,
            5 to 40
        ),
        4 to mapOf(
            1 to 3,
            2 to 7,
            3 to 10,
            4 to 20,
            5 to 30
        ),
        5 to mapOf(
            1 to 3,
            2 to 5,
            3 to 8,
            4 to 10,
            5 to 15)
    )

    override fun onStop()
    {
        super.onStop()
        musicPlayer.pause()
        musicPlayer.reset()
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.release()
    }
}