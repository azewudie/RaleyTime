package com.aaron.raleytime.data.local


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = MainPreferenceDataStore.MAIN_DATASTORE,
)

class MainPreferenceDataStore(
    context: Context,
) : PreferenceDataStoreAPI {
    private val dataSource = context.dataStore

    companion object {
        const val MAIN_DATASTORE = "booking_datastore"
    }

    override suspend fun <T> getPreference(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): T = dataSource.data.first()[key] ?: defaultValue

    override suspend fun <T> putPreference(
        key: Preferences.Key<T>,
        value: T,
    ) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataSource.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun <T> clearAllPreference() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }
}
