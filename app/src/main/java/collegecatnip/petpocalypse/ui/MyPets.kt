package collegecatnip.petpocalypse.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import collegecatnip.petpocalypse.R
import collegecatnip.petpocalypse.ui.petbox.PetBoxFragment

class MyPets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PetBoxFragment.newInstance())
                .commitNow()
        }

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}