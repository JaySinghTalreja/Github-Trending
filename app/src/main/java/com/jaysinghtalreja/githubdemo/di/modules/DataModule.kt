package com.jaysinghtalreja.githubdemo.di.modules

import android.app.Application
import com.jaysinghtalreja.githubdemo.data.GithubLocalDatabase
import com.jaysinghtalreja.githubdemo.data.sourceofdata.dao.RepositoryListDao
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Module
    companion object {
        /**
         * [RepositoryListDao]
         */
        @JvmStatic
        @Provides
        fun provideAssetDao(application: Application): RepositoryListDao =
            GithubLocalDatabase.getInstance(application).repositoryListDao()
    }

}