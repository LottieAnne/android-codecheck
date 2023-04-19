/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.code_check.repositoryDetail.RepositoryDetailArg
import jp.co.yumemi.android.code_check.repositoryDetail.RepositoryDetailRoute
import jp.co.yumemi.android.code_check.repositoryDetail.asNavigationPath
import jp.co.yumemi.android.code_check.repositoryDetail.navRepositoryDetailArg
import jp.co.yumemi.android.code_check.repositoryDetail.routePlaceholder
import jp.co.yumemi.android.code_check.repositoryList.RepositoryListRoute

@AndroidEntryPoint
class TopActivity : AppCompatActivity(R.layout.activity_top) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    RepositoryListRoute(
                        viewModel = hiltViewModel(),
                        onRepositoryClick = {
                            val arg = RepositoryDetailArg(repositoryInfo = it)
                            navController.navigate("detail/${arg.asNavigationPath()}")
                        }
                    )
                }
                composable(
                    "detail/${RepositoryDetailArg::class.routePlaceholder}",
                    arguments = listOf(navRepositoryDetailArg())
                ) {
                    val arg = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        it.arguments?.getParcelable("RepositoryDetailArg", RepositoryDetailArg::class.java)
                    } else {
                        it.arguments?.getParcelable<RepositoryDetailArg>("RepositoryDetailArg")
                    }
                    RepositoryDetailRoute(repositoryInfo = checkNotNull(arg?.repositoryInfo))
                }
            }
        }
    }
}
