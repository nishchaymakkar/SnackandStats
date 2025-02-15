package com.logbook.snackstats

import android.app.Application
import com.logbook.snackstats.data.datastore.dataStoreModule
import com.logbook.snackstats.ui.screens.auth.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SnackStatsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@SnackStatsApplication)
            androidLogger()
            modules(dataStoreModule, viewModelModule)
        }
    }
}