package co.feip.fefu2025.presentation.ui.states.git_lab_page


sealed class GitLabPageState {
    object Loading : GitLabPageState()
    object Loaded : GitLabPageState()
    object Error : GitLabPageState()
}