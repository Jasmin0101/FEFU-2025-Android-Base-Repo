package co.feip.fefu2025.presentation.ui.states.home_page

import co.feip.fefu2025.domain.model.Repository

sealed class HomePageState {
    object Loading : HomePageState()
    object Loaded : HomePageState()
    object Error : HomePageState()
}