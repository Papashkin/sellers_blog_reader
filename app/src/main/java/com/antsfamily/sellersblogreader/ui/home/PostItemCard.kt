package com.antsfamily.sellersblogreader.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.antsfamily.data.models.PostApiModel
import com.antsfamily.sellersblogreader.ui.theme.FontSize
import com.antsfamily.sellersblogreader.ui.theme.Padding
import com.antsfamily.sellersblogreader.ui.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostItemCard(
    post: PostApiModel,
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
                text = post.title.rendered,
                style = TextStyle(fontSize = FontSize.H5, fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(Padding.xSmall)
            )
            HtmlText(
                text = post.excerpt.rendered,
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


@Composable
fun HtmlText(
    text: String,
    modifier: Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Text(
        text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
        style = style,
        modifier = modifier,
        fontSize = fontSize
    )
}
