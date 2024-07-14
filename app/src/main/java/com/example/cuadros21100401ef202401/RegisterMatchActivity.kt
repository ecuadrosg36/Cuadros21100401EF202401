package com.example.cuadros21100401ef202401

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cuadros21100401ef202401.databinding.ActivityRegisterMatchBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterMatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterMatchBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            val localTeam = binding.spinnerLocalTeam.selectedItem.toString()
            val visitorTeam = binding.spinnerVisitorTeam.selectedItem.toString()
            val localOdds = binding.editTextLocalOdds.text.toString().toDoubleOrNull()
            val drawOdds = binding.editTextDrawOdds.text.toString().toDoubleOrNull()
            val visitorOdds = binding.editTextVisitorOdds.text.toString().toDoubleOrNull()

            if (localTeam.isNotEmpty() && visitorTeam.isNotEmpty() && localOdds != null && drawOdds != null && visitorOdds != null) {
                val match = Match(
                    localTeam = localTeam,
                    visitorTeam = visitorTeam,
                    localOdds = localOdds,
                    drawOdds = drawOdds,
                    visitorOdds = visitorOdds,
                    localTeamLogo = "", // Aquí puedes agregar la lógica para obtener el logo
                    visitorTeamLogo = "" // Aquí puedes agregar la lógica para obtener el logo
                )

                db.collection("matches")
                    .add(match)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Enfrentamiento registrado con éxito", Toast.LENGTH_SHORT).show()
                        binding.spinnerLocalTeam.setSelection(0)
                        binding.spinnerVisitorTeam.setSelection(0)
                        binding.editTextLocalOdds.text.clear()
                        binding.editTextDrawOdds.text.clear()
                        binding.editTextVisitorOdds.text.clear()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al registrar el enfrentamiento", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
