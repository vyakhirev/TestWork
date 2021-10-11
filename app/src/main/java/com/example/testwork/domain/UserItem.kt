package com.example.testwork.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class UserItem(
    @field:PrimaryKey
    var id : Int,
    var email: String,
    var first_name: String,
    var last_name: String,
    var avatar: String
):Serializable