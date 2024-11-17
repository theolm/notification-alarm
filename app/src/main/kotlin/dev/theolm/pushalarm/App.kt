package dev.theolm.pushalarm

import android.app.Application
import dev.theolm.pushalarm.di.appModule
import dev.theolm.pushalarm.di.dataModule
import dev.theolm.pushalarm.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule + dataModule + domainModule)
        }
    }
}