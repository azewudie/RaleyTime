package com.aaron.raleytime.repository

import com.aaron.raleytime.data.local.PreferenceDataStoreAPI

abstract class DataRepository {
    abstract fun getAppDataStore(): PreferenceDataStoreAPI
}