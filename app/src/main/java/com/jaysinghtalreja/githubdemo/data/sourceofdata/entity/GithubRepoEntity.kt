package com.jaysinghtalreja.githubdemo.data.sourceofdata.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = RepoData.TABLE_NAME,
    indices = [Index("id", unique = true)]
)
data class RepoData(

    @PrimaryKey
    @ColumnInfo(name="id")
    val id: Int? = null,

    @ColumnInfo(name="allow_forking")
    val allowForking: Boolean? = null,

    @ColumnInfo(name="stargazers_count")
    val stargazersCount: Int? = null,

    @ColumnInfo(name="is_template")
    val isTemplate: Boolean? = null,

    @ColumnInfo(name="pushed_at")
    val pushedAt: String? = null,

    @ColumnInfo(name="subscription_url")
    val subscriptionUrl: String? = null,

    @ColumnInfo(name="language")
    val language: String? = null,

    @ColumnInfo(name="branches_url")
    val branchesUrl: String? = null,

    @ColumnInfo(name="issue_comment_url")
    val issueCommentUrl: String? = null,

    @ColumnInfo(name="labels_url")
    val labelsUrl: String? = null,

    @ColumnInfo(name="score")
    val score: Double? = null,

    @ColumnInfo(name="subscribers_url")
    val subscribersUrl: String? = null,

    @ColumnInfo(name="releases_url")
    val releasesUrl: String? = null,

    @ColumnInfo(name="svn_url")
    val svnUrl: String? = null,

    @ColumnInfo(name="has_discussions")
    val hasDiscussions: Boolean? = null,

    @ColumnInfo(name="forks")
    val forks: Int? = null,

    @ColumnInfo(name="archive_url")
    val archiveUrl: String? = null,

    @ColumnInfo(name="git_refs_url")
    val gitRefsUrl: String? = null,

    @ColumnInfo(name="forks_url")
    val forksUrl: String? = null,

    @ColumnInfo(name="visibility")
    val visibility: String? = null,

    @ColumnInfo(name="statuses_url")
    val statusesUrl: String? = null,

    @ColumnInfo(name="ssh_url")
    val sshUrl: String? = null,

    @ColumnInfo(name="full_name")
    val fullName: String? = null,

    @ColumnInfo(name="size")
    val size: Int? = null,

    @ColumnInfo(name="languages_url")
    val languagesUrl: String? = null,

    @ColumnInfo(name="html_url")
    val htmlUrl: String? = null,

    @ColumnInfo(name="collaborators_url")
    val collaboratorsUrl: String? = null,

    @ColumnInfo(name="clone_url")
    val cloneUrl: String? = null,

    @ColumnInfo(name="name")
    val name: String? = null,

    @ColumnInfo(name="pulls_url")
    val pullsUrl: String? = null,

    @ColumnInfo(name="default_branch")
    val defaultBranch: String? = null,

    @ColumnInfo(name="hooks_url")
    val hooksUrl: String? = null,

    @ColumnInfo(name="trees_url")
    val treesUrl: String? = null,

    @ColumnInfo(name="tags_url")
    val tagsUrl: String? = null,

    @ColumnInfo(name="private")
    val jsonMemberPrivate: Boolean? = null,

    @ColumnInfo(name="contributors_url")
    val contributorsUrl: String? = null,

    @ColumnInfo(name="has_downloads")
    val hasDownloads: Boolean? = null,

    @ColumnInfo(name="notifications_url")
    val notificationsUrl: String? = null,

    @ColumnInfo(name="open_issues_count")
    val openIssuesCount: Int? = null,

    @ColumnInfo(name="description")
    val description: String? = null,

    @ColumnInfo(name="created_at")
    val createdAt: String? = null,

    @ColumnInfo(name="watchers")
    val watchers: Int? = null,

    @ColumnInfo(name="keys_url")
    val keysUrl: String? = null,

    @ColumnInfo(name="deployments_url")
    val deploymentsUrl: String? = null,

    @ColumnInfo(name="has_projects")
    val hasProjects: Boolean? = null,

    @ColumnInfo(name="archived")
    val archived: Boolean? = null,

    @ColumnInfo(name="has_wiki")
    val hasWiki: Boolean? = null,

    @ColumnInfo(name="updated_at")
    val updatedAt: String? = null,

    @ColumnInfo(name="comments_url")
    val commentsUrl: String? = null,

    @ColumnInfo(name="stargazers_url")
    val stargazersUrl: String? = null,

    @ColumnInfo(name="disabled")
    val disabled: Boolean? = null,

    @ColumnInfo(name="git_url")
    val gitUrl: String? = null,

    @ColumnInfo(name="has_pages")
    val hasPages: Boolean? = null,

    @ColumnInfo(name="commits_url")
    val commitsUrl: String? = null,

    @ColumnInfo(name="compare_url")
    val compareUrl: String? = null,

    @ColumnInfo(name="git_commits_url")
    val gitCommitsUrl: String? = null,

    @ColumnInfo(name="topics")
    val topics: List<String?>? = null,

    @ColumnInfo(name="blobs_url")
    val blobsUrl: String? = null,

    @ColumnInfo(name="git_tags_url")
    val gitTagsUrl: String? = null,

    @ColumnInfo(name="merges_url")
    val mergesUrl: String? = null,

    @ColumnInfo(name="downloads_url")
    val downloadsUrl: String? = null,

    @ColumnInfo(name="has_issues")
    val hasIssues: Boolean? = null,

    @ColumnInfo(name="web_commit_signoff_required")
    val webCommitSignoffRequired: Boolean? = null,

    @ColumnInfo(name="url")
    val url: String? = null,

    @ColumnInfo(name="contents_url")
    val contentsUrl: String? = null,

    @ColumnInfo(name="mirror_url")
    val mirrorUrl: String? = null,

    @ColumnInfo(name="milestones_url")
    val milestonesUrl: String? = null,

    @ColumnInfo(name="teams_url")
    val teamsUrl: String? = null,

    @ColumnInfo(name="fork")
    val fork: Boolean? = null,

    @ColumnInfo(name="issues_url")
    val issuesUrl: String? = null,

    @ColumnInfo(name="events_url")
    val eventsUrl: String? = null,

    @ColumnInfo(name="issue_events_url")
    val issueEventsUrl: String? = null,

    @ColumnInfo(name="assignees_url")
    val assigneesUrl: String? = null,

    @ColumnInfo(name="open_issues")
    val openIssues: Int? = null,

    @ColumnInfo(name="watchers_count")
    val watchersCount: Int? = null,

    @ColumnInfo(name="node_id")
    val nodeId: String? = null,

    @ColumnInfo(name="homepage")
    val homepage: String? = null,

    @ColumnInfo(name="forks_count")
    val forksCount: Int? = null,

    var isSelected: Boolean? = false
) {

    companion object {
        /**
         * Table name to store categories
         */
        const val TABLE_NAME: String = "repositories"
    }

}
