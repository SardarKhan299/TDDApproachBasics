package com.raywenderlich.android.cocktails.game.repository

import android.content.SharedPreferences
import com.raywenderlich.android.cocktails.common.network.CocktailsApi
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class RepositoryUnitTest {

    @Mock
    lateinit var api: CocktailsApi
    @Mock
    lateinit var sharePreferencesEditor:SharedPreferences.Editor
    @Mock
    lateinit var sharedPreferences: SharedPreferences
    lateinit var repository: CocktailsRepositoryImpl

    @Before
    fun setup(){
        whenever(sharedPreferences.edit()).thenReturn(sharePreferencesEditor)
        repository = CocktailsRepositoryImpl(api,sharedPreferences)
    }

    @Test
    fun saveScoreShouldSaveToSharedPreferences(){
        var score = 100
        repository.saveHighScore(score)

        inOrder(sharePreferencesEditor){
            verify(sharePreferencesEditor).putInt(any(), eq(score))
            verify(sharePreferencesEditor).apply()
        }
    }

    @Test
    fun getScoreShouldGetFromSharedPreferences(){
        repository.getHighScore()
        verify(sharedPreferences).getInt(any(),any())
    }

    @Test
    fun saveScoreShouldNotSaveToPreferenceWhenLower(){
        val previouslySaveHighScore = 100
        val newHighScore = 10
        val spyRepository = spy(repository)
        doReturn(previouslySaveHighScore).whenever(spyRepository).getHighScore()
        spyRepository.saveHighScore(newHighScore)
        verify(sharePreferencesEditor, never()).putInt(any(),eq(newHighScore))
    }

}