package com.jaysinghtalreja.githubdemo.data.sourceofdata.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.jaysinghtalreja.githubdemo.data.sourceofdata.entity.RepoData


@Dao
interface RepositoryListDao {

    @Query("SELECT * from repositories")
    fun getAllRepositories() : LiveData<List<RepoData>>
    @Insert
    suspend fun insert(repoData : List<RepoData>)

    @Transaction
    suspend fun insertAll(repoData: List<RepoData>) {
        nuke()
        insert(repoData)
    }

    @Query("DELETE from repositories")
    suspend fun nuke()
}