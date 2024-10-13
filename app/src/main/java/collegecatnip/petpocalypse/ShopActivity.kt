package collegecatnip.petpocalypse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import collegecatnip.petpocalypse.ui.petbox.PetBoxFragment

class ShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PetBoxFragment.newInstance())
                .commitNow()
        }
    }
}