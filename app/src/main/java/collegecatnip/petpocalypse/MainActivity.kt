package collegecatnip.petpocalypse

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var loveCounter: TextView = findViewById(R.id.love_counter)
    private var treatsCounter: TextView = findViewById(R.id.treats_counter)

    private var loveNum: Int = 0;
    private var treatsNum: Int = 0;

    val runnable = Runnable {
        // Code to be executed
        loveNum+= 1;
        treatsNum+= 1;
        loveCounter.setText(loveNum);
        treatsCounter.setText(treatsNum);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}