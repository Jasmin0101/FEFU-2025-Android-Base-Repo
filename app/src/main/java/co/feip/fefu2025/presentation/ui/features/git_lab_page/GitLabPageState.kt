package co.feip.fefu2025.presentation.ui.features.git_lab_page

import co.feip.fefu2025.domain.model.RepositoryModel


sealed class GitLabPageState {
    object Loading : GitLabPageState()
    data class Loaded(val repositoryModel: RepositoryModel, val isStared: Boolean ) : GitLabPageState()
    object Error : GitLabPageState()
}