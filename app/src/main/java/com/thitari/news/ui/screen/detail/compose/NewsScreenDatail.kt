package com.thitari.news.ui.screen.detail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NewsScreenDetail(
    url: String,
    onBackClick: () -> Unit,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            NewsScreenDetailTopBar(
                onBackClick = onBackClick,
            )
        }
    ) {

        AndroidView(
            factory = {
                WebView(it).apply {
                    webViewClient = WebViewClient()
                }
            },
            update = { it.loadUrl(url) }
        )
    }
}

@Composable
fun NewsScreenDetailTopBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(title = {
        Text(text = "NewsDetail",
            fontSize = 22.sp)
    },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(
                onClick = onBackClick) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Preview
@Composable
fun NewsScreenDetailPreview() {
    NewsScreenDetail("https://www.google.com/", onBackClick = {})
}

@Preview
@Composable
fun NewsScreenDetailTopBarPreview() {
    NewsScreenDetailTopBar(onBackClick = {})
}
