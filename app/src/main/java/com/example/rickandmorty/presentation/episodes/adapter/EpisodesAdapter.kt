package com.example.rickandmorty.presentation.episodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.EpisodesListItemBinding
import com.example.rickandmorty.domain.entity.Episode

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>() {

    private val episodes: MutableList<Episode> = mutableListOf()

    class EpisodeViewHolder(private val viewBinding: EpisodesListItemBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(episode: Episode){
            viewBinding.episodesName.text = episode.name
            val airDate = viewBinding.root.context.getString(R.string.episodes_air_date, episode.airDate)
            viewBinding.episodesAirDate.text = airDate
        }
    }

    fun setEpisodes(newList: List<Episode>){
        episodes.clear()
        episodes.addAll(newList)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val viewBinding = EpisodesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }
}