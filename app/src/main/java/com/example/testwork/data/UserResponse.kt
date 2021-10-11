package com.example.testwork.data

import com.example.testwork.domain.UserItem

data class UserResponse (
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<UserItem>
)