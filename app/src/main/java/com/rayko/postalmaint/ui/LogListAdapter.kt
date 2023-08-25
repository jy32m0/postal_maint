package com.rayko.postalmaint.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rayko.postalmaint.data.CallEntity
import com.rayko.postalmaint.databinding.FragmentDetailBinding
import kotlin.math.floor
import java.text.SimpleDateFormat
import java.util.Date

class LogListAdapter(
    private val onItemClicked: (CallEntity) -> Unit
) : ListAdapter<CallEntity, LogListAdapter.LogListViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CallEntity>() {
            override fun areItemsTheSame(oldItem: CallEntity, newItem: CallEntity): Boolean {
                return oldItem.roomId == newItem.roomId
            }

            override fun areContentsTheSame(oldItem: CallEntity, newItem: CallEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogListViewHolder {
        val viewHolder = LogListViewHolder(
            FragmentDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: LogListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    @SuppressLint("SimpleDateFormat")
    class LogListViewHolder(
        private var binding: FragmentDetailBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(callEntity: CallEntity) {
            binding.equipName.text = callEntity.equipType

            binding.equipNum.text = callEntity.equipNum.toString()

            binding.callTime.text =
                SimpleDateFormat("HH:mm, MMM dd").format(Date(callEntity.callTime))

            binding.clearTime.text =
                if (callEntity.callTime == callEntity.clearTime) {
                    "- - -"
                } else {
                "Cleared at " + formattedTime(callEntity.clearTime)}

            binding.downTime.text =
                if (callEntity.callTime == callEntity.clearTime) {
                    "Downed so far " + timeDiff(callEntity.clearTime, callEntity.callTime)
                } else {
                    "Total downed for " + formattedTime(callEntity.downTime)
                }

            binding.callReason.text =
                "Called for: " + callEntity.callReason

            binding.clearSolution.text =
                "Remedy: " + callEntity.clearSolution
        }

        private fun formattedTime(milliTime: Long): String {
            return SimpleDateFormat("HH:mm").format(Date(milliTime))
        }
        private fun timeDiff(endTime: Long, startTime: Long): String {
            val diff: Double = endTime.minus(startTime) / 1000.0
            val hours = floor(diff / 3600).toInt()
            val mins = floor((diff % 3660) / 60).toInt()
            return "$hours hour(s) and $mins minutes"
        }
    }
}