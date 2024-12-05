package collegecatnip.petpocalypse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Button
import collegecatnip.petpocalypse.R

class ShopActivity : AppCompatActivity() {

    private lateinit var musicPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        musicPlayer = MediaPlayer.create(this, R.raw.happ_food_loop)
        musicPlayer.isLooping = true;
        musicPlayer.start()

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
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
}