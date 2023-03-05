package com.jaysinghtalreja.githubdemo.data.sourceofdata

import com.jaysinghtalreja.githubdemo.data.sourceofdata.model.ResponseGithub
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubApi {


    /**
     * Get Github Trending
     */
    @GET("/search/repositories")
    @Throws(Exception::class)
    suspend fun getGithubTrending(@QueryMap payload: Map<String, String>): Response<ResponseGithub>

    companion object {
        /**
         * Factory function for [GithubApi]
         */
        fun create(retroFit: Retrofit): GithubApi = retroFit.create(
            GithubApi::class.java
        )
    }
}