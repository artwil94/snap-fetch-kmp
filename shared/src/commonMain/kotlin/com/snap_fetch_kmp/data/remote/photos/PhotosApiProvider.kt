package com.snap_fetch_kmp.data.remote.photos

import com.snap_fetch_kmp.data.remote.createPhotosAPI
import com.snap_fetch_kmp.data.remote.photos.dto.PhotoDto
import com.snap_fetch_kmp.getHttpClient
import com.snap_fetch_kmp.util.KResult
import com.snap_fetch_kmp.util.runSafely
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import io.github.aakira.napier.Napier
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PhotosApiProvider {

    private val ktorfit: Ktorfit = ktorfit {
        baseUrl("BuildKonfig.APIGEE_BASE_URL")
        httpClient(getHttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = "HTTP Client", message = message)
                    }
                }
                level = LogLevel.ALL
            }
        })
    }

    val photosAPI = ktorfit.createPhotosAPI()

    suspend fun getPhotos(
        page: Int,
        limit: Int
    ): KResult<List<PhotoDto>> {
        return runSafely {
            val response = photosAPI.getPhotos(page = page, limit = limit)
            return KResult.success(response)
        }
    }
}