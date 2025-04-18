package co.feip.fefu2025.presentation

import android.app.Application
import co.feip.fefu2025.di.navModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidContext(this@App)
            androidLogger()

            modules(navModule)
        }
    }

}