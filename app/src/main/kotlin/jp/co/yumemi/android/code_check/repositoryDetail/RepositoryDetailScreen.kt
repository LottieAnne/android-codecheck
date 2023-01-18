package jp.co.yumemi.android.code_check.repositoryDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.co.yumemi.android.code_check.RepositoryInfo

@Composable
fun RepositoryDetailRoute(repositoryInfo: RepositoryInfo) {
    RepositoryDetailScreen(repositoryInfo)
}

@Composable
private fun RepositoryDetailScreen(
    repositoryInfo: RepositoryInfo
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = repositoryInfo.name,
        )
        Text(text = repositoryInfo.language.repeat(50))
    }

}

@Preview
@Composable
fun RepositoryDetailScreenPreview() {
    RepositoryDetailScreen(
        repositoryInfo = RepositoryInfo(
            name = "name",
            ownerIconUrl = "ownerIconUrl",
            language = "language",
            stargazersCount = 2,
            watchersCount = 4,
            forksCount = 6,
            openIssuesCount = 8,
        )
    )
}