package com.snap_fetch_kmp.presentation.viewmodel

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.snap_fetch_kmp.domain.entity.photo.Photo
import com.snap_fetch_kmp.domain.interactor.PhotosRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PhotoDetailsViewModel(
    private val repository: PhotosRepository,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(viewModelScope, PhotoDetailsUIState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()
    val actions = PhotoDetailsActions(
        start = ::getPhotoDetails,
        onClose = ::resetError
    )

    private fun getPhotoDetails(photoId: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    error = false,
                    isLoading = true
                )
            }
            val response = repository.getPhotoDetails(photoId = photoId)
            if (response.isSuccess) {
                _uiState.update {
                    it.copy(
                        photo = response.value(),
                        isLoading = false,
                        error = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        photo = null,
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

}

data class PhotoDetailsActions(
    val start: (photoId: String) -> Unit = {},
    val onClose: () -> Unit
)

data class PhotoDetailsUIState(
    val isLoading: Boolean = true,
    val error: Boolean = false,
    val photo: Photo? = null
)