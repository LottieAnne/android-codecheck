package jp.co.yumemi.android.code_check.repositoryList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import jp.co.yumemi.android.code_check.RepositoryInfo
import jp.co.yumemi.android.code_check.RepositoryListUiState
import jp.co.yumemi.android.code_check.RepositoryListViewModel

@Composable
fun RepositoryListRoute(
    viewModel: RepositoryListViewModel,
    onRepositoryClick: (RepositoryInfo) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    RepositoryListScreen(
        uiState = uiState,
        onSearchValueChange = viewModel::onValueChange,
        onSearch = {
            viewModel.searchResults(it, context)
        },
        onRepositoryClick = onRepositoryClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RepositoryListScreen(
    uiState: RepositoryListUiState,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onRepositoryClick: (RepositoryInfo) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Android Engineer CodeCheck") },
            )
        },
    ) {
        Column(
            modifier = Modifier.padding(it),
        ) {
            TextField(
                value = uiState.searchText,
                onValueChange = onSearchValueChange,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearch(uiState.searchText) },
                )
            )
            LazyColumn {
                items(
                    items = uiState.repositoryList,
                ) { repository ->
                    Text(
                        text = repository.name,
                        modifier = Modifier.clickable {
                            onRepositoryClick(
                                repository
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    RepositoryListScreen(
        uiState = RepositoryListUiState.InitialValue,
        onSearchValueChange = {},
        onSearch = {},
        onRepositoryClick = {},
    )
}