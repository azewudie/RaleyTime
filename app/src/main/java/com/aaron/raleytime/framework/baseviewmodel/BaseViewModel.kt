package com.aaron.raleytime.framework.baseviewmodel

import androidx.lifecycle.ViewModel
import com.aaron.raleytime.repository.DataRepository
import com.aaron.raleytime.utlits.helper.StoreHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val dataRepository: DataRepository,
): ViewModel(){
    val dataStoreHelper by lazy {
       StoreHelper(dataRepository.getAppDataStore())
    }
    fun getDataRepository():DataRepository{
        return dataRepository
    }

}