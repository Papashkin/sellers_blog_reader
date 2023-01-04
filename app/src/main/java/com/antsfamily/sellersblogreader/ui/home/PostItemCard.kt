package com.antsfamily.sellersblogreader.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.antsfamily.sellersblogreader.ui.common.HtmlText
import com.antsfamily.sellersblogreader.ui.theme.FontSize
import com.antsfamily.sellersblogreader.ui.theme.Padding
import com.antsfamily.sellersblogreader.ui.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostItemCard(
    post: HomeItem,
    onItemClick: (Int) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        onClick = { onItemClick(post.id) }
    ) {
        Column(
            modifier = Modifier.padding(top = Padding.small)
        ) {
            HtmlText(
                text = post.title,
                style = TextStyle(fontSize = FontSize.H5, fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(Padding.xSmall)
            )
            AsyncImage(
                model = HtmlCompat
                    .fromHtml(post.imageUrl, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    .toString(),
                contentDescription = null
            )
            HtmlText(
                text = post.excerpt,
                style = Typography.body1,
                modifier = Modifier.padding(start = Padding.xSmall, top = Padding.small)
            )
            HtmlText(
                text = post.date,
                fontSize = FontSize.Caption,
                modifier = Modifier.padding(Padding.xSmall)
            )
            Spacer(modifier = Modifier.height(Padding.xSmall))
        }
    }
}
