package com.sirex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sirex.domain.entites.DbRepository
import com.sirex.domain.entites.DbUser

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repos: List<DbRepository>)

    @Query("SELECT * FROM DbRepository")
    suspend fun queryRepositories(): List<DbRepository>
}
