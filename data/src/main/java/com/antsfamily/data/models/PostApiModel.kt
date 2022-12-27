package com.antsfamily.data.models

data class PostApiModel(
    val id: Int,
    val date: String,
    val title: PostRenderedDataApiModel,
    val content: PostRenderedDataApiModel,
    val excerpt: PostRenderedDataApiModel,
)

data class PostRenderedDataApiModel(val rendered: String)
