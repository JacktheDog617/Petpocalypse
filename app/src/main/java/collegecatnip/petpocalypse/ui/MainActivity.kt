package collegecatnip.petpocalypse.ui

/**
 * Main Activity for Petpocalypse
 * onCreate() loads music and button listeners
 *
 * @author Jaime Lee
 * @date 10/13/2024
 *
 * @author Jaime Lee
 * Date last modified: 12/06/2024
 * Patch Notes:
 *      Added room items
 */

import android.content.Intent
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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

    // Sounds
    private lateinit var musicPlayer: MediaPlayer
    private lateinit var sfxPlayer: SoundPool

    // runs on creation of app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Load player data
        var save = PlayerSave(getApplicationContext(), 3)
        var playerData = PlayerData()

        // Handle insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom) // Apply bottom padding
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

        // Update love and treat counters
        var loveNum: Long = playerData.getLove();
        var treatsNum: Int = playerData.getTreats();

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
                loveNum += incomeIncrease; // Increment currency
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

        var items = arrayOf(
            findViewById<ImageView>(R.id.item1),
            findViewById<ImageView>(R.id.item2),
            findViewById<ImageView>(R.id.item3),
            findViewById<ImageView>(R.id.item4),
            findViewById<ImageView>(R.id.item5),
            findViewById<ImageView>(R.id.item6),
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