package com.example.testwork.data

import android.util.Log
import com.example.testwork.data.local.AppDatabase
import com.example.testwork.data.remote.RetrofitClient
import com.example.testwork.domain.IRepository
import com.example.testwork.domain.UserItem
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofit: RetrofitClient,
    private val db: AppDatabase
//        private val signalRClient: SignalRClient
) : IRepository {

    override fun getUsers(): Single<List<UserItem>> {

        var cacheIsDirty = false

        if ((0..1).random() ==1)
            cacheIsDirty = true

        return if (cacheIsDirty)
           getFromRemote()
        else
            getFromLocal()
//       val data = getFromRemote()
//        if (data != null )
//        return data
    }

    override fun getUserFromRemote(): Single<List<UserItem>> = getFromRemote()

    override fun getUserFromLocal(): Single<List<UserItem>>  = getFromLocal()

    override fun getPage(): Int {
        TODO("Not yet implemented")
    }

    override fun getPerPage(): Int {
        TODO("Not yet implemented")
    }

    override fun getTotalUsers(): Int {
        TODO("Not yet implemented")
    }

    override fun getTotalPages(): Int {
        TODO("Not yet implemented")
    }

    private fun getFromRemote(): Single<List<UserItem>> {
        Log.d("debug", "Get from remote")
        return retrofit.api.getUsers()
            .map {
                it.data
            }
            .doOnSuccess {
                storeLocally(it)
            }

    }

    private fun storeLocally(users: List<UserItem>) {
        db.usersDao().insertAll(users)
            .subscribe()
    }

    private fun getFromLocal(): Single<List<UserItem>> {
        Log.d("debug", "Get from local")
        return db.usersDao().getAllUsers()

    }

}