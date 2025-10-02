package com.aaron.raleytime.utlits.helper

import com.aaron.raleytime.data.local.PreferenceDataStoreAPI
import com.aaron.raleytime.data.local.PreferenceDataStoreKey
import javax.inject.Inject

class StoreHelper
@Inject
constructor(
    private val dataStore: PreferenceDataStoreAPI
) {
    suspend fun updateFirstTimeUser(isFirstTime: Boolean) {
        dataStore.putPreference(PreferenceDataStoreKey.IS_FIRST_TIME_USER, isFirstTime)
    }
    suspend fun getFirstTimeUser(): Boolean {
        return dataStore.getPreference(
            PreferenceDataStoreKey.IS_FIRST_TIME_USER, false
        )
    }
}