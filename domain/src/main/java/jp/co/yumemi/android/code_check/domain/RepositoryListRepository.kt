package jp.co.yumemi.android.code_check.domain

interface RepositoryListRepository {
    suspend fun searchResults(inputText: String): List<RepositoryInfo>
}