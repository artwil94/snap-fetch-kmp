package com.snap_fetch_kmp.domain.interactor

import com.snap_fetch_kmp.data.remote.ApiService
import com.snap_fetch_kmp.domain.entity.photo.Photo
import com.snap_fetch_kmp.domain.entity.photo.toEntity
import com.snap_fetch_kmp.util.ResponseState

class PhotosRepositoryImpl(private val apiService: ApiService) : PhotosRepository {

    override suspend fun getPhotos(page: Int, limit: Int): ResponseState<List<Photo>> {
        val response = apiService.getPhotos(page = page, limit = limit)
        return if (response.isSuccess) {
            ResponseState.success(response.value()!!.map { it.toEntity() }.requireNoNulls())
        } else {
            ResponseState.failure(response.error()!!)
        }
    }

    override suspend fun getPhotoDetails(photoId: String): ResponseState<Photo> {
        val response = apiService.getPhotoDetails(photoId = photoId)
        return if (response.isSuccess) {
            ResponseState.success(response.value()!!.toEntity())
        } else {
            ResponseState.failure(response.error()!!)
        }
    }

}