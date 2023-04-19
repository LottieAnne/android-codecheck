package jp.co.yumemi.android.code_check.repositoryDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import jp.co.yumemi.android.code_check.domain.RepositoryInfo

@Composable
fun RepositoryDetailRoute(repositoryInfo: jp.co.yumemi.android.code_check.domain.RepositoryInfo) {
    RepositoryDetailScreen(
        repositoryInfo,
    )
}

@Composable
private fun RepositoryDetailScreen(
    repositoryInfo: jp.co.yumemi.android.code_check.domain.RepositoryInfo,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xffffffff)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = repositoryInfo.ownerIconUrl,
            contentDescription = null,
        ) {
            when (painter.state) {
                AsyncImagePainter.State.Empty,
                is AsyncImagePainter.State.Error,
                is AsyncImagePainter.State.Loading -> Unit
                is AsyncImagePainter.State.Success -> {
                    SubcomposeAsyncImageContent()
                }
            }
        }
        Text(
            text = repositoryInfo.name,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = repositoryInfo.ownerIconUrl,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(text = repositoryInfo.language,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp),
        )
    }
}


@Preview
@Composable
private fun RepositoryDetailScreenPreview() {
    RepositoryDetailScreen(
        repositoryInfo = jp.co.yumemi.android.code_check.domain.RepositoryInfo(
            name = "name",
            ownerIconUrl = "ownerIconUrl",
            language = "language",
            stargazersCount = 2,
            watchersCount = 4,
            forksCount = 6,
            openIssuesCount = 8,
        ),
    )
}
