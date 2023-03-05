package com.jaysinghtalreja.githubdemo.data.sourceofdata

import androidx.lifecycle.LiveData
import com.jaysinghtalreja.githubdemo.data.sourceofdata.model.RepoData
import com.jaysinghtalreja.githubdemo.data.sourceofdata.model.ResponseGithub
import com.jaysinghtalreja.githubdemo.utils.async.ThreadManager
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val threadManager: ThreadManager,
    private val githubRepositoryDataSourceLocal: GithubRepositoryDataSourceLocal
) {

    /**
     * Get Trending Github Repositories
     */
    suspend fun getGithubTrendingRepository() : Pair<ResponseGithub?, Int> {
        val param  = HashMap<String, String>()
        param[PARAM_QUERY] = "created:2023-03-01"
        param[PARAM_SORT] = "stars"
        param[PARAM_ORDER] = "desc"

        return withContext(threadManager.io) {
            val response = githubApi.getGithubTrending(param)
            response.body() to response.code()
        }

    }

    suspend fun saveGithubRepositoryLocal(repoData: List<RepoData>) {
            val repos = repoData!!.map {
            com.jaysinghtalreja.githubdemo.data.sourceofdata.entity.RepoData(
                id = it.id,
                allowForking = it.allowForking,
                stargazersCount = it.stargazersCount,
                isTemplate = it.isTemplate,
                pushedAt = it.pushedAt,
                subscriptionUrl = it.subscriptionUrl,
                language = it.language,
                branchesUrl = it.branchesUrl,
                issueCommentUrl = it.issueCommentUrl,
                labelsUrl = it.labelsUrl,
                score = it.score,
                subscribersUrl = it.subscribersUrl,
                releasesUrl = it.releasesUrl,
                svnUrl = it.svnUrl,
                hasDiscussions = it.hasDiscussions,
                forks = it.forks,
                archiveUrl = it.archiveUrl,
                gitRefsUrl = it.gitRefsUrl,
                forksUrl = it.forksUrl,
                visibility = it.visibility,
                statusesUrl = it.statusesUrl,
                sshUrl = it.sshUrl,
                fullName = it.fullName,
                size = it.size,
                languagesUrl = it.languagesUrl,
                htmlUrl = it.htmlUrl,
                collaboratorsUrl = it.collaboratorsUrl,
                cloneUrl = it.cloneUrl,
                name = it.name,
                pullsUrl = it.pullsUrl,
                defaultBranch = it.defaultBranch,
                hooksUrl = it.hooksUrl,
                treesUrl = it.treesUrl,
                tagsUrl = it.tagsUrl,
                jsonMemberPrivate = it.jsonMemberPrivate,
                contributorsUrl = it.contributorsUrl,
                hasDownloads = it.hasDownloads,
                notificationsUrl=it.notificationsUrl,
                openIssuesCount=it.openIssuesCount,
                description=it.description,
                createdAt=it.createdAt,
                watchers=it.watchers,
                keysUrl=it.keysUrl,
                deploymentsUrl=it.deploymentsUrl,
                hasProjects=it.hasProjects,
                archived=it.archived,
                hasWiki=it.hasWiki,
                updatedAt=it.updatedAt,
                commentsUrl=it.commentsUrl,
                stargazersUrl=it.stargazersUrl,
                disabled=it.disabled,
                gitUrl=it.gitUrl,
                hasPages=it.hasPages,
                commitsUrl=it.commitsUrl,
                compareUrl=it.compareUrl,
                gitCommitsUrl=it.gitCommitsUrl,
                blobsUrl=it.blobsUrl,
                gitTagsUrl=it.gitTagsUrl,
                mergesUrl=it.mergesUrl,
                downloadsUrl=it.downloadsUrl,
                hasIssues=it.hasIssues,
                webCommitSignoffRequired=it.webCommitSignoffRequired,
                url =it.url,
                contentsUrl = it.contentsUrl,
                mirrorUrl = it.mirrorUrl,
                milestonesUrl = it.milestonesUrl,
                teamsUrl=it.teamsUrl,
                fork=it.fork,
                issuesUrl=it.issuesUrl,
                eventsUrl=it.eventsUrl,
                issueEventsUrl=it.issueEventsUrl,
                assigneesUrl=it.assigneesUrl,
                openIssues=it.openIssues,
                watchersCount=it.watchersCount,
                nodeId=it.nodeId,
                homepage=it.homepage,
                forksCount=it.forksCount
            )
        }
        githubRepositoryDataSourceLocal.insertRepositories(repos)
    }


    fun getRepoDataLiveData(): LiveData<List<com.jaysinghtalreja.githubdemo.data.sourceofdata.entity.RepoData>> =
        githubRepositoryDataSourceLocal.getRepositoriesLiveData()


    companion object {
        private const val PARAM_QUERY = "q"
        private const val PARAM_SORT = "sort"
        private const val PARAM_ORDER = "order"

    }
}