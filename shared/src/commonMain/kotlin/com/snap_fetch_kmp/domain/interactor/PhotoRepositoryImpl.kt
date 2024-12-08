package com.snap_fetch_kmp.domain.interactor

import com.snap_fetch_kmp.data.remote.ApiService
import com.snap_fetch_kmp.domain.entity.photo.Photo
import com.snap_fetch_kmp.domain.entity.photo.toEntity
import com.snap_fetch_kmp.util.KResult

class PhotoRepositoryImpl(private val apiService: ApiService) : PhotoRepository {

    override suspend fun getPhotos(page: Int, limit: Int): KResult<List<Photo>> {
        val response = apiService.getPhotos(page = page, limit = limit)
        return if (response.isSuccess) {
            KResult.success(response.value()!!.map { it.toEntity() }.requireNoNulls())
        } else {
            KResult.failure(response.error()!!)
        }
    }

}