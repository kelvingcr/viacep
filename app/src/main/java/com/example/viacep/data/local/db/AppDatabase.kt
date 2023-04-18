package com.example.viacep.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.viacep.data.local.dao.AddressDao
import com.example.viacep.data.local.entity.AddressEntity

/**
 * RoomDatabase
 * Ela fornece uma camada de abstração sobre a API SQLite do Android
 * e simplifica o processo de criação e gerenciamento de bancos de dados SQLite em um aplicativo Android.
 */

@Database(entities = [AddressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun addressDao() : AddressDao

}