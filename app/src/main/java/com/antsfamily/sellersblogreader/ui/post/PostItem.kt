package com.antsfamily.sellersblogreader.ui.post

import com.antsfamily.data.models.PostApiModel
import com.antsfamily.sellersblogreader.ui.utils.simpleDateFormat

data class PostItem(
    val id: Int,
    val date: String,
    val title: String,
    val content: String,
)

fun PostApiModel.mapToItem(): PostItem {
    val formattedDate = simpleDateFormat.format(this.date)
    return PostItem(
        id = this.id,
        title = this.title.rendered,
        content = this.content.rendered,
        date = formattedDate
    )
}
