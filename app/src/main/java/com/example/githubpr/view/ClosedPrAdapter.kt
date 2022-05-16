package com.example.githubpr.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubpr.databinding.ItemClosedPrBinding
import com.example.githubpr.model.ClosedPR

class ClosedPrAdapter(private var list: List<ClosedPR>, private val context: Context): RecyclerView.Adapter<ClosedPrAdapter.ClosedPrViewHolder>() {

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
        holder.setValues(list[position], context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ClosedPrViewHolder(private val binding: ItemClosedPrBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setValues(closedPr: ClosedPR, context: Context) {
            binding.apply {
                titleTv.text = closedPr.title
                createdDateTv.text = closedPr.createdAt
                closedDateTv.text = closedPr.closedAt
                nameTv.text = closedPr.user.login
                Glide.with(context)
                    .load(
                        closedPr.user.avatarUrl
                    )
                    .into(binding.profileIv)
            }
        }
    }
}