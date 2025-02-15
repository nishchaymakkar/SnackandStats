package com.logbook.snackstats.data.datastore

import android.content.Context
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore(name = "user_credentials")

// Repository for preferences access
class DataStoreRepository ( context: Context) {

    private val dataStore = context.dataStore

    companion object {

        val USER_NAME = stringPreferencesKey("user_name")
        val USER_AGE = stringPreferencesKey("user_age")
    }

    // Retrieve session token
    val userName: Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_NAME]
    }


    val userAge: Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_AGE]
    }
    // Save session token
    suspend fun saveUserCredentials(userName: String, userAge: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
            preferences[USER_AGE] = userAge
        }
    }
    suspend fun clearCredentials() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


}


val dataStoreModule = module {
    single { DataStoreRepository(get()) }
}