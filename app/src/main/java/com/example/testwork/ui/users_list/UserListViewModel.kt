package com.example.testwork.ui.users_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testwork.domain.IRepository
import com.example.testwork.domain.UserItem
import com.example.testwork.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.annotation.concurrent.ThreadSafe
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {
    var disposable = CompositeDisposable()

    private val _users = MutableLiveData<List<UserItem>>()
    val users: LiveData<List<UserItem>> = _users

    private val _isViewLoading = MutableLiveData<Event<Boolean>>()
    val isViewLoading: LiveData<Event<Boolean>> = _isViewLoading

    private val _onMessageError = MutableLiveData<Event<Boolean>>()
    val onMessageError: LiveData<Event<Boolean>> = _onMessageError

    fun getUsers() {
        disposable.add(
            repository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _isViewLoading.value = Event(true)
                        Thread.sleep(1000)
                        _users.value = it
                        _isViewLoading.value = Event(false)
                        _onMessageError.value = Event(it.isEmpty())
                    }, {
                        _isViewLoading.value = Event(false)
                        _onMessageError.value = Event ( true)
                    }
                )
        )
    }

    fun getFromRemote() {
        _users.value = listOf()
        disposable.add(
            repository.getUserFromRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _isViewLoading.value = Event(true)
                        Thread.sleep(2000)
                        _users.value = it
                        _onMessageError.value = Event(it.isEmpty())
                        _isViewLoading.value = Event(false)
                    }, {
                        _isViewLoading.value = Event(false)
                        _onMessageError.value = Event ( true)
                    }
                )
        )
    }

    fun getFromLocal() {
        _users.value = listOf()
        disposable.add(
            repository.getUserFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _isViewLoading.value = Event(true)
                        Thread.sleep(1000)
                        _users.value = it
                        _isViewLoading.value = Event(false)
                        _onMessageError.value = Event(it.isEmpty())
                    }, {
                        _isViewLoading.value = Event(false)
                        _onMessageError.value = Event ( true)
                    }
                )
        )
    }

    override fun onCleared() {
        disposable.clear()
    }
}