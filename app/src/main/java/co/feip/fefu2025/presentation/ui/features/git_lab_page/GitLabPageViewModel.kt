package co.feip.fefu2025.presentation.ui.features.git_lab_page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.RepositoryModel
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.presentation.ui.features.git_lab_page.GitLabPageState.Loaded
import co.feip.fefu2025.presentation.ui.features.git_lab_page.GitLabPageState.Loading
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel

class GitLabPageViewModel(
    private val getRepositoryUseCase: GetRepositoryUseCase,
    private val navigator: Navigator

) : ViewModel() {
    private val perPage = 10
    var state by mutableStateOf<GitLabPageState>(GitLabPageState.Loading)
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun refresh(id: Int) {
        state = Loading
        try {
            val result = getRepositoryUseCase.execute(id)
            var isStared = getRepositoryUseCase.executeIsStared(id, this.viewModelScope)
            if (result != null) {
                state = Loaded(result, isStared)
            }
        } catch (e: Exception) {
            state = GitLabPageState.Error

        }
    }

    fun navigateHome(){
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.HomePage
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun toggleStarred(id : Int , repositoryModel: RepositoryModel , isStarred : Boolean){
         val scope = this.viewModelScope
         this.viewModelScope.launch {
             val successToggle = getRepositoryUseCase.executeToggleStared(id , repositoryModel ,isStarred , scope, )

             if (successToggle){
                 state  = Loaded(repositoryModel, isStarred)
             }
         }
    }

}
