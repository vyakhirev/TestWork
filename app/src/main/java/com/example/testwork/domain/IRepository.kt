package com.example.testwork.domain

import io.reactivex.Single

interface IRepository {
    fun getUsers(): Single<List<UserItem>>
    fun getUserFromRemote(): Single<List<UserItem>>
    fun getUserFromLocal(): Single<List<UserItem>>
    fun getPage(): Int
    fun getPerPage(): Int
    fun getTotalUsers():Int
    fun getTotalPages(): Int
}