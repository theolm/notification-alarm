package dev.theolm.pushalarm.di

import android.media.MediaPlayer
import dev.theolm.pushalarm.alarm.AlarmCore
import dev.theolm.pushalarm.alarm.AlarmCoreImpl
import dev.theolm.pushalarm.ui.screens.home.HomeScreenViewModel
import org.koin.dsl.module

val appModule = module {
    single { MediaPlayer() }
    single<AlarmCore> {
        AlarmCoreImpl(
            applicationContext = get(),
            mediaPlayer = get()
        )
    }

    factory {
        HomeScreenViewModel()
    }
}