package co.feip.fefu2025.presentation.ui.states.git_lab_page


import co.feip.fefu2025.domain.model.Repository

sealed class GitLabPageState {
    object Loading : GitLabPageState()
    object Loaded : GitLabPageState()
    object Error : GitLabPageState()
}