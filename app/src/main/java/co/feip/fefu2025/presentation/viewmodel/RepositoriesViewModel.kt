package co.feip.fefu2025.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.model.Repository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.presentation.ui.states.home_page.HomePageState
import co.feip.fefu2025.presentation.ui.states.home_page.MyStarsStatesPage
import co.feip.fefu2025.presentation.ui.states.search_page.SearchScreenPageState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class  RepositoriesViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _repositories = mutableStateOf<List<Repository>>(emptyList())
    val repositories: State<List<Repository>> = _repositories
    var  allRepositories : List<Repository> = emptyList();

    var homePageState by mutableStateOf<HomePageState>(HomePageState.Loading)
        private set
    var searchScreenPageState by mutableStateOf<SearchScreenPageState>(SearchScreenPageState.Loading)
        private set
    var myStarsPageState by mutableStateOf<MyStarsStatesPage>(MyStarsStatesPage.Loading)
        private set

    private val _searchResults = mutableStateOf<List<Repository>>(emptyList())
    val searchResults: State<List<Repository>> = _searchResults
    var isSearching by mutableStateOf(false)
        private set

    init {
        loadRepositories()
        viewModelScope.launch {
            allRepositories = getRepositoriesUseCase.execute()
        }
    }


    fun searchRepositories(query: String) {
        viewModelScope.launch {
            isSearching = true
            delay(500)

            val trimmedQuery = query.trim()
            if (trimmedQuery.isBlank()) {
                _searchResults.value = emptyList()
                isSearching = false
                return@launch
            }

            _searchResults.value = allRepositories.filter {
                it.repositoryName.contains(trimmedQuery, ignoreCase = true)
            }

            isSearching = false
        }
    }

    private fun loadRepositories() {
        viewModelScope.launch {
            try {

                if (Random.nextBoolean()) {
                    _repositories.value = getRepositoriesUseCase.execute()
                    homePageState = HomePageState.Loaded
                    myStarsPageState = MyStarsStatesPage.Loaded
                } else {
                    throw Exception("Random error occurred")
                }
            } catch (e: Exception) {
                homePageState = HomePageState.Error
                myStarsPageState = MyStarsStatesPage.Error
            }
        }
    }


    fun retry() {

        myStarsPageState = MyStarsStatesPage.Loading
        homePageState = HomePageState.Loading
        loadRepositories()
    }
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
