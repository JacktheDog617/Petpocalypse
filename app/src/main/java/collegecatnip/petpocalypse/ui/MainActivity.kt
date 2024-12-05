package collegecatnip.petpocalypse.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.media.SoundPool
import android.widget.TextView
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import collegecatnip.petpocalypse.R
import collegecatnip.petpocalypse.backend.IncomeCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import collegecatnip.petpocalypse.backend.PlayerData
import collegecatnip.petpocalypse.backend.PlayerSave
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var musicPlayer: MediaPlayer
    private lateinit var sfxPlayer: SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Load player data
        val catsFilePath = File(filesDir, "cat.json").absolutePath
        //var save = PlayerSave(getFilesDir().getAbsolutePath(), catsFilePath)
        //save.loadPlayerData()
        //var playerData = PlayerData()

        // Handle insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom) // Apply bottom padding
            WindowInsetsCompat.CONSUMED
        }

        musicPlayer = MediaPlayer.create(this, R.raw.childhood_loop)
        musicPlayer.isLooping = true;
        musicPlayer.start()

        sfxPlayer = SoundPool.Builder().setMaxStreams(10).build()
        var buttonClick: Int
        buttonClick = sfxPlayer.load(this, R.raw.bubble_sound, 1)

        val shopButton: ImageButton = findViewById(R.id.shop_button)
        shopButton.setOnClickListener {
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        val roomButton: ImageButton = findViewById(R.id.room_button)
        roomButton.setOnClickListener {
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            val intent = Intent(this, RoomEditorActivity::class.java)
            startActivity(intent)
        }

        val petsButton: ImageButton = findViewById(R.id.pets_button)
        petsButton.setOnClickListener {
            sfxPlayer.play(buttonClick, 1f, 1f, 0, 0, 1f)
            val intent = Intent(this, MyPetsActivity::class.java)
            startActivity(intent)
        }

        var loveCounter: TextView = findViewById(R.id.love_counter)
        var treatsCounter: TextView = findViewById(R.id.treats_counter)

        //var loveNum: Long = playerData.getLove();
        //var treatsNum: Int = playerData.getTreats();

        var loveNum: Long = 0;
        var treatsNum: Int = 0;

        //var incomeCalc = IncomeCalculator();
        //var incomeIncrease: Long = incomeCalc.calculateIncome();
        var incomeIncrease: Long = 1;

        loveCounter.text = loveNum.toString();
        treatsCounter.text = treatsNum.toString();

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

    override fun onStop()
    {
        super.onStop()
        musicPlayer.pause()
        musicPlayer.reset()
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.release()
        sfxPlayer.release()
    }
}