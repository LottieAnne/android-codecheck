package jp.co.yumemi.android.code_check

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import jp.co.yumemi.android.code_check.domain.RepositoryInfo
import jp.co.yumemi.android.code_check.domain.RepositoryListRepository
import jp.co.yumemi.android.codecheck.data.R
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryListRepositoryModule {
    @Binds
    fun bindRepositoryListRepository(impl: RepositoryListRepositoryImpl) : RepositoryListRepository
}
class RepositoryListRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val client: HttpClient,
) : RepositoryListRepository {
    override suspend fun searchResults(inputText: String): List<RepositoryInfo> {
            val response: HttpResponse = client.get("https://api.github.com/search/repositories") {
                header("Accept", "application/vnd.github.v3+json")
                parameter("q", inputText)
            }

            val jsonBody = JSONObject(response.receive<String>())

            val jsonItems = jsonBody.optJSONArray("items")!!

        return jsonItems.toRepositoryInfoList()
    }
    private fun JSONArray.toRepositoryInfoList() : List<RepositoryInfo> {
        val items = mutableListOf<RepositoryInfo>()

        /**
         * アイテムの個数分ループする
         */
        for (i in 0 until this.length()) {
            val jsonItem = this.optJSONObject(i)!!
            val name = jsonItem.optString("full_name")
            val ownerIconUrl = jsonItem.optJSONObject("owner")!!.optString("avatar_url")
            val language = jsonItem.optString("language")
            val stargazersCount = jsonItem.optLong("stargazers_count")
            val watchersCount = jsonItem.optLong("watchers_count")
            val forksCount = jsonItem.optLong("forks_conut")
            val openIssuesCount = jsonItem.optLong("open_issues_count")

            items.add(
                RepositoryInfo(
                    name = name,
                    ownerIconUrl = ownerIconUrl,
                    language = context.getString(R.string.written_language, language),
                    stargazersCount = stargazersCount,
                    watchersCount = watchersCount,
                    forksCount = forksCount,
                    openIssuesCount = openIssuesCount
                )
            )
        }
        return items
    }
}