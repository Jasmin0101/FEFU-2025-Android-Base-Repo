package co.feip.fefu2025.di


import co.feip.fefu2025.presentation.viewmodel.RepositoriesViewModel
import co.feip.fefu2025.presentation.viewmodel.RepositoryViewModel
import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
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


var viewModelModule = module{
    viewModelOf(::RepositoriesViewModel)
    viewModelOf(::RepositoryViewModel)
}


val useCaseModule = module {

}

