package co.feip.fefu2025.di

import co.feip.fefu2025.navigation.DefaultNavigator
import co.feip.fefu2025.navigation.Destination
import co.feip.fefu2025.navigation.Navigator
import org.koin.dsl.module

val navModule = module{
    single<Navigator>{
        DefaultNavigator(startDestination = Destination.HomePage)
    }
}