package com.raywenderlich.android.cocktails.game.repository

import android.content.SharedPreferences
import com.raywenderlich.android.cocktails.common.network.CocktailsApi
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepositoryImpl
import org.junit.Test
import org.mockito.kotlin.*

class RepositoryUnitTest {

    @Test
    fun saveScoreShoudSaveToSharedPreferences(){
        val api: CocktailsApi = mock()
        val sharePreferencesEditor:SharedPreferences.Editor = mock()
        val sharedPreferences: SharedPreferences = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharePreferencesEditor)
        val repository = CocktailsRepositoryImpl(api,sharedPreferences)
        var score = 100
        repository.saveHighScore(score)

        inOrder(sharePreferencesEditor){
            verify(sharePreferencesEditor).putInt(any(), eq(score))
            verify(sharePreferencesEditor).apply()
        }
    }

}