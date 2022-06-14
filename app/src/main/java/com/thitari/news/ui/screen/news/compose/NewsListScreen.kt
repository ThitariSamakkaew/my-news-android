package com.thitari.news.ui.screen.news.compose

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.thitari.news.R
import com.thitari.news.data.model.Article
import com.thitari.news.ui.screen.news.viewmodel.BaseNewsViewModel
import com.thitari.news.ui.screen.news.viewmodel.BusinessViewModel
import com.thitari.news.ui.screen.news.viewmodel.NationalViewModel
import com.thitari.news.ui.screen.news.viewmodel.PoliticalViewModel
import com.thitari.news.ui.screen.news.viewmodel.SportViewModel
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun NewsListScreen(
    onArticleClick: (Article) -> Unit,
) {
    NewsListContent(
        onItemClick = onArticleClick
    )
}

@ExperimentalPagerApi
@Composable
fun NewsListContent(
    onItemClick: (Article) -> Unit,
) {
    val pageState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = { TopBar() }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Tabs(
                tabs = tabs,
                onTabClick = { page ->
                    scope.launch {
                        pageState.animateScrollToPage(page)
                    }
                },
                selectedTab = pageState.currentPage,
            )

            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                state = pageState,
                count = tabs.size
            ) { page ->

                println("Page: $page")
                val viewModel: BaseNewsViewModel = when (page) {
                    PAGE_BUSINESS -> hiltViewModel<BusinessViewModel>()
                    PAGE_SPORT -> hiltViewModel<SportViewModel>()
                    PAGE_POLITICAL -> hiltViewModel<PoliticalViewModel>()

                    else -> hiltViewModel<NationalViewModel>()
                }

                val articles: LazyPagingItems<Article> =
                    viewModel.articles.collectAsLazyPagingItems()
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                ) {
                    items(articles) { article ->
                        article?.let {
                            NewsItem(
                                article = it,
                                onItemClick = onItemClick,
                                selectedItem = false,
                            )
                        }
                    }
                }
                articles.apply {

                    when {
                        loadState.refresh is LoadState.Loading -> {
                            NewsListLoading()
                        }
                        loadState.append is LoadState.Loading -> {
                            NewsListLoading()
                        }
                        loadState.append is LoadState.Error -> {
                            val context = LocalContext.current
                            Toast.makeText(context,
                                "Some thing get error",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "NewsApp",
                fontSize = 22.sp)
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    )
}

@Composable
private fun Tabs(
    tabs: List<NewsListTabItem>,
    onTabClick: (Int) -> Unit,
    selectedTab: Int,
) {

    ScrollableTabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTab == index,
                onClick = {
                    onTabClick(index)

                },
                selectedContentColor = Color.White,
                unselectedContentColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
            ) {

                Text(
                    text = stringResource(id = tab.title),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(bottom = 2.dp))
}

@Composable
fun NewsItem(
    article: Article,
    onItemClick: (Article) -> Unit,
    selectedItem: Boolean,
) {

    Card {

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)) {

                Tab(
                    selected = selectedItem,
                    onClick = {
                        onItemClick(article)
                    }
                ) {
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))

                    Text(
                        text = article.title,
                        modifier = Modifier.padding(bottom = 2.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif
                    )

                    AsyncImage(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(article.urlToImage)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        alignment = Alignment.TopStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(all = 2.dp)
                    )

                    Spacer(modifier = Modifier.padding(bottom = 6.dp))

                    Text(
                        text = article.description,
                        modifier = Modifier.padding(bottom = 4.dp),
                        fontSize = 18.sp
                    )

                    TextDetail(
                        nameRes = R.string.news_list_item_detail_author,
                        nameDescription = article.author
                    )

                    TextDetail(
                        nameRes = R.string.news_list_item_detail_publishedAt,
                        nameDescription = article.publishedAt
                    )

                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        }
    }
}

@Composable
fun TextDetail(
    @StringRes nameRes: Int,
    nameDescription: String,

    ) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {

        Text(text = stringResource(id = nameRes),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold)

        Text(
            text = nameDescription,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }

    Spacer(modifier = Modifier.padding(start = 2.dp, bottom = 2.dp))
}

private val tabs = listOf(
    NewsListTabItem.BUSINESS,
    NewsListTabItem.SPORT,
    NewsListTabItem.POLITICAL,
    NewsListTabItem.NATIONAL
)

@Preview
@Composable
fun NewsListPreview() {

    val listNews = mutableListOf<Article>()
    for (i in 0..100) {
        listNews.add(article)
    }
}

private val article = Article(
    sourceName = "Engadget",
    author = "Kris Holt",
    title = "Senate bill would break up Google’s ad business",
    description = "A bill that would break up Google's\r\n advertising business if it becomes " +
            "law has been introduced in the Senate. The Competition and Transparency " +
            "in Digital Advertising Act, which has support on both sides of the aisle, " +
            "would prevent companies that process mor…",
    url = "https://www.engadget.com/senate-digital-advertising-bill-google-alphabet-antitrust-192825002.html",
    urlToImage = "https://static01.nyt.com/images/2022/05/16/us/16econ-briefing-mcd-s-russia/16econ-briefing-mcd-s-russia-facebookJumbo.jpg",
    publishedAt = "2022-05-19T19:28:25Z",
    content = "A bill that would break up Google's\r\n advertising business if it becomes " +
            "law has been introduced in the Senate. The Competition and Transparency in Digital " +
            "Advertising Act, which has support on both … [+2839 chars]"
)

private const val PAGE_BUSINESS = 0
private const val PAGE_SPORT = 1
private const val PAGE_POLITICAL = 2
