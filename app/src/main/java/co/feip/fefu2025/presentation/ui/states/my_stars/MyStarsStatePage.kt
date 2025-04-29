package co.feip.fefu2025.presentation.ui.states.home_page

import co.feip.fefu2025.domain.model.Repository

sealed class MyStarsStatesPage {
    object Loading : MyStarsStatesPage()
    object Loaded : MyStarsStatesPage()
    object Error : MyStarsStatesPage()
}