package co.feip.fefu2025.presentation

import android.app.Application
import co.feip.fefu2025.di.navModule
import co.feip.fefu2025.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        try {
            startKoin {
                androidLogger(org.koin.core.logger.Level.DEBUG)
                androidContext(this@App)
                modules(navModule)
                modules(viewModelModule)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}