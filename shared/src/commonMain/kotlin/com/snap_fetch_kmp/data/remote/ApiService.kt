package com.snap_fetch_kmp.data.remote

import com.snap_fetch_kmp.data.remote.photos.dto.PhotoDto
import com.snap_fetch_kmp.util.KResult

interface ApiService {
    suspend fun getPhotos(
        page: Int,
        limit: Int
    ): KResult<List<PhotoDto>>
}