package com.enesselcuk.watchmovies.source.remote.response.videos

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("results") val results: List<ResultVideos>
)