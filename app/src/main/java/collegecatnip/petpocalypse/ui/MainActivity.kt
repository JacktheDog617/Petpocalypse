package collegecatnip.petpocalypse.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import collegecatnip.petpocalypse.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Handle insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = insets.bottom) // Apply bottom padding
            WindowInsetsCompat.CONSUMED
        }

        val shopButton: ImageButton = findViewById(R.id.shop_button)
        shopButton.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        val roomButton: ImageButton = findViewById(R.id.room_button)
        roomButton.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        val petsButton: ImageButton = findViewById(R.id.pets_button)
        petsButton.setOnClickListener {
            val intent = Intent(this, MyPets::class.java)
            startActivity(intent)
        }

        var loveCounter: TextView = findViewById(R.id.love_counter)
        var treatsCounter: TextView = findViewById(R.id.treats_counter)

        var loveNum: Int = 0;
        var treatsNum: Int = 0;

        loveCounter.text = loveNum.toString();
        treatsCounter.text = treatsNum.toString();

        lifecycleScope.launch {
            while (isActive) {
                delay(1000)
                loveNum += 1000; // Increment currency
                withContext(Dispatchers.Main) {
                    if (loveNum / 1000 < 1) {
                        loveCounter.text = loveNum.toString() // Update UI
                    }
                    else if (loveNum / 1000000 < 1)
                    {
                        loveCounter.text = ((floor((loveNum/1000).toDouble()))).toInt().toString() + "K";
                    }
                    else
                    {
                        loveCounter.text = ((floor((loveNum/1000000).toDouble()))).toInt().toString() + "M";
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}