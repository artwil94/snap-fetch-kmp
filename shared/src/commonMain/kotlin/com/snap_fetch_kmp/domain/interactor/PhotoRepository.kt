package com.snap_fetch_kmp.domain.interactor

import com.snap_fetch_kmp.domain.entity.photo.Photo
import com.snap_fetch_kmp.util.KResult

interface PhotoRepository {
    suspend fun getPhotos(
        page: Int,
        limit: Int
    ): KResult<List<Photo>>
}