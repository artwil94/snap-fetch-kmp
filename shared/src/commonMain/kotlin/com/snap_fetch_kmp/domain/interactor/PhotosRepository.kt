package com.snap_fetch_kmp.domain.interactor

import com.snap_fetch_kmp.domain.entity.photo.Photo
import com.snap_fetch_kmp.util.ResponseState

interface PhotosRepository {
    suspend fun getPhotos(
        page: Int,
        limit: Int
    ): ResponseState<List<Photo>>

    suspend fun getPhotoDetails(
        photoId: String
    ): ResponseState<Photo>
}