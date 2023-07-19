package com.raywenderlich.android.cocktails.game

import com.raywenderlich.android.cocktails.common.network.Cocktail
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository
import com.raywenderlich.android.cocktails.common.repository.RepositoryCallback
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactory
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactoryImpl
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class CocktailGameFactoryUnitTest {
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory

    private val cocktails = listOf(
        Cocktail("1", "Drink1", "image1"),
        Cocktail("2", "Drink2", "image2"),
        Cocktail("3", "Drink3", "image3"),
        Cocktail("4", "Drink4", "image4")
    )


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

    @Test
    fun buildGameShouldCallOnSuccess(){
        val callback = mock<CocktailsGameFactory.Callback>()
        setupRepositoryWithCoctails(repository)
        factory.buildGame(callback)
        verify(callback).onSuccess(any())

    }

    private fun setupRepositoryWithCoctails(repository: CocktailsRepository) {
        doAnswer {
            val callback : RepositoryCallback<List<Cocktail>,String> = it.getArgument(0)
            callback.onSuccess(cocktails)
        }.whenever(repository).getAlcoholic(any())
    }


}