package com.antsfamily.sellersblogreader.ui.home

import com.antsfamily.data.models.PostApiModel
import java.text.SimpleDateFormat
import java.util.Locale

data class HomeItem(
    val id: Int,
    val date: String,
    val title: String,
    val imageUrl: String,
    val excerpt: String,
)

val simpleDateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm", Locale.getDefault())

fun PostApiModel.mapToItem(): HomeItem {
    val formattedDate = simpleDateFormat.format(this.date)
    return HomeItem(
        id = this.id,
        title = this.title.rendered,
        excerpt = this.excerpt.rendered,
        imageUrl = this.headJson.image,
        date = formattedDate
    )
}
