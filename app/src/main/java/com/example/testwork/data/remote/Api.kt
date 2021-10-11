package com.example.testwork.data.remote

import com.example.testwork.domain.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("/api/users")
    fun getUsers(): Single<UserResponse>
}