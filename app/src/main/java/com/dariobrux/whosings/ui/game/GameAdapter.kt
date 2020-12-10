package com.dariobrux.whosings.ui.game

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.whosings.data.local.game.Artist
import com.dariobrux.whosings.databinding.ItemChoiceBinding


/**
 *
 * Created by Dario Bruzzese on 10/12/2020.
 *
 * This adapter displays the artist choice.
 *
 */
class GameAdapter(private val context: Context, val items: MutableList<Artist>, private val listener: OnItemSelectedListener?) : RecyclerView.Adapter<GameAdapter.ChoiceViewHolder>() {

    interface OnItemSelectedListener {
        /**
         * Invoked when an artist has been selected.
         */
        fun onArtistSelected(item: Artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceViewHolder {
        return ChoiceViewHolder(ItemChoiceBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ChoiceViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ChoiceViewHolder(private val binding: ItemChoiceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) = with(binding) {
            txt.text = item.name
            card.setOnClickListener {
                listener?.onArtistSelected(item)
            }
        }
    }
}