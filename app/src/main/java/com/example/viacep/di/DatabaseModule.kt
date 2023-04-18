package com.example.viacep.di

import android.content.Context
import androidx.room.Room
import com.example.viacep.data.local.dao.AddressDao
import com.example.viacep.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Cria uma instancia de AppDatabase para todo o aplicativo
     */
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase
    = Room.databaseBuilder(context, AppDatabase::class.java, "address_database").build()

    @Provides
    fun providesAddressDao(database: AppDatabase) : AddressDao = database.addressDao()
}