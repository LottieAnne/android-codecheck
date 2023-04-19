/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
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
                        onRepositoryClick = {navController.navigate("detail")}
                    )
                }
                composable("detail") {
//                    val args = it.arguments?.getParcelable("key", RepositoryDetailArg::class.java)
//                    RepositoryDetailRoute(repositoryInfo = checkNotNull(args?.repositoryInfo))
                }
            }
        }
    }
}
