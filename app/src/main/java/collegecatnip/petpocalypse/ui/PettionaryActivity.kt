package collegecatnip.petpocalypse.ui

/*
The Pettionary Activity
Controls how the pettionary activity behaves when interacted with

@author Kendall Devich
Date Created: 12/7/2024
 */

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import collegecatnip.petpocalypse.R
import collegecatnip.petpocalypse.backend.Pet
import collegecatnip.petpocalypse.backend.PlayerData
import java.util.Locale

class PettionaryActivity : AppCompatActivity() {

    private lateinit var petFrames: Array<Drawable>
    private lateinit var petSprites: Array<Drawable>
    private lateinit var petReal: Array<Drawable>
    private lateinit var boba: ArrayList<Pet> //copy of player petsOwned named boba cause bored

    var sizes = mapOf(1 to "Small", 2 to "Medium", 3 to "Large", 4 to "Extra Large")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pettionary)

        //Array of image button ids to loop through to set click listeners
        val imageButtonIds = arrayOf(
            R.id.pettionary_secret_button,
            R.id.pettionary_exit_button,
            R.id.card_exit_button,
        )

        //Array of pet card ids
        val petCards = arrayOf(
            R.id.pettionary_card_1,
            R.id.pettionary_card_2,
            R.id.pettionary_card_3,
            R.id.pettionary_card_4,
            R.id.pettionary_card_5,
            R.id.pettionary_card_6,
            R.id.pettionary_card_7,
            R.id.pettionary_card_8,
            R.id.pettionary_card_9,
            R.id.pettionary_card_10,
            R.id.pettionary_card_11,
            R.id.pettionary_card_12,
            R.id.pettionary_card_13,
            R.id.pettionary_card_14,
            R.id.pettionary_card_15,
            R.id.pettionary_card_16,
            R.id.pettionary_card_17,
            R.id.pettionary_card_18,
            R.id.pettionary_card_19,
            R.id.pettionary_card_20,
            R.id.pettionary_card_21,
            R.id.pettionary_card_22,
            R.id.pettionary_card_23
        )

        //Array of pet sprites in frames
        petFrames = arrayOf(
            ContextCompat.getDrawable(this, R.drawable.tuxedo_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.tabby_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.orange_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.mainecoon_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.persian_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.russian_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.siamese_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.munchkin_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.scottish_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.sphynx_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.thai_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.ukranian_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.lykoi_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.norwegian_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.selkirk_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.bread_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.taco_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.plastic_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.keyboard_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.slipper_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.aj_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.noodles_frame)!!,
            ContextCompat.getDrawable(this, R.drawable.marley_frame)!!
        )

        //Array of pet sprites in cards
        petSprites = arrayOf(
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

        //Array of real pet images
        petReal = arrayOf(
            ContextCompat.getDrawable(this, R.drawable.real_tuxedo)!!,
            ContextCompat.getDrawable(this, R.drawable.real_tabby)!!,
            ContextCompat.getDrawable(this, R.drawable.real_orange)!!,
            ContextCompat.getDrawable(this, R.drawable.real_mainecoon)!!,
            ContextCompat.getDrawable(this, R.drawable.real_persian)!!,
            ContextCompat.getDrawable(this, R.drawable.real_russian)!!,
            ContextCompat.getDrawable(this, R.drawable.real_siamese)!!,
            ContextCompat.getDrawable(this, R.drawable.real_munchkin)!!,
            ContextCompat.getDrawable(this, R.drawable.real_scottish)!!,
            ContextCompat.getDrawable(this, R.drawable.real_sphynx)!!,
            ContextCompat.getDrawable(this, R.drawable.real_thai)!!,
            ContextCompat.getDrawable(this, R.drawable.real_ukranian)!!,
            ContextCompat.getDrawable(this, R.drawable.real_lykoi)!!,
            ContextCompat.getDrawable(this, R.drawable.real_norwegian)!!,
            ContextCompat.getDrawable(this, R.drawable.real_selkirk)!!,
            ContextCompat.getDrawable(this, R.drawable.real_bread)!!,
            ContextCompat.getDrawable(this, R.drawable.real_taco)!!,
            ContextCompat.getDrawable(this, R.drawable.real_plastic)!!,
            ContextCompat.getDrawable(this, R.drawable.real_keyboard)!!,
            ContextCompat.getDrawable(this, R.drawable.real_slipper)!!,
            ContextCompat.getDrawable(this, R.drawable.real_aj)!!,
            ContextCompat.getDrawable(this, R.drawable.real_noodles)!!,
            ContextCompat.getDrawable(this, R.drawable.real_marley)!!
        )

        //Copy petsOwned from playerData for use in pettionary
        boba = PlayerData.getPetsOwned()

        //Loop through the image button ids and set click listeners & image displays
        for (imageButtonId in imageButtonIds) {
            findViewById<ImageButton>(imageButtonId).setOnClickListener {
                handleImageButtonClick(imageButtonId)
            }
        }

        for (petCards in petCards) {
            findViewById<ImageView>(petCards).setOnClickListener {
                petCardClick(petCards)
            }
        }

    }

    private fun handleImageButtonClick(imageButtonId: Int) {
        when (imageButtonId) {
            R.id.pettionary_secret_button -> {
                // Handle secret button click

            }

            R.id.pettionary_exit_button -> {
                finish()
            }

            R.id.card_exit_button -> {
                // Handle card exit button click
                findViewById<View>(R.id.pettionary_card_dim).visibility = View.GONE
                findViewById<View>(R.id.pettionary_card_text_layout).visibility = View.GONE
                findViewById<View>(R.id.common_pettionary_card).visibility = View.GONE
                findViewById<View>(R.id.rare_pettionary_card).visibility = View.GONE
                findViewById<View>(R.id.legendary_pettionary_card).visibility = View.GONE
                findViewById<View>(R.id.mystery_pettionary_card).visibility = View.GONE
                findViewById<View>(R.id.dev_pettionary_card).visibility = View.GONE
            }
        }
    }

    private fun petCardClick(petCards: Int) {
        //Sets the information into the card
        if(boba[petCards].isOwned == true) {
            findViewById<ImageView>(petCards).setImageDrawable(petFrames[boba[petCards].pettionaryID - 1])
            petDisplay(boba[petCards])
        }
    }

    private fun petDisplay(new_pet: Pet) {
            //Sets the information into the card
            findViewById<TextView>(R.id.pettionary_number).text = new_pet.getPettionaryID().toString()
            findViewById<TextView>(R.id.pettionary_breed).text = new_pet.getBreed()

            //Checks the rarity and sets the card and fish images accordingly
            when (new_pet.getRarity()) {
                1 -> {
                    findViewById<ImageView>(R.id.pettionary_rarity_image).setImageResource(R.drawable.one_fish)
                    findViewById<ImageView>(R.id.common_pettionary_card).visibility = View.VISIBLE}
                2 -> {
                    findViewById<ImageView>(R.id.pettionary_rarity_image).setImageResource(R.drawable.two_fish)
                    findViewById<ImageView>(R.id.rare_pettionary_card).visibility = View.VISIBLE}
                3 -> {
                    findViewById<ImageView>(R.id.pettionary_rarity_image).setImageResource(R.drawable.three_fish)
                    findViewById<ImageView>(R.id.legendary_pettionary_card).visibility = View.VISIBLE}
                4 -> {
                    findViewById<ImageView>(R.id.pettionary_rarity_image).setImageResource(R.drawable.four_fish)
                    findViewById<ImageView>(R.id.mystery_pettionary_card).visibility = View.VISIBLE}
                5 -> {
                    findViewById<ImageView>(R.id.pettionary_rarity_image).setImageResource(R.drawable.five_fish)
                    findViewById<ImageView>(R.id.dev_pettionary_card).visibility = View.VISIBLE}
            }

            findViewById<TextView>(R.id.pettionary_size).text = String.format(Locale.ENGLISH, "Size: %s", sizes[new_pet.getSize()])
            findViewById<ImageView>(R.id.pettionary_image).setImageDrawable(petSprites[new_pet.pettionaryID-1])

            findViewById<TextView>(R.id.pettionary_flavor).text = "Flavor Text: " + new_pet.flavorText
            if (displaySecret(new_pet.getRarity(), new_pet.getLevel(), new_pet.getDuplicates()))
                findViewById<TextView>(R.id.pettionary_secret).text = "Secret: " + new_pet.secret
            else
                findViewById<TextView>(R.id.pettionary_secret).text = "Secret: ???"

            findViewById<View>(R.id.pettionary_card_dim).visibility = View.VISIBLE
            findViewById<View>(R.id.pettionary_card_text_layout).visibility = View.VISIBLE
        }

    private fun displaySecret(rarity: Int, level: Int, duplicates: Int): Boolean {
            if (rarity == 1 && level == 5 && duplicates == 100) {
                return true
            } else if (rarity == 2 && level == 5 && duplicates == 50) {
                return true
            } else if (rarity == 3 && level == 5 && duplicates == 40) {
                return true
            } else if (rarity == 4 && level == 5 && duplicates == 30) {
                return true
            } else if (rarity == 5 && level == 5 && duplicates == 15){
                return true
            } else
                return false
        }
}

