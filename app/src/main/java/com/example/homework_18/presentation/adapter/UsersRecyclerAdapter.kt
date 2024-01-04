package com.example.homework_18.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_18.databinding.UsersCardBinding
import com.example.homework_18.domain.response.UsersResponse

class UsersRecyclerAdapter :
    ListAdapter<UsersResponse, UsersRecyclerAdapter.UsersCardViewHolder>(UserDiffUtil) {

    companion object {
        val UserDiffUtil = object : DiffUtil.ItemCallback<UsersResponse>() {
            override fun areItemsTheSame(oldItem: UsersResponse, newItem: UsersResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UsersResponse,
                newItem: UsersResponse
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    lateinit var onClick: (Int) -> Unit

    inner class UsersCardViewHolder(private val binding: UsersCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                val user = currentList[adapterPosition]
                tvEmail.text = user.email
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                Glide
                    .with(binding.root)
                    .load(user.avatar)
                    .into(ivUserImage)
            }
        }

        fun listener(){
            binding.root.setOnClickListener {
                val user = currentList[adapterPosition]
                onClick(user.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersCardViewHolder {
        return UsersCardViewHolder(
            UsersCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersCardViewHolder, position: Int) {
        with(holder){
            bind()
            listener()
        }
    }

}