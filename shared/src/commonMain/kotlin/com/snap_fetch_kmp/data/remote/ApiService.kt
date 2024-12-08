package com.snap_fetch_kmp.data.remote

import com.snap_fetch_kmp.data.remote.photos.dto.PhotoDto
import com.snap_fetch_kmp.util.ResponseState

interface ApiService {

    suspend fun getPhotos(page: Int, limit: Int): ResponseState<List<PhotoDto>>

    suspend fun getPhotoDetails(photoId: String): ResponseState<PhotoDto>
}