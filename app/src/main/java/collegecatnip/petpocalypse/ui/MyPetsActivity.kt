package collegecatnip.petpocalypse.ui;

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import collegecatnip.petpocalypse.R
import java.util.Locale

class MyPetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pets)

        val backButton: ImageButton = findViewById(R.id.pets_exit_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onStart()
    {
        super.onStart()
        val cardContainer = findViewById<LinearLayout>(R.id.myPetsCardContainer)
        val inflater = LayoutInflater.from(this)

        fun addCard(sprite: Drawable?, name: String, breed: String, level: Int, rarity: Int, duplicatesRequired: Int, duplicatesOwned: Int) {
            val cardView = inflater.inflate(R.layout.custom_card_template, cardContainer, false) as CardView
            cardView.findViewById<ImageView>(R.id.SpritePic).setImageDrawable(sprite)
            cardView.findViewById<TextView>(R.id.PetName).text = name
            cardView.findViewById<TextView>(R.id.PetBreed).text = breed
            cardView.findViewById<TextView>(R.id.PetLevel).text = String.format(Locale.ENGLISH,"Lvl %d", level)
            when (rarity) {
                1 -> {cardView.findViewById<TextView>(R.id.PetRarity).text = "Rarity: Common"
                    cardView.findViewById<TextView>(R.id.EarningRate).text = "Earning Rate: 1/sec"}
                2 -> {cardView.findViewById<TextView>(R.id.PetRarity).text = "Rarity: Rare"
                    cardView.findViewById<TextView>(R.id.EarningRate).text = "Earning Rate: 2/sec"}
                3 -> {cardView.findViewById<TextView>(R.id.PetRarity).text = "Rarity: Legendary"
                    cardView.findViewById<TextView>(R.id.EarningRate).text = "Earning Rate: 3/sec"}
                4 -> {cardView.findViewById<TextView>(R.id.PetRarity).text = "Rarity: Mystic"
                    cardView.findViewById<TextView>(R.id.EarningRate).text = "Earning Rate: 4/sec"}
                5 -> {cardView.findViewById<TextView>(R.id.PetRarity).text = "Rarity: Dev"
                    cardView.findViewById<TextView>(R.id.EarningRate).text = "Earning Rate: 5/sec"}


            }
            cardView.findViewById<TextView>(R.id.PetRarity).text = String.format(Locale.ENGLISH,"Rarity: %d", rarity)
            cardView.findViewById<ProgressBar>(R.id.PetProgress).max = duplicatesRequired
            cardView.findViewById<ProgressBar>(R.id.PetProgress).progress = duplicatesOwned

            cardContainer.addView(cardView)
        }

        val template = ContextCompat.getDrawable(this, R.drawable.bread_cat)
        val breadCat = ContextCompat.getDrawable(this, R.drawable.bread_cat)
        val slipperCat = ContextCompat.getDrawable(this, R.drawable.slipper)

        addCard(template,"Template Cat", "Template Cat", 0, 1, 5, 0)
        addCard(breadCat,"Breab Cat", "Bread Cat", 1, 2, 5, 3)
        addCard(slipperCat,"Pope Cat", "Slipper Cat", 0, 3, 5, 1)
        addCard(template,"Template Cat", "Template Cat", 0, 4, 5, 0)
        addCard(breadCat,"Breab Cat", "Bread Cat", 1, 4, 5, 3)
        addCard(slipperCat,"Pope Cat", "Slipper Cat", 0, 5, 5, 1)
        addCard(template,"Template Cat", "Template Cat", 0, 1, 5, 0)
        addCard(breadCat,"Breab Cat", "Bread Cat", 1, 2, 5, 3)
        addCard(slipperCat,"Pope Cat", "Slipper Cat", 0, 5, 5, 1)
    }
}