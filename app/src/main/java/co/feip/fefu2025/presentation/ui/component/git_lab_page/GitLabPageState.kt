package co.feip.fefu2025.presentation.ui.component.git_lab_page

import co.feip.fefu2025.domain.model.RepositoryModel


sealed class GitLabPageState {
    data object Loading : GitLabPageState()
    data class Loaded(val repositoryModel: RepositoryModel, val isStared: Boolean) :
        GitLabPageState()

    data object Error : GitLabPageState()
}