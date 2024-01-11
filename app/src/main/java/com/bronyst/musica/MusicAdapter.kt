package com.bronyst.musica

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MusicAdapter(val context: Activity, val dataList: List<Data>) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val musicTitle: TextView = itemView.findViewById(R.id.musicTitle)
        val musicImage: ImageView = itemView.findViewById(R.id.musicImage)
        val playMusic: ImageButton = itemView.findViewById(R.id.playMusic)
        val pauseMusic: ImageButton = itemView.findViewById(R.id.pauseMusic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(LayoutInflater.from(context).inflate(R.layout.music_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
//        populate data into the view
        val music: Data = dataList[position]
        holder.musicTitle.text = music.title
        Picasso.get().load(music.album.cover).into(holder.musicImage);
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, music.preview.toUri())


        holder.pauseMusic.setOnClickListener {
           mediaPlayer.stop()
        }
        holder.playMusic.setOnClickListener {
           mediaPlayer.start()
        }
    }
}