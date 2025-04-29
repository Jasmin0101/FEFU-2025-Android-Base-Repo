package co.feip.fefu2025.presentation.ui.states.home_page

import co.feip.fefu2025.domain.model.Repository

sealed class MyStarsStatePage {
    object Loading : MyStarsStatePage()
    object Loaded : MyStarsStatePage()
    object Error : MyStarsStatePage()
}