package com.example.cuadros21100401ef202401

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuadros21100401ef202401.databinding.ActivityListMatchesBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListMatchesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListMatchesBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        binding.buttonRegisterTeams.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRegisterMatch.setOnClickListener {
            val intent = Intent(this, RegisterMatchActivity::class.java)
            startActivity(intent)
        }

        fetchMatches()
    }

    private fun fetchMatches() {
        db.collection("matches")
            .get()
            .addOnSuccessListener { result ->
                val matchList = arrayListOf<Match>()
                for (document in result) {
                    val match = document.toObject(Match::class.java)
                    matchList.add(match)
                }
                binding.recyclerView.adapter = MatchAdapter(matchList)
            }
            .addOnFailureListener { exception ->
                // Manejar errores
            }
    }
}
