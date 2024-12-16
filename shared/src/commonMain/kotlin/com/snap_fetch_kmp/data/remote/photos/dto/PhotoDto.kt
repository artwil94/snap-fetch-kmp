package com.snap_fetch_kmp.data.remote.photos.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    @SerialName("id") val id: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("download_url") val downloadUrl: String? = null
)