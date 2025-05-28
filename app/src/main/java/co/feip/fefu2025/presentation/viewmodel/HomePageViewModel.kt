package co.feip.fefu2025.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class  HomePageViewModel(
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

    fun navigateHome(){
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.HomePage
            )
        }
    }

    fun navigateSearchScreen(){
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.SearchScreenPage
            )
        }
    }
}
