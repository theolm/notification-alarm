package dev.theolm.pushalarm.di

import dev.theolm.pushalarm.alarm.AlarmCore
import dev.theolm.pushalarm.alarm.AlarmCoreImpl
import org.koin.dsl.module

val appModule = module {
    single<AlarmCore> {
        AlarmCoreImpl(
            applicationContext = get()
        )
    }
}