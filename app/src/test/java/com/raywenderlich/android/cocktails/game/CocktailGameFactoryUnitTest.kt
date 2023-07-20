package com.raywenderlich.android.cocktails.game

import com.raywenderlich.android.cocktails.common.network.Cocktail
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository
import com.raywenderlich.android.cocktails.common.repository.RepositoryCallback
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactory
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactoryImpl
import com.raywenderlich.android.cocktails.game.viewmodel.model.Game
import com.raywenderlich.android.cocktails.game.viewmodel.model.Question
import org.junit.Assert
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
        setupRepositoryWithCocktails(repository)
        factory.buildGame(callback)
        verify(callback).onSuccess(any())

    }

    @Test
    fun buildGameShouldCallOnError(){
        val callback = mock<CocktailsGameFactory.Callback>()
        setupRepositoryWithError(repository)
        factory.buildGame(callback)
        verify(callback).onError()

    }

    @Test
    fun buildGameShouldGetHighScoreFromRepo(){
        setupRepositoryWithCocktails(repository)
        factory.buildGame(mock())
        verify(repository).getHighScore()
    }

    @Test
    fun buildGameShouldBuildGameWithHighScore(){
        var highScore = 100
        setupRepositoryWithCocktails(repository)
        whenever(repository.getHighScore()).thenReturn(highScore)
        factory.buildGame(object :CocktailsGameFactory.Callback{
            override fun onSuccess(game: Game)= Assert.assertEquals(highScore,game.score.highest)
            override fun onError()= Assert.fail()

        })

    }

    @Test
    fun buildGameShouldBuildGameWithHQuestions(){
        setupRepositoryWithCocktails(repository)
        factory.buildGame(object :CocktailsGameFactory.Callback{
            override fun onSuccess(game: Game){
                cocktails.forEach {
                    assertQuestion(game.nextQuestion(),it.strDrink,it.strDrinkThumb)
                }
            }
            override fun onError()= Assert.fail()

        })

    }

    private fun assertQuestion(question: Question?, correctOption: String, imageUrl: String) {
        Assert.assertNotNull(question)
        Assert.assertEquals(imageUrl,question?.imageUrl)
        Assert.assertEquals(correctOption,question?.correctOption)
        Assert.assertNotEquals(correctOption,question?.inCorrectOption)
    }


    private fun setupRepositoryWithCocktails(repository: CocktailsRepository) {
        doAnswer {
            val callback : RepositoryCallback<List<Cocktail>,String> = it.getArgument(0)
            callback.onSuccess(cocktails)
        }.whenever(repository).getAlcoholic(any())
    }

    private fun setupRepositoryWithError(repository: CocktailsRepository) {
        doAnswer {
            val callback : RepositoryCallback<List<Cocktail>,String> = it.getArgument(0)
            callback.onError("Error")
        }.whenever(repository).getAlcoholic(any())
    }


}