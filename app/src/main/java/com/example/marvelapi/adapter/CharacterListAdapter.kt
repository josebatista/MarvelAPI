package com.example.marvelapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.Character
import com.example.marvelapi.R
import com.example.marvelapi.extensions.load
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterListAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<CharacterListAdapter.VH>() {

    private val characters = ArrayList<Character>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)

        val vh = VH(v)
        v.setOnClickListener {
            val position = vh.adapterPosition
            val character = characters[position]
            onItemClick(character.id)
        }

        return vh
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val character = characters[position]
        holder.run {
            characterName?.text = character.name
            characterImage?.load(character.thumbnail.path)
        }
    }

    fun addCharacters(list: List<Character>) {
        characters.addAll(list)
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val characterImage: ImageView? = view.iv_character
        val characterName: TextView? = view.tv_character_name
    }
}