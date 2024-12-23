package collegecatnip.petpocalypse.ui

/**
 * Main Activity for Petpocalypse
 * onCreate() loads music and button listeners
 *
 * @author Jaime Lee
 * @date 10/13/2024
 *
 * @author Jaime Lee
 * Date last modified: 12/07/2024
 * Patch Notes:
 *      Added burger boi
 */

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import collegecatnip.petpocalypse.R
import collegecatnip.petpocalypse.backend.IncomeCalculator
import collegecatnip.petpocalypse.backend.PlayerData
import collegecatnip.petpocalypse.backend.PlayerSave
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // global values
    private var loveNum: Long = 0
    private var treatsNum: Int = 0

    // Sounds
    private lateinit var musicPlayer: MediaPlayer
    private lateinit var sfxPlayer: SoundPool

    // runs on creation of app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Load player data
        // No int for no presave
        /*
        1 = new save
        2 = partial filled save
        3 = finished game state
         */
        var save = PlayerSave(getApplicationContext(), 3)
        var playerData = PlayerData()

        playerData.love = 1100

        // Handle insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom) // Apply bottom padding
            view.updatePadding(top = insets.top) // Apply top padding
            WindowInsetsCompat.CONSUMED
        }

        // Run background music
        musicPlayer = MediaPlayer.create(this, R.raw.childhood_loop)
        musicPlayer.isLooping = true
        musicPlayer.start()

        // Set up sound effects for button
        sfxPlayer = SoundPool.Builder().setMaxStreams(10).build()
        var buttonClick: Int
        buttonClick = sfxPlayer.load(this, R.raw.bubble_sound, 1)

        // Set up button listeners
        val burgerboi: ImageButton = findViewById(R.id.burgerboi)
        burgerboi.setOnClickListener {
            // play button noise
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            var cardView = findViewById<View>(R.id.dropdown)
            if (cardView.visibility == View.VISIBLE) {
                cardView.visibility = View.GONE
                cardView = findViewById<View>(R.id.credits)
                if (cardView.visibility == View.VISIBLE) {
                    cardView.visibility = View.GONE
                }
                cardView = findViewById<View>(R.id.stats)
                if (cardView.visibility == View.VISIBLE) {
                    cardView.visibility = View.GONE
                }
            } else {
                cardView.visibility = View.VISIBLE
            }
        }

        // Pettionary button
        val pettionaryButton: Button = findViewById(R.id.pettionaryButton)
        pettionaryButton.setOnClickListener {
            // play button noise
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            // stop music
            musicPlayer.pause()
            val intent = Intent(this, PettionaryActivity::class.java)
            startActivity(intent)
        }

        // Credits button
        val creditsButton: Button = findViewById(R.id.creditsButton)
        creditsButton.setOnClickListener {
            // play button noise
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            val cardView = findViewById<View>(R.id.credits)
            if (cardView.visibility == View.VISIBLE) {
                cardView.visibility = View.GONE
            } else {
                cardView.visibility = View.VISIBLE
            }
        }

        // Stats button
        val statsButton: Button = findViewById(R.id.statsButton)
        statsButton.setOnClickListener {
            // play button noise
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            val cardView = findViewById<View>(R.id.stats)
            if (cardView.visibility == View.VISIBLE) {
                cardView.visibility = View.GONE
            } else {
                var petCount = 0;
                var EarningRate = 0;
                for(pet in PlayerData.getPetsOwned())
                {
                    if(pet.isOwned)
                    {
                        petCount++;
                        EarningRate += (pet.getRarity() * pet.getLevel())
                    }
                }
                cardView.findViewById<TextView>(R.id.TotalCats).text = String.format(Locale.ENGLISH,"Total Cats Owned: %d", petCount)
                cardView.findViewById<TextView>(R.id.EarningRate).text = String.format(Locale.ENGLISH,"Earning Rate: %d", EarningRate)

                var multipliers = playerData.multipliers
                var totalMultiplier = 0.0;
                for (i in 0 until multipliers.size)
                {
                    if(multipliers[i][2] == 1.0)
                    {
                        totalMultiplier += 0.5;
                    }
                }
                cardView.findViewById<TextView>(R.id.Multipliers).text = String.format(Locale.ENGLISH,"Total Multiplier: %.1f", totalMultiplier)

                cardView.visibility = View.VISIBLE
            }
        }

        val shopButton: ImageButton = findViewById(R.id.shop_button)
        shopButton.setOnClickListener {
            // play button noise
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            // stop music
            musicPlayer.pause()
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        val roomButton: ImageButton = findViewById(R.id.room_button)
        roomButton.setOnClickListener {
            // play button noise
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            // stop music
            musicPlayer.pause()
            val intent = Intent(this, RoomEditorActivity::class.java)
            startActivity(intent)
        }

        val petsButton: ImageButton = findViewById(R.id.pets_button)
        petsButton.setOnClickListener {
            // play button noise
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            // stop music
            musicPlayer.pause()
            val intent = Intent(this, MyPetsActivity::class.java)
            startActivity(intent)
        }

        // Set up love and treat counters
        var loveCounter: TextView = findViewById(R.id.love_counter)
        var treatsCounter: TextView = findViewById(R.id.treats_counter)

        // Calculate income
        var incomeCalc = IncomeCalculator();
        var incomeIncrease: Long = incomeCalc.calculateIncome();

        // Update love and treat counters
        loveCounter.text = loveNum.toString();
        treatsCounter.text = treatsNum.toString();

        // Update love counter every second
        lifecycleScope.launch {
            while (isActive) {
                delay(1000)
                playerData.setLove(playerData.getLove() + incomeIncrease); // Increment currency
                var loveNum = playerData.getLove() // ; // Save currency
                withContext(Dispatchers.Main) {
                    if (loveNum / 1000 < 1) // if under 1000
                    {                       // show user 1000
                        loveCounter.text = String.format(Locale.ENGLISH,"%,d", loveNum);
                    }
                    else if (loveNum / 1000000 < 1) // if under 1,000,000
                    {                               // show user 1-999K
                        loveCounter.text = String.format(Locale.ENGLISH,"%.1fK", (loveNum/1000f))
                    }
                    else if (loveNum / 1000000000 < 1) // if under 1,000,000,000
                    {                                  // show user 1-999M
                        loveCounter.text = String.format(Locale.ENGLISH,"%.1fM", (loveNum/1000000f))
                    }
                    else if (loveNum / 1000000000000 < 1) // if under 1,000,000,000,000
                    {                                     // show user 1-999B
                        loveCounter.text = String.format(Locale.ENGLISH,"%.1fB", (loveNum/1000000000f))
                    }
                    else if(loveNum / 1000000000000000 < 1) // if under 1,000,000,000,000,000
                    {                                       // show user 1-999T
                        loveCounter.text = String.format(Locale.ENGLISH,"%.1fT", (loveNum/1000000000000f))
                    }
                    else // if over 1,000,000,000,000,000
                    {    // show user 1-999aa
                        loveCounter.text = String.format(Locale.ENGLISH,"%.1faa", (loveNum/1000000000000000f))
                    }
                }
            }
        }
    }

    override fun onStart()
    {
        super.onStart()

        // update any currency changes
        var playerData = PlayerData()
        loveNum = playerData.getLove()
        treatsNum = playerData.getTreats()

        var items = arrayOf(
            findViewById<ImageView>(R.id.item0),
            findViewById<ImageView>(R.id.item1),
            findViewById<ImageView>(R.id.item2),
            findViewById<ImageView>(R.id.item3),
            findViewById<ImageView>(R.id.item4),
            findViewById<ImageView>(R.id.item5),
        )

        var player = PlayerData();
        var multipliers = player.multipliers

        for (i in 0 until multipliers.size)
        {
            if(multipliers[i][2] == 1.0)
            {
                items[i].visibility = View.VISIBLE
            }
        }

        // initialize pet sprites into array for ease of access
        var petSprites = arrayOf(
            R.drawable.room_tuxedo,
            R.drawable.room_tabby,
            R.drawable.room_orange,
            R.drawable.room_mainecoon,
            R.drawable.room_persian,
            R.drawable.room_russian,
            R.drawable.room_siamese,
            R.drawable.room_munchkin,
            R.drawable.room_scottish,
            R.drawable.room_sphynx,
            R.drawable.room_thai,
            R.drawable.room_ukrainian,
            R.drawable.room_lykoi,
            R.drawable.room_norwegian,
            R.drawable.room_selkirk,
            R.drawable.room_bread,
            R.drawable.room_taco,
            R.drawable.room_plastic,
            R.drawable.room_keyboard,
            R.drawable.room_slipper,
            R.drawable.room_aj,
            R.drawable.room_noodles,
            R.drawable.room_marley
        )

        // find place to add cats
        val catContainer = findViewById<FrameLayout>(R.id.room_layout)

        // initialize draw cat func
        fun addCat(pettionaryID: Int) {
            val imageView = ImageView(this)
            imageView.setImageResource(petSprites[pettionaryID-1])
            val layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            // Adjust layoutParams as needed based on RoomLayout's structure
            imageView.layoutParams = layoutParams
            catContainer.addView(imageView)
        }

        for(pet in PlayerData.getPetsOwned())
        {
            if(pet.isOwned()) {
                addCat(pet.pettionaryID)
            }
        }
    }

    override fun onStop()
    {
        super.onStop()
        // reset background music
        musicPlayer.reset()
    }

    override fun onDestroy() {
        super.onDestroy()
        // release unused resources
        musicPlayer.release()
        sfxPlayer.release()
    }
}