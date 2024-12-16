package com.snap_fetch_kmp.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.snap_fetch_kmp.android.R
import com.snap_fetch_kmp.composable.PreviewWrapper
import com.snap_fetch_kmp.util.openExternalBrowser
import com.snap_fetch_kmp.components.AlertDialog
import com.snap_fetch_kmp.components.LoadingScreen
import com.snap_fetch_kmp.components.PhotoCard
import com.snap_fetch_kmp.components.PhotoCardType
import com.snap_fetch_kmp.components.PhotoDetailsItem
import com.snap_fetch_kmp.components.TopBar
import com.snap_fetch_kmp.domain.entity.photo.Photo
import com.snap_fetch_kmp.navigation.LocalNavController
import com.snap_fetch_kmp.presentation.viewmodel.PhotoDetailsActions
import com.snap_fetch_kmp.presentation.viewmodel.PhotoDetailsUIState
import com.snap_fetch_kmp.presentation.viewmodel.PhotoDetailsViewModel
import com.snap_fetch_kmp.theme.SfTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoDetailsScreen(photoId: String, viewModel: PhotoDetailsViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PhotoDetailsContent(uiState = uiState, actions = viewModel.actions, photoId = photoId)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PhotoDetailsContent(
    uiState: PhotoDetailsUIState,
    actions: PhotoDetailsActions,
    photoId: String
) {
    LaunchedEffect(key1 = Unit) {
        actions.start(photoId)
    }
    val navController = LocalNavController.current
    val context = LocalContext.current
    if (uiState.isLoading) {
        LoadingScreen()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SfTheme.colors.primarySurface)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val photo = uiState.photo
                TopBar(
                    modifier = Modifier.padding(start = SfTheme.dimensions.padding),
                    onNavigationIconClick = { navController.popBackStack() },
                    title = stringResource(id = R.string.photo_id, photo?.id ?: "")
                )
                photo?.let {
                    PhotoCard(
                        modifier = Modifier.padding(start = SfTheme.dimensions.padding),
                        photo = it,
                        photoCardType = PhotoCardType.DetailItem
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SfTheme.dimensions.padding)
                            .clip(shape = SfTheme.shapes.photoCard)
                            .background(color = Color(0xFF2d2e32), shape = SfTheme.shapes.photoCard)

                    ) {
                        Column(
                            modifier = Modifier.padding(
                                horizontal = SfTheme.dimensions.padding,
                                vertical = SfTheme.dimensions.paddingM
                            )
                        ) {
                            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingS))
                            PhotoDetailsItem(
                                value = photo.author,
                                subtitle = stringResource(id = R.string.author),
                                icon = R.drawable.ic_author,
                                textStyle = SfTheme.typography.author
                            )
                            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingM))
                            PhotoDetailsItem(
                                value = "${photo.width} x ${photo.height}",
                                subtitle = stringResource(id = R.string.format),
                                icon = R.drawable.ic_dimensions
                            )
                            Spacer(modifier = Modifier.height(SfTheme.dimensions.paddingM))
                            PhotoDetailsItem(
                                value = photo.downloadUrl,
                                subtitle = stringResource(id = R.string.download_url),
                                icon = R.drawable.ic_download,
                                textStyle = SfTheme.typography.downloadLink,
                                clickableValue = true,
                                onValueClick = {
                                    openExternalBrowser(
                                        context = context,
                                        url = photo.downloadUrl
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    if (uiState.error) {
        AlertDialog(
            body = stringResource(id = R.string.alert_dialog_body),
            firstActionCTA = stringResource(id = R.string.try_again),
            onFirstActionClick = { actions.start(photoId) },
            onSecondActionClick = actions.onClose,
            secondActionCTA = stringResource(
                id = R.string.close
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PhotoDetailsScreenPreview() {
    val uiState = PhotoDetailsUIState(
        photo = Photo(
            id = "0",
            author = "Artur Wilczek",
            width = 500,
            height = 1000,
            url = "https://www.miquido.com/",
            downloadUrl = "https://www.miquido.com/"
        )
    )
    PreviewWrapper {
        PhotoDetailsContent(
            uiState = uiState,
            actions = PhotoDetailsActions(onClose = {}),
            photoId = ""
        )
    }
}