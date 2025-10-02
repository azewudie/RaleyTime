package com.aaron.raleytime.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceDataStoreKey {
    val IS_FIRST_TIME_USER = booleanPreferencesKey("is_first_time_user")
}