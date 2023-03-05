package com.jaysinghtalreja.githubdemo.data.sourceofdata

import androidx.lifecycle.LiveData
import com.jaysinghtalreja.githubdemo.data.sourceofdata.dao.RepositoryListDao
import com.jaysinghtalreja.githubdemo.data.sourceofdata.entity.RepoData
import javax.inject.Inject

class GithubRepositoryDataSourceLocal @Inject constructor(val repositoryListDao: RepositoryListDao) {

    /**
     * Get Repositories Live Data
     */
    fun getRepositoriesLiveData() : LiveData<List<RepoData>> {
        return repositoryListDao.getAllRepositories()
    }

    /**
     * Insert into Repositories
     */
    open suspend fun insertRepositories(repoData : List<RepoData>) {
        repositoryListDao.insertAll(repoData)
    }
}