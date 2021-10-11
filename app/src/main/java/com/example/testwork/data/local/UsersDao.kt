package com.example.testwork.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testwork.domain.UserItem
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserItem>): Completable

    @Query("SELECT * FROM users")
    fun getAllUsers(): Single<List<UserItem>>

    @Query("SELECT * FROM users WHERE id=:Id")
    fun getUserById(Id: Int): Single<UserItem>

}