package com.example.testwork.ui.user_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testwork.domain.UserItem

class UsersAdapter(
    private var users: List<UserItem>,
    private val userClick: ((user: UserItem) -> Unit)?,

    ) : RecyclerView.Adapter<UserItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        if (holder is UserItemViewHolder)
            holder.bindItems(users[position])

        if (holder is UserItemViewHolder)
            holder.itemView.setOnClickListener {
                userClick?.invoke(users[position])
            }

    }

    fun update(data: List<UserItem>) {
        users = data
        notifyDataSetChanged()
    }
}