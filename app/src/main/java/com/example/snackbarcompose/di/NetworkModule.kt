package com.example.snackbarcompose.di

import android.content.Context
import androidx.room.Room
import com.example.snackbarcompose.data.SnackBarDatabase
import com.example.snackbarcompose.data.mock.FlowMockApi
import com.example.snackbarcompose.data.mock.mockApi
import com.example.snackbarcompose.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): FlowMockApi = mockApi(context)
}