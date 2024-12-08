package com.snap_fetch_kmp.presentation.viewmodel

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.snap_fetch_kmp.domain.entity.photo.Photo
import com.snap_fetch_kmp.domain.interactor.PhotoRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PhotosListViewModel(
    private val repository: PhotoRepository,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(viewModelScope, PhotosUIState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()

    val actions = PhotosActions(
        start = {
            getPhotos()
        },
        loadMore = {
            getPhotos()
        },
        onTryAgain = { getPhotos() },
        onClose = { resetError() }
    )
    private var currentPage = 1

    private fun getPhotos() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val response = repository.getPhotos(page = currentPage, limit = PHOTOS_LIMIT)
            if (response.isSuccess) {
                val newPhotos = response.value() ?: emptyList()
                _uiState.update {
                    it.copy(
                        photos = uiState.value.photos + newPhotos,
                        isLoading = false,
                        error = false
                    )
                }
                currentPage++
            } else {
                _uiState.update {
                    it.copy(
                        photos = listOf(),
                        error = true,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun resetError() {
        _uiState.update {
            it.copy(
                error = false
            )
        }
    }

    companion object {
        const val PHOTOS_LIMIT = 20
    }
}


data class PhotosActions(
    val start: () -> Unit,
    val loadMore: () -> Unit,
    val onTryAgain: () -> Unit,
    val onClose: () -> Unit
)

data class PhotosUIState(
    val isLoading: Boolean = true,
    val error: Boolean = false,
    val photos: List<Photo> = listOf(),
)