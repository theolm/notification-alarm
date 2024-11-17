package dev.theolm.pushalarm.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dev.theolm.pushalarm.datasource.DataStoreFileName
import dev.theolm.pushalarm.models.AppLocalData
import dev.theolm.pushalarm.serializer.AppLocalDataSerializer
import org.koin.dsl.module

val dataModule = module {
    single<DataStore<AppLocalData>> {
        DataStoreFactory.create(
            serializer = AppLocalDataSerializer,
            produceFile = { get<Context>().dataStoreFile(DataStoreFileName) },
        )
    }
}