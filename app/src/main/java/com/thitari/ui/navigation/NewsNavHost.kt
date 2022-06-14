package com.thitari.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thitari.ui.navigation.NavRoute.ARTICLE
import com.thitari.ui.navigation.NavRoute.NEWS_LIST
import com.thitari.ui.screen.compose.NewsListScreen
import com.thitari.ui.screen.newsWebView.NewsScreenDetail
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalPagerApi
@Composable
fun NewsNavHost(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = NEWS_LIST
    ) {

        composable(NEWS_LIST) {

            NewsListScreen(
                onArticleClick = { article ->
                    val encodeUrl = URLEncoder.encode(article.url,
                        StandardCharsets.UTF_8.toString())
                    navController.navigate("NewsScreenDetail/${encodeUrl}")
                }
            )
        }

        composable(
            route = ARTICLE,
            arguments = listOf(navArgument("articleUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("articleUrl")?.let { url ->
                NewsScreenDetail(
                    url = url,
                    onBackClick = {
                        navController.popBackStack()
                    })
            }
        }
    }
}
