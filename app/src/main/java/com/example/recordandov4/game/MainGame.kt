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
    private lateinit var reset: ImageButton
    private lateinit var cards: List<Cards>
    private var indexOfSingleSelectedCard: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val images = mutableListOf( card1, card2, card3, card4, card5, card6, card7, card8)

        images.addAll(images)
        images.shuffle()


        buttons = listOf(imageButton, imageButton2, imageButton3, imageButton4, imageButton5,
            imageButton6, imageButton7, imageButton8, imageButton9, imageButton10, imageButton11,
            imageButton12, imageButton13, imageButton15, imageButton17, imageButton18)

        reset = findViewById(R.id.imageButtonR)



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
                button.setImageResource(card_2)
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else card_2)
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