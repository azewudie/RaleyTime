package com.aaron.raleytime.data.local

import androidx.datastore.preferences.core.Preferences

interface PreferenceDataStoreAPI {

    suspend fun <T> getPreference(
        key: Preferences.Key<T>,
        defaultValue: T,
    ):T

    suspend fun <T> putPreference(
        key: Preferences.Key<T>,
        value: T
    )

    suspend fun <T> removePreference(key: Preferences.Key<T>)

    suspend fun <T> clearAllPreference()

}