/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.repositoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.domain.RepositoryInfo
import jp.co.yumemi.android.code_check.domain.RepositoryListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * RepositoryDetailFragment で使う
 */
@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private val repository: RepositoryListRepository,
) : ViewModel() {

    private val _searchTextFlow = MutableStateFlow("")
    private val _repositoryList = MutableStateFlow<List<RepositoryInfo>>(emptyList())

    val uiState = combine(_repositoryList, _searchTextFlow) { repositoryList, searchText ->
        RepositoryListUiState(
            repositoryList = repositoryList,
            searchText = searchText,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RepositoryListUiState.InitialValue,
    )


    // 検索結果
    fun searchResults(inputText: String) {
        viewModelScope.launch {
        _repositoryList.value = repository.searchResults(inputText)
        }
    }

    fun onValueChange(value: String) {
        _searchTextFlow.value = value
    }
}


data class RepositoryListUiState(
    val repositoryList: List<jp.co.yumemi.android.code_check.domain.RepositoryInfo>,
    val searchText: String,
) {
    companion object {
        val InitialValue = RepositoryListUiState(
            repositoryList = emptyList(),
            searchText = ""
        )
    }
}
