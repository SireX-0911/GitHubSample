package com.sirex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sirex.data.local.dao.RepositoryDao
import com.sirex.data.local.dao.UserDao
import com.sirex.domain.entites.DbRepository
import com.sirex.domain.entites.DbUser

@Database(entities = [DbUser::class, DbRepository::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        const val DATABASE_NAME = "github_sample_app_db"
    }
}
