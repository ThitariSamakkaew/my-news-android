package com.thitari.ui.screen.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thitari.news.R

@Composable
fun NewsListLoading() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = stringResource(id = R.string.news_list_loading),
            fontSize = 16.sp,
            modifier = Modifier.padding(all = 10.dp)
        )

        CircularProgressIndicator(
            modifier = Modifier
                .width(50.dp)
                .height(3.dp),
            color = MaterialTheme.colors.primary,
        )
    }
}

@Preview
@Composable
fun NewsListLoadingPreview() {
    NewsListLoading()
}
