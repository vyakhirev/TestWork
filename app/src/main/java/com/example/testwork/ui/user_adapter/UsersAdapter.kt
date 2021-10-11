package com.example.testwork.ui.user_adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testwork.domain.UserItem

class UsersAdapter(
    private var users: List<UserItem>,
    private val userClick: ((user: UserItem) -> Unit)?,

    ) : RecyclerView.Adapter<UserItemViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

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