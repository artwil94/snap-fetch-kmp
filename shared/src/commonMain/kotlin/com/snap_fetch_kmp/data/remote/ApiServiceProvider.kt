package com.snap_fetch_kmp.data.remote

import com.snap_fetch_kmp.data.remote.photos.PhotosApiProvider
import com.snap_fetch_kmp.data.remote.photos.dto.PhotoDto
import com.snap_fetch_kmp.util.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class ApiServiceProvider(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ApiService {

    private val photosApi = PhotosApiProvider()

    override suspend fun getPhotos(page: Int, limit: Int): ResponseState<List<PhotoDto>> {
        return withContext(dispatcher) { photosApi.getPhotos(page = page, limit = limit) }
    }

    override suspend fun getPhotoDetails(photoId: String): ResponseState<PhotoDto> {
        return withContext(dispatcher) { photosApi.getPhotoDetails(photoId = photoId) }
    }
}