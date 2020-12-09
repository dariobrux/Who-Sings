package com.dariobrux.whosings.ui.score

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobrux.whosings.data.local.score.HeaderData
import com.dariobrux.whosings.data.local.score.ScoreData
import com.dariobrux.whosings.data.local.score.ScoreInfoData
import com.dariobrux.whosings.databinding.ItemScoreDataBinding
import com.dariobrux.whosings.databinding.ItemScoreHeaderBinding


/**
 *
 * Created by Dario Bruzzese on 9/12/2020.
 *
 * This adapter displays the scores.
 *
 */
class ScoreAdapter(private val context: Context, private val items: List<ScoreData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (items[position] is HeaderData) {
            return HEADER
        } else if (items[position] is ScoreInfoData) {
            return SCORE
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                HeaderDataViewHolder(ItemScoreHeaderBinding.inflate(LayoutInflater.from(context), parent, false))
            }
            else -> {
                ScoreDataViewHolder(ItemScoreDataBinding.inflate(LayoutInflater.from(context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder.itemViewType) {
            HEADER -> {
                (holder as HeaderDataViewHolder).bind(item as HeaderData)
            }
            else -> {
                (holder as ScoreDataViewHolder).bind(item as ScoreInfoData)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ScoreDataViewHolder(private val binding: ItemScoreDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ScoreInfoData) = with(binding) {
            txtNumber.text = item.left
            txtName.text = item.center
            txtScore.text = item.right
        }
    }

    inner class HeaderDataViewHolder(private val binding: ItemScoreHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HeaderData) = with(binding) {
            txtNumber.text = item.left
            txtName.text = item.center
            txtScore.text = item.right
        }
    }

    companion object {
        const val HEADER = 0
        const val SCORE = 1
    }
}