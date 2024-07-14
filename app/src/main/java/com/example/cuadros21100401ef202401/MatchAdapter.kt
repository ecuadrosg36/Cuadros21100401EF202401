package com.example.cuadros21100401ef202401

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuadros21100401ef202401.databinding.ItemMatchBinding
import com.squareup.picasso.Picasso

class MatchAdapter(private val matchList: List<Match>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matchList[position]
        holder.bind(match)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    class MatchViewHolder(private val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match) {
            binding.localTeamName.text = match.localTeam
            binding.visitorTeamName.text = match.visitorTeam
            binding.localOdds.text = match.localOdds.toString()
            binding.drawOdds.text = match.drawOdds.toString()
            binding.visitorOdds.text = match.visitorOdds.toString()
            Picasso.get().load(match.localTeamLogo).into(binding.localTeamLogo)
            Picasso.get().load(match.visitorTeamLogo).into(binding.visitorTeamLogo)
        }
    }
}
