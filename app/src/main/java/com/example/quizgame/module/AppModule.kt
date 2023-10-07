package com.example.quizgame.module

import android.app.Application
import android.content.Context
import com.example.data.QuizRepositoryMock
import com.example.domain.repository.QuizRepository
import com.example.domain.usecase.QuizUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideRepository(context: Context):QuizRepository{
        return QuizRepositoryMock(context)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: QuizRepository):QuizUseCase{
        return QuizUseCase(repository)
    }
}