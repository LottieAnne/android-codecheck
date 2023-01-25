package jp.co.yumemi.android.code_check.repositoryDetail

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.RepositoryInfo

@Composable
fun RepositoryDetailRoute(repositoryInfo: RepositoryInfo) {
    RepositoryDetailScreen(
        repositoryInfo,
        imagePainter = painterResource(R.drawable.eevee)
    )
}

@Composable
private fun RepositoryDetailScreen(
    repositoryInfo: RepositoryInfo,
    imagePainter: Painter,
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
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier.padding(32.dp),
        )
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
        ),
        imagePainter = painterResource(R.drawable.eevee)
    )
}
