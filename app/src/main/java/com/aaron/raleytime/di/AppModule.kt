package com.aaron.raleytime.di

import android.content.Context
import com.aaron.raleytime.data.local.MainPreferenceDataStore
import com.aaron.raleytime.data.local.PreferenceDataStoreAPI
import com.aaron.raleytime.data.service.SpeechRecognitionRepository
import com.aaron.raleytime.data.service.TextToSpeechRepository
import com.aaron.raleytime.repository.DataRepository
import com.aaron.raleytime.repository.MainDataRepository
import com.aaron.raleytime.repository.SpeechRecognitionRepositoryImpl
import com.aaron.raleytime.repository.TextToSpeechRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun getMainPreferenceDataStore(
        @ApplicationContext context: Context,
    ): PreferenceDataStoreAPI {
        return MainPreferenceDataStore(context)
    }
    @Singleton
    @Provides
    fun provideEmployeeDirectoryDataRepository(
        dataStore: PreferenceDataStoreAPI
    ): DataRepository =
        MainDataRepository(
            dataStore
        )

    @Provides
    @Singleton
    fun provideSpeechRecognitionRepository(
        @ApplicationContext context: Context
    ): SpeechRecognitionRepository {
        return SpeechRecognitionRepositoryImpl(context)
    }


    @Provides
    @Singleton
    fun provideTextToSpeechRepository(
        @ApplicationContext context: Context
    ): TextToSpeechRepository {
        return TextToSpeechRepositoryImpl(context)
    }

}
