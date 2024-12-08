package com.snap_fetch_kmp.data.remote.photos

import com.snap_fetch_kmp.data.remote.photos.dto.PhotoDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Headers
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface PhotosAPI {

    @GET("v2/list")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<PhotoDto>

    @GET("/id/{id}/info")
    @Headers("Content-Type: application/json")
    suspend fun getPhotoDetails(@Path("id") photoId: String): PhotoDto
}