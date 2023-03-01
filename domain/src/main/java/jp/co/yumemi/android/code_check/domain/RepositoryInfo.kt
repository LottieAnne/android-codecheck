package jp.co.yumemi.android.code_check.domain

import kotlinx.serialization.Serializable

@Serializable
data class RepositoryInfo(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
)