package com.example.cuadros21100401ef202401

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTeamName: EditText
    private lateinit var editTextTeamURL: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonRegisterMatches: Button
    private lateinit var buttonListMatches: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTeamName = findViewById(R.id.editTextTeamName)
        editTextTeamURL = findViewById(R.id.editTextTeamURL)
        buttonSave = findViewById(R.id.buttonSave)
        buttonRegisterMatches = findViewById(R.id.buttonRegisterMatches)
        buttonListMatches = findViewById(R.id.buttonListMatches)

        val db = Firebase.firestore

        buttonSave.setOnClickListener {
            val teamName = editTextTeamName.text.toString()
            val teamURL = editTextTeamURL.text.toString()

            if (teamName.isNotEmpty() && teamURL.isNotEmpty()) {
                val team = hashMapOf(
                    "name" to teamName,
                    "url" to teamURL
                )

                db.collection("teams")
                    .add(team)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Equipo registrado con éxito", Toast.LENGTH_SHORT).show()
                        editTextTeamName.text.clear()
                        editTextTeamURL.text.clear()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al registrar el equipo", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        buttonRegisterMatches.setOnClickListener {
            val intent = Intent(this, RegisterMatchActivity::class.java)
            startActivity(intent)
        }

        buttonListMatches.setOnClickListener {
            val intent = Intent(this, ListMatchesActivity::class.java)
            startActivity(intent)
        }

    }
}
