package com.raywenderlich.android.cocktails.game

import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactory
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CocktailGameFactoryUnitTest {
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory

    @Before
    fun setup(){
        repository = mock()
        factory = CocktailsGameFactoryImpl(repository)
    }

    @Test
    fun buildGameShouldGetCocktailFromRepo(){
        factory.buildGame(mock())
        verify(repository).getAlcoholic(any())
    }

}