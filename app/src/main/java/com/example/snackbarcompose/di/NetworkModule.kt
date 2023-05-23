package com.example.snackbarcompose.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideAppDatabase(@ApplicationContext context: Context): FlowMockApi = mockApi(context)
}