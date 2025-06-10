package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.presentation.navigation.Destination
import co.feip.fefu2025.presentation.navigation.Navigator
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class HomePageViewModel(
    private val navigator: Navigator
) : ViewModel() {


    fun navigateRepository(id: Int) {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.Repository(id),
            )
        }
    }

    fun navigateMyStars() {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.MyStars,
            )
        }
    }

    fun navigateHome() {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.HomePage
            )
        }
    }

    fun navigateSearchScreen() {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.SearchScreenPage
            )
        }
    }
}
