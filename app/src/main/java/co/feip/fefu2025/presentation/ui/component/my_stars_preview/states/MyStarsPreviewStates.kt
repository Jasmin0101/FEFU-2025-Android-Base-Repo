package co.feip.fefu2025.presentation.ui.component.my_stars_preview.states

import co.feip.fefu2025.domain.model.RepositoryModel


sealed class MyStarsPreviewStates {
    data object Loading : MyStarsPreviewStates()
    data class Loaded(val list: List<RepositoryModel>) : MyStarsPreviewStates()
    data object Error : MyStarsPreviewStates()
    data object Empty : MyStarsPreviewStates()
}