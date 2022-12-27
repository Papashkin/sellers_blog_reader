package com.antsfamily.data.models

data class PostApiModel(
    val id: Int,
    val date: String,
    val title: PostTitleApiModel,
    val content: PostContentApiModel,
)

data class PostTitleApiModel(
    val rendered: String,
)
data class PostContentApiModel(
    val rendered: String,
)


