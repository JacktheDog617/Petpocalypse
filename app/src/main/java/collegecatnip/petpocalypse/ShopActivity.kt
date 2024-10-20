package collegecatnip.petpocalypse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import collegecatnip.petpocalypse.ui.petbox.PetBoxFragment

class ShopActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, PetBoxFragment.newInstance())
//                .commitNow()
//        }
    }

    private fun configureBackButton(){
        val backButton = findViewById<Button>(R.id.back_button);
        backButton.setOnClickListener {
            finish()
        }
    }
}