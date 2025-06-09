package co.feip.fefu2025.presentation.ui.states.git_lab_page

import co.feip.fefu2025.domain.model.RepositoryModel


sealed class MyStarsPreviewStates {
    object Loading : MyStarsPreviewStates()
    data class Loaded(val list: List<RepositoryModel>)  : MyStarsPreviewStates()
    object Error : MyStarsPreviewStates()
    object Empty : MyStarsPreviewStates()
}