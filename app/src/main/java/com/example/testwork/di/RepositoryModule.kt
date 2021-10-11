package com.example.testwork.di

import com.example.testwork.data.Repository
import com.example.testwork.domain.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repository: Repository
    ): IRepository

}