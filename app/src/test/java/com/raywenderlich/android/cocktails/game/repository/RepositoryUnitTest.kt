package com.raywenderlich.android.cocktails.game.repository

import android.content.SharedPreferences
import com.raywenderlich.android.cocktails.common.network.CocktailsApi
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class RepositoryUnitTest {

    lateinit var api: CocktailsApi
    lateinit var sharePreferencesEditor:SharedPreferences.Editor
    lateinit var sharedPreferences: SharedPreferences
    lateinit var repository: CocktailsRepositoryImpl

    @Before
    fun setup(){
        api = mock()
        sharePreferencesEditor = mock()
        sharedPreferences = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharePreferencesEditor)
        repository = CocktailsRepositoryImpl(api,sharedPreferences)
    }

    @Test
    fun saveScoreShoudSaveToSharedPreferences(){
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

}