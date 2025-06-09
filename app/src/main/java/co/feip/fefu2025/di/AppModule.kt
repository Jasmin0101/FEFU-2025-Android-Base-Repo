package co.feip.fefu2025.di


import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.data.RepositoryRepositoryImpl
import co.feip.fefu2025.domain.repository.StarRepository
import co.feip.fefu2025.domain.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetIsStarredUseCase
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.domain.usecase.GetSearchRepositoriesUseCase
import co.feip.fefu2025.domain.usecase.GetStarredRepositoriesUseCase
import co.feip.fefu2025.domain.usecase.GetToggleStarredUseCase
import co.feip.fefu2025.presentation.navigation.DefaultNavigator
import co.feip.fefu2025.presentation.navigation.Destination
import co.feip.fefu2025.presentation.navigation.Navigator
import co.feip.fefu2025.presentation.ui.component.all_projects_list.AllProjectsListViewModel
import co.feip.fefu2025.presentation.ui.component.git_lab_page.GitLabPageViewModel
import co.feip.fefu2025.presentation.ui.component.my_stars_list.MyStarsListViewModel
import co.feip.fefu2025.presentation.ui.component.my_stars_preview.MyStarsPreviewViewModel
import co.feip.fefu2025.presentation.ui.component.search_project_list.SearchProjectListViewModel
import co.feip.fefu2025.presentation.viewmodel.HomePageViewModel
import co.feip.fefu2025.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val navModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.HomePage)
    }

    single<RepositoryRepository> { RepositoryRepositoryImpl() }

    single { StarRepository() }

    factory { GetRepositoriesUseCase(get()) }
    factory { GetStarredRepositoriesUseCase(get()) }
    factory { GetSearchRepositoriesUseCase(get()) }
    factory { GetRepositoryUseCase(get()) }
    factory { GetIsStarredUseCase(get()) }
    factory { GetToggleStarredUseCase(get()) }
}


@RequiresApi(Build.VERSION_CODES.O)
var viewModelModule = module {
    viewModelOf(::HomePageViewModel)
    viewModelOf(::RepositoryViewModel)
    viewModelOf(::AllProjectsListViewModel)
    viewModelOf(::MyStarsPreviewViewModel)
    viewModelOf(::SearchProjectListViewModel)
    viewModelOf(::MyStarsListViewModel)
    viewModelOf(::GitLabPageViewModel)
}




