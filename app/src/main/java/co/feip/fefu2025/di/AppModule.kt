package co.feip.fefu2025.di

import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.presentation.viewmodel.HomePageViewModel
import co.feip.fefu2025.presentation.viewmodel.RepositoryViewModel
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import co.feip.fefu2025.presentation.ui.features.all_projects_list.AllProjectsListViewModel
import co.feip.fefu2025.presentation.ui.features.my_stars_preview.MyStarsPreviewViewModel
import co.feip.fefu2025.presentation.ui.features.search_project_list.SearchProjectListViewModel
import co.feip.fefu2025.presentation.ui.features.my_stars_list.MyStarsListViewModel


import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val navModule = module{
    single<Navigator>{
        DefaultNavigator(startDestination = Destination.HomePage)
    }

    single { RepositoryRepository() }

    factory { GetRepositoriesUseCase(get()) }
    factory { GetRepositoryUseCase(get()) }
    single { RepositoryRepository() }

    factory { GetRepositoriesUseCase(get()) }
    factory { GetRepositoryUseCase(get()) }

}


@RequiresApi(Build.VERSION_CODES.O)
var viewModelModule = module{
    viewModelOf(::HomePageViewModel)
    viewModelOf(::RepositoryViewModel)
    viewModelOf(::AllProjectsListViewModel)
    viewModelOf(::MyStarsPreviewViewModel)
    viewModelOf(::SearchProjectListViewModel)
    viewModelOf(::MyStarsListViewModel)
}


val useCaseModule = module {

}

