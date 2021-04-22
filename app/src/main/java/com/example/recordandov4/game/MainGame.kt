package com.example.recordandov4.game

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recordandov4.R
import kotlinx.android.synthetic.main.activity_card.*
import com.example.recordandov4.R.drawable.*

class MainGame : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var reset: Button
    private lateinit var cards: List<Cards>
    private var indexOfSingleSelectedCard: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val images = mutableListOf( ic_b, ic_c, ic_d, ic_e, ic_f, ic_g, ic_g, ic_tick)

        images.addAll(images)
        images.shuffle()


        buttons = listOf(imageButton, imageButton2, imageButton3, imageButton4, imageButton5,
            imageButton6, imageButton7, imageButton8, imageButton9, imageButton10, imageButton11,
            imageButton12, imageButton13, imageButton15, imageButton17, imageButton18)

        reset = findViewById(R.id.button2)



        reset.setOnClickListener{
            resetar()
        }

        cards = buttons.indices.map { index ->
            Cards(images[index])
        }


        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                updateModels(index)
                updateViews()

            }
        }
    }

    private fun resetar() {
        val intent = intent
        finish()
        startActivity(intent)
    }


    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if(card.isMatched){
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else ic_s)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        if (card.isFaceUp) {
            Toast.makeText(this, "movimento inválido", Toast.LENGTH_SHORT).show()
            return
        }
        if (indexOfSingleSelectedCard == null){
            restoreCards()
            indexOfSingleSelectedCard = position
        }else{
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for(card in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if(cards[position1].identifier== cards[position2].identifier){
            Toast.makeText(this,"Você fez um par!!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true
        }
    }
}