package com.antsfamily.data.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PostApiModel(
    val id: Int,
    val date: Date,
    val title: PostRenderedDataApiModel,
    @SerializedName("yoast_head_json")
    val headJson: YoastHeadJsonApiModel,
    val content: PostRenderedDataApiModel,
    val excerpt: PostRenderedDataApiModel,
)

data class PostRenderedDataApiModel(val rendered: String)

data class YoastHeadJsonApiModel(
    @SerializedName("twitter_image")
    val image: String,
)
