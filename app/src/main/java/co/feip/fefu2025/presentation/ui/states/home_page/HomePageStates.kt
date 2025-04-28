package co.feip.fefu2025.presentation.ui.states.home_page

import co.feip.fefu2025.domain.model.Repository

sealed  class HomePageStates {
    object  Loading: HomePageStates()
    data class Success(val data: List<Repository> ): HomePageStates()
    object Error: HomePageStates()

}