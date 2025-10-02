package com.aaron.raleytime.repository

import com.aaron.raleytime.data.local.PreferenceDataStoreAPI

class MainDataRepository(
    private var dataStore: PreferenceDataStoreAPI
) : DataRepository() {
    override fun getAppDataStore(): PreferenceDataStoreAPI = dataStore

}