package co.feip.fefu2025.di

import co.feip.fefu2025.data.repository.RepositoryRepository
import co.feip.fefu2025.domain.usecase.GetRepositoriesUseCase
import co.feip.fefu2025.domain.usecase.GetRepositoryUseCase
import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
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

val useCaseModule = module {

}

