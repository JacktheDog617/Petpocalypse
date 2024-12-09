package collegecatnip.petpocalypse.ui

/**
 * RoomEditorActivity for Petpocalypse
 *
 * @author Jaime Lee
 * @Date 12/01/24
 *
 * @author Jaime Lee
 * Date last modified: 12/06/2024
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.media.MediaPlayer
import android.widget.ImageButton
import collegecatnip.petpocalypse.R

class RoomEditorActivity : AppCompatActivity() {
    private lateinit var musicPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roomeditor)

        // set up funny construction noise for WIP filler
        musicPlayer = MediaPlayer.create(this, R.raw.construction_sound)
        musicPlayer.isLooping = true
        musicPlayer.start()

        // button listener to go back to main activity
        val backButton: ImageButton = findViewById(R.id.editor_exit_button)
        backButton.setOnClickListener {
            musicPlayer.pause()
            finish()
        }
    }

    override fun onStop()
    {
        super.onStop()
        // reset music
        musicPlayer.reset()
    }

    override fun onDestroy() {
        super.onDestroy()
        // release unused resources
        musicPlayer.release()
    }
}