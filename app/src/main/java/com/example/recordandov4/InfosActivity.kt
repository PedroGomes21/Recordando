package com.example.recordandov4

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recordandov4.entities.Infos
import com.google.android.gms.auth.api.signin.GoogleSignIn
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class InfosActivity : AppCompatActivity() {
    private lateinit var nomeI: EditText
    private lateinit var idadeI: EditText
    private lateinit var telefoneI: EditText
    private lateinit var telefoneI2: EditText
    private lateinit var Iemail: EditText
    private lateinit var saveInf: ImageButton
    private lateinit var infoList: ArrayList<Infos>
    private lateinit var realm: Realm




    override fun onCreate(savedInstanceState: Bundle?) {
        Realm.init(this)
        realm = Realm.getDefaultInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infos)


        nomeI = findViewById(R.id.nomeI)
        idadeI = findViewById(R.id.idadeI)
        telefoneI = findViewById(R.id.telI)
        telefoneI2 = findViewById(R.id.tellI)
        Iemail = findViewById(R.id.Iemail)
        saveInf = findViewById(R.id.saveInf)

       updateInfos()



        saveInf.setOnClickListener {
            saveInfos()


        }

    }




    private fun saveInfos() {


        var info = Infos()
        info.nome = nomeI.text.toString()
        info.idade = idadeI.text.toString()
        info.tell = telefoneI.text.toString()
        info.tell2 = telefoneI2.text.toString()
        info.email = Iemail.text.toString()

        try {

            if (info.nome.isNullOrEmpty()) {
                Toast.makeText(this, "Insira um nome!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, InfosActivity::class.java))
                finish()

            } else if (info.idade.isNullOrEmpty()) {
                Toast.makeText(this, "Insira a idade", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, InfosActivity::class.java))
                finish()

            } else if (info.tell.isNullOrEmpty()) {
                Toast.makeText(this, "Insira seu telefone", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, InfosActivity::class.java))
                finish()


            } else if (info.tell2.isNullOrEmpty()) {
                Toast.makeText(
                    this,
                    "Insira seu telefone de emerg??ncia",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, InfosActivity::class.java))
                finish()


            } else if (info.email.isNullOrEmpty()) {
                Toast.makeText(this, "Insira seu email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, InfosActivity::class.java))
                finish()


            } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(info.email).matches()) {
                realm.beginTransaction()

                var info = Infos()
                info.nome = nomeI.text.toString()
                info.idade = idadeI.text.toString()
                info.tell = telefoneI.text.toString()
                info.tell2 = telefoneI2.text.toString()
                info.email = Iemail.text.toString()

                realm.insertOrUpdate(info)
                val nomes = realm.where(Infos::class.java).findAll().last()

                nomeI.hint = "Seu Nome:  " + nomes?.nome
                idadeI.hint = "Sua Idade:" + nomes?.idade
                telefoneI.hint = "Seu telefone:  " + nomes?.tell
                telefoneI2.hint = "Telefone de Emerg??ncia:  " + nomes?.tell2
                Iemail.hint = "Seu Email:  " + nomes?.email
                realm.commitTransaction()
                updateInfos()




                Toast.makeText(this, "salvo", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()


            } else {
                Toast.makeText(this, "email invalido", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, InfosActivity::class.java))
                finish()


            }
        }catch (e: Exception){

        }



    }
    private fun updateInfos() {

        try {
            val nomes = realm.where(Infos::class.java).findAll().last()
            if (nomes?.nome.isNullOrEmpty()) {


            } else {
                nomeI.hint = "Seu Nome:  " + nomes?.nome
                idadeI.hint = "Sua Idade:  " + nomes?.idade
                telefoneI.hint = "Seu telefone:  " + nomes?.tell
                telefoneI2.hint = "Telefone de Emerg??ncia:  " + nomes?.tell2
                Iemail.hint = "Seu Email:  " + nomes?.email


            }
        }catch (e: Exception){

            val acct = GoogleSignIn.getLastSignedInAccount(this)
            if (acct != null) {

                nomeI.hint = "Seu Nome:  " + acct.givenName
                idadeI.hint = "Sua Idade:  "
                telefoneI.hint = "Seu telefone:  "
                telefoneI2.hint = "Telefone de Emerg??ncia:  "
                Iemail.hint = "Seu Email:  " + acct.email


            }
        }

    }


    private fun voltar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
