package com.example.testwork.ui.user_adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testwork.databinding.UserItemBinding
import com.example.testwork.domain.UserItem

class UserItemViewHolder(val binding: UserItemBinding) :
RecyclerView.ViewHolder(binding.root) {

    fun bindItems(item: UserItem) {
        binding.firstNameOnListTV.text = item.first_name
        binding.lastNameOnListTV.text = item.last_name
    }

    companion object {
        fun from(parent: ViewGroup): UserItemViewHolder {
            val binding = UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return UserItemViewHolder(binding)
        }
    }

}
