package com.example.recordandov4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.recordandov4.entities.Infos
import com.example.recordandov4.game.MainGame
import com.google.android.gms.auth.api.signin.GoogleSignIn
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

private const val TAG = "MainActivity"
private lateinit var realm: Realm



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            nomeED.text = acct.givenName

        }


        game.setOnClickListener{
            jogo()
        }

        notas.setOnClickListener{
            notas()
        }
        info.setOnClickListener{
            infos()
        }
        emer.setOnClickListener{
            abrirDiscador()
        }
        sair.setOnClickListener{
            signOut()

        }
    }

    private fun signOut() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


    private fun abrirDiscador() {
        try {
            val tell = realm.where(Infos::class.java).findAll().last()

            if (tell!!.tell2 == null) {





            }else {


                realm.beginTransaction()

                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + tell?.tell2)
                realm.commitTransaction()

                startActivity(intent)
            }

        }catch (e: Exception){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "192")
            startActivity(intent)

        }
    }

    private fun jogo() {
        val intent = Intent(this, MainGame::class.java)
        startActivity(intent)
    }

    private fun notas() {
        val intent = Intent(this,MainActivityNotes::class.java)
        startActivity(intent)
    }
    private fun infos() {
        val intent = Intent(this,InfosActivity::class.java)
        startActivity(intent)
    }

}

