package com.example.githubpr.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubpr.databinding.ItemClosedPrBinding
import com.example.githubpr.model.ClosedPR

class ClosedPrAdapter(private var list: List<ClosedPR>): RecyclerView.Adapter<ClosedPrAdapter.ClosedPrViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(closedPrList: List<ClosedPR>) {
        list = closedPrList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosedPrViewHolder {
        return ClosedPrViewHolder(ItemClosedPrBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ClosedPrViewHolder, position: Int) {
        holder.setValues(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ClosedPrViewHolder(private val binding: ItemClosedPrBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setValues(closedPr: ClosedPR) {
            binding.apply {
                titleTv.text = closedPr.title
                createdDateTv.text = closedPr.createdAt
                closedDateTv.text = closedPr.closedAt
            }
        }
    }
}