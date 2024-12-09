package collegecatnip.petpocalypse.ui

/*
The Shop Activity
Controls how the shop activity behaves when interacted with

@author Kendall Devich
Date Created: 10/5/2024

Date Modified: 12/8/2024
Patch Notes:
    Added Arrays
    Added event listeners for buttons
    added functions: petDisplay, handleButtonClick, handleImageButtonClick, displaySecret, runMultiplePurchase

 */


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import collegecatnip.petpocalypse.R
import collegecatnip.petpocalypse.backend.Pet
import collegecatnip.petpocalypse.backend.PlayerData
import collegecatnip.petpocalypse.backend.Shop
import java.util.Locale

class ShopActivity : AppCompatActivity() {

    private lateinit var musicPlayer: MediaPlayer
    private lateinit var shop: Shop
    private lateinit var petSprites: Array<Drawable>
    private lateinit var petFrames: Array<Drawable>
    private lateinit var getting: MutableList<Drawable>
    private lateinit var gettingId: ArrayList<Pet>
    private lateinit var player: PlayerData

    var sizes = mapOf(1 to "Small", 2 to "Medium", 3 to "Large", 4 to "Extra Large")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        shop = Shop(PlayerData())
        player = PlayerData()


        musicPlayer = MediaPlayer.create(this, R.raw.happ_food_loop)
        musicPlayer.isLooping = true
        musicPlayer.start()

        //Array of button IDs
        val buttonIds = arrayOf(
            R.id.carrier_basic_money,
            R.id.drop_rate_basic,
            R.id.carrier_silver_money,
            R.id.drop_rate_silver,
            R.id.carrier_gold_money,
            R.id.drop_rate_gold,
            R.id.buy_bed_button,
            R.id.buy_couch_button,
            R.id.buy_plant_button,
            R.id.buy_rug_button,
            R.id.buy_shelf_button,
            R.id.buy_table_button
        )

        //Array of Image Button Ids
        val imageButtonIds = arrayOf(
            R.id.drop_rate_basic_exit,
            R.id.drop_rate_silver_exit,
            R.id.drop_rate_gold_exit,
            R.id.shop_exit_button,
            R.id.carrier_shop_button,
            R.id.room_shop_button,
            R.id.micro_shop_button,
            R.id.card_exit_button,
            R.id.multi_buy_exit_button,
            R.id.multi_buy_frame_1,
            R.id.multi_buy_frame_2,
            R.id.multi_buy_frame_3,
            R.id.multi_buy_frame_4,
            R.id.multi_buy_frame_5,
            R.id.multi_buy_frame_6,
            R.id.multi_buy_frame_7,
            R.id.multi_buy_frame_8,
            R.id.multi_buy_frame_9,
            R.id.multi_buy_frame_10
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

        //Array of Pet Sprites
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

        // Set up button listeners
        for (buttonId in buttonIds) {
            findViewById<Button>(buttonId).setOnClickListener {
                handleButtonClick(buttonId)
            }
        }

        // Set up buy multiple button listeners
        findViewById<Button>(R.id.carrier_basic_money_multiplier).setOnClickListener {
            runMultiplePurchase(1)
        }
        findViewById<Button>(R.id.carrier_silver_money_multiplier).setOnClickListener {
            runMultiplePurchase(2)
        }
        findViewById<Button>(R.id.carrier_gold_money_multiplier).setOnClickListener {
            runMultiplePurchase(3)
        }

        // Set up image buttons
        for (imageButtonId in imageButtonIds) {
            findViewById<ImageButton>(imageButtonId).setOnClickListener {
                handleImageButtonClick(imageButtonId)
            }
        }

        // Dim items already bought
        for(i in 0 until player.multipliers.size)
        {
            if(player.multipliers[i][2] == 1.0)
            {
                when(i)
                {
                    0 -> findViewById<ImageView>(R.id.buy_bed).setImageResource(R.drawable.bed_dimmed)
                    1 -> findViewById<ImageView>(R.id.buy_couch).setImageResource(R.drawable.couch_dimmed)
                    2 -> findViewById<ImageView>(R.id.buy_plant).setImageResource(R.drawable.plant_dimmed)
                    3 -> findViewById<ImageView>(R.id.buy_rug).setImageResource(R.drawable.rug_dimmed)
                    4 -> findViewById<ImageView>(R.id.buy_shelf).setImageResource(R.drawable.shelf_dimmed)
                    5 -> findViewById<ImageView>(R.id.buy_table).setImageResource(R.drawable.table_dimmed)
                }
            }
        }
    }

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

    // Error, not recieving button press
    @SuppressLint("SetTextI18n")
    private fun handleButtonClick(buttonId: Int) {
        when (buttonId) {
            R.id.carrier_basic_money-> {
                // Handle basic button click
                var new_pet: Pet = shop.buyBasicPetCarrier()
                if (new_pet != null)
                    petDisplay(new_pet)
                else {
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.drop_rate_basic -> {
                //Display the basic drop rate
                if (findViewById<View>(R.id.drop_rate_basic_popup).visibility == View.GONE)
                    //Display the drop rate
                    findViewById<View>(R.id.drop_rate_basic_popup).visibility = View.VISIBLE
                else
                    //If it's being displayed do nothing
                    findViewById<View>(R.id.drop_rate_basic_popup).visibility = View.GONE
            }
            R.id.carrier_silver_money -> {
                // Handle silver button click
                var new_pet: Pet = shop.buySilverPetCarrier()
                if (new_pet != null)
                    petDisplay(new_pet)
                else {
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.drop_rate_silver -> {
                // Display the silver drop rate
                if (findViewById<View>(R.id.drop_rate_silver_popup).visibility == View.VISIBLE)
                    //If it's being displayed do nothing
                    findViewById<View>(R.id.drop_rate_silver_popup).visibility = View.GONE
                else
                //Display the drop rate
                    findViewById<View>(R.id.drop_rate_silver_popup).visibility = View.VISIBLE
            }
            R.id.carrier_gold_money -> {
                // Handle gold button click
                var new_pet: Pet = shop.buyGoldPetCarrier()
                if (new_pet != null)
                    petDisplay(new_pet)
                else {
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.drop_rate_gold -> {
                // Display the gold drop rate
                if (findViewById<View>(R.id.drop_rate_gold_popup).visibility == View.VISIBLE)
                    //If it's being displayed do nothing
                    findViewById<View>(R.id.drop_rate_gold_popup).visibility = View.GONE
                else
                //Display the drop rate
                    findViewById<View>(R.id.drop_rate_gold_popup).visibility = View.VISIBLE
            }
            R.id.buy_bed_button -> {
                // Handle buy bed button click
                var bed = shop.buyRoomItem(0)
                if (bed)
                    findViewById<ImageView>(R.id.buy_bed).setImageResource(R.drawable.bed_dimmed)
                else{
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.buy_couch_button -> {
                // Handle buy couch button click
                var couch = shop.buyRoomItem(1)
                if (couch)
                    findViewById<ImageView>(R.id.buy_couch).setImageResource(R.drawable.couch_dimmed)
                else{
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.buy_plant_button -> {
                // Handle buy plant button click
                var plant = shop.buyRoomItem(2)
                if (plant)
                    findViewById<ImageView>(R.id.buy_plant).setImageResource(R.drawable.plant_dimmed)
                else{
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.buy_rug_button -> {
                // Handle buy rug button click
                var rug = shop.buyRoomItem(3)
                if (rug)
                    findViewById<ImageView>(R.id.buy_rug).setImageResource(R.drawable.rug_dimmed)
                else{
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.buy_shelf_button -> {
                // Handle buy shelf button click
                var shelf = shop.buyRoomItem(4)
                if (shelf)
                    findViewById<ImageView>(R.id.buy_shelf).setImageResource(R.drawable.shelf_dimmed)
                else{
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
            R.id.buy_table_button -> {
                // Handle buy table button click
                var table = shop.buyRoomItem(5)
                if (table)
                    findViewById<ImageView>(R.id.buy_table).setImageResource(R.drawable.table_dimmed)
                else{
                    findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                }
            }
        }
    }

    @SuppressLint("CutPasteId")
    private fun handleImageButtonClick(imageButtonId: Int) {
        when (imageButtonId) {
            R.id.drop_rate_basic_exit -> {
                //Exit the basic drop rate
                if (findViewById<View>(R.id.drop_rate_basic_popup).visibility == View.VISIBLE)
                //If it's being displayed hide it
                    findViewById<View>(R.id.drop_rate_basic_popup).visibility = View.GONE
            }
            R.id.drop_rate_silver_exit -> {
                // Exit the silver drop rate
                if (findViewById<View>(R.id.drop_rate_silver_popup).visibility == View.VISIBLE)
                //If it's being displayed hide it
                    findViewById<View>(R.id.drop_rate_silver_popup).visibility = View.GONE
            }
            R.id.drop_rate_gold_exit -> {
                // Exit the gold drop rate
                if (findViewById<View>(R.id.drop_rate_gold_popup).visibility == View.VISIBLE)
                //If it's being displayed hide it
                    findViewById<View>(R.id.drop_rate_gold_popup).visibility = View.GONE
            }
            R.id.shop_exit_button -> {
                // Handle shop exit button click
                finish()
            }
            R.id.carrier_shop_button -> {
                // Handle carrier shop button click
                // Check if it's already being displayed
                if (findViewById<View>(R.id.carrier_shop_layout).visibility == View.VISIBLE) {
                    //If it's being displayed do nothing
                } else {
                    // If it's not being displayed, hide the other views and display it
                    findViewById<View>(R.id.carrier_shop_layout).visibility = View.VISIBLE
                    findViewById<View>(R.id.room_shop_layout).visibility = View.GONE
                    findViewById<View>(R.id.micro_shop_layout).visibility = View.GONE
                }
            }
            R.id.room_shop_button -> {
                // Handle room shop button click
                // Check if it's already being displayed
                if (findViewById<View>(R.id.room_shop_layout).visibility == View.VISIBLE) {
                    //If it's being displayed do nothing
                } else {
                    // If it's not being displayed, hide the other views and display it
                    findViewById<View>(R.id.carrier_shop_layout).visibility = View.GONE
                    findViewById<View>(R.id.room_shop_layout).visibility = View.VISIBLE
                    findViewById<View>(R.id.micro_shop_layout).visibility = View.GONE
                }
            }
            R.id.micro_shop_button -> {
                // Handle micro shop button click
                // Check if it's already being displayed
                if (findViewById<View>(R.id.micro_shop_layout).visibility == View.VISIBLE) {
                    //If it's being displayed do nothing
                } else {
                    // If it's not being displayed, hide the other views and display it
                    findViewById<View>(R.id.carrier_shop_layout).visibility = View.GONE
                    findViewById<View>(R.id.room_shop_layout).visibility = View.GONE
                    findViewById<View>(R.id.micro_shop_layout).visibility = View.VISIBLE
                }
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
                findViewById<View>(R.id.card_exit_button).visibility = View.GONE
            }
            R.id.multi_buy_exit_button -> {
                // Handle multi buy exit button click
                findViewById<View>(R.id.multi_buy_dim).visibility = View.GONE
                findViewById<View>(R.id.multi_back).visibility = View.GONE
                findViewById<View>(R.id.multi_buy_frames).visibility = View.GONE
                getting.clear()
                gettingId.clear()
            }
            R.id.multi_buy_frame_1 -> {
                petDisplay(gettingId[0])
            }
            R.id.multi_buy_frame_2 -> {
                petDisplay(gettingId[1])
            }
            R.id.multi_buy_frame_3 -> {
                petDisplay(gettingId[2])
            }
            R.id.multi_buy_frame_4 -> {
                petDisplay(gettingId[3])
            }
            R.id.multi_buy_frame_5 -> {
                petDisplay(gettingId[4])
            }
            R.id.multi_buy_frame_6 -> {
                petDisplay(gettingId[5])
            }
            R.id.multi_buy_frame_7 -> {
                petDisplay(gettingId[6])
            }
            R.id.multi_buy_frame_8 -> {
                petDisplay(gettingId[7])
            }
            R.id.multi_buy_frame_9 -> {
                petDisplay(gettingId[8])
            }
            R.id.multi_buy_frame_10 -> {
                petDisplay(gettingId[9])
            }
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
        findViewById<View>(R.id.card_exit_button).visibility = View.VISIBLE
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

    private fun runMultiplePurchase(tier: Int) {
        //Basic Tier
        if (tier == 1){
            //Check for money
            if (player.getLove() >= 1000) {
                findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                return
            }

            //Run the purchase add images to mutablelist and pets to the arraylist
            for (i in 1..10) {
                var new_pet: Pet = shop.buyBasicPetCarrier()
                getting.add(petFrames[new_pet.getPettionaryID()-1])
                gettingId.add(new_pet)
            }
            //Display the images
            displayMultiple(getting)
        }
        else if (tier == 2){
            //Check for money
            if (player.getLove() >= 2000) {
                findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                return
            }

            //Run the purchase add images to mutablelist and pets to the arraylist
            for (i in 1..10) {
                var new_pet: Pet = shop.buySilverPetCarrier()
                getting.add(petFrames[new_pet.getPettionaryID()-1])
                gettingId.add(new_pet)
            }
            //Display the images
            displayMultiple(getting)
        }
        else if (tier == 3){
            //Check for money
            if (player.getLove() >= 3000) {
                findViewById<View>(R.id.shop_error_message).visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({findViewById<View>(R.id.shop_error_message).visibility = View.GONE }, 2500)
                return
            }

            //Run the purchase add images to mutablelist and pets to the arraylist
            for (i in 1..10) {
                var new_pet: Pet = shop.buyGoldPetCarrier()
                getting.add(petFrames[new_pet.getPettionaryID()-1])
                gettingId.add(new_pet)
            }
            //Display the images
            displayMultiple(getting)
        }
        else
            return
    }

    private fun displayMultiple(getting: MutableList<Drawable>) {
        findViewById<ImageView>(R.id.multi_buy_dim).visibility = View.VISIBLE
        findViewById<ImageView>(R.id.multi_back).visibility = View.VISIBLE
        findViewById<View>(R.id.multi_buy_frames).visibility = View.VISIBLE
        findViewById<View>(R.id.multi_buy_exit_button).visibility = View.VISIBLE

        findViewById<ImageButton>(R.id.multi_buy_frame_1).setImageDrawable(getting[0])
        findViewById<ImageButton>(R.id.multi_buy_frame_2).setImageDrawable(getting[1])
        findViewById<ImageButton>(R.id.multi_buy_frame_3).setImageDrawable(getting[2])
        findViewById<ImageButton>(R.id.multi_buy_frame_4).setImageDrawable(getting[3])
        findViewById<ImageButton>(R.id.multi_buy_frame_5).setImageDrawable(getting[4])
        findViewById<ImageButton>(R.id.multi_buy_frame_6).setImageDrawable(getting[5])
        findViewById<ImageButton>(R.id.multi_buy_frame_7).setImageDrawable(getting[6])
        findViewById<ImageButton>(R.id.multi_buy_frame_8).setImageDrawable(getting[7])
        findViewById<ImageButton>(R.id.multi_buy_frame_9).setImageDrawable(getting[8])
        findViewById<ImageButton>(R.id.multi_buy_frame_10).setImageDrawable(getting[9])

    }
}