package com.raywenderlich.android.cocktails.game.viewmodel.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactory
import com.raywenderlich.android.cocktails.game.viewmodel.CocktailsGameViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

class CocktailGameViewModelUnitTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    private lateinit var repository: CocktailsRepository
    private lateinit var factory: CocktailsGameFactory
    private lateinit var viewModel: CocktailsGameViewModel
    private lateinit var game: Game
    private lateinit var loadingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<Boolean>
    private lateinit var scoreObserver: Observer<Score>
    private lateinit var questionObserver: Observer<Question>

    @Before
    fun setup() {
        // 1
        repository = mock()
        factory = mock()
        viewModel = CocktailsGameViewModel(repository, factory)
        // 2
        game = mock()
        // 3
        loadingObserver = mock()
        errorObserver = mock()
        scoreObserver = mock()
        questionObserver = mock()
        viewModel.getLoading().observeForever(loadingObserver)
        viewModel.getScore().observeForever(scoreObserver)
        viewModel.getQuestion().observeForever(questionObserver)
        viewModel.getError().observeForever(errorObserver)
    }

    @Test
    fun initShouldBuildGame(){
        viewModel.initGame()
        verify(factory).buildGame(any())
    }

    @Test
    fun initShouldShowLoading(){
        viewModel.initGame()
        verify(loadingObserver).onChanged(eq(true))
    }

    @Test
    fun initShouldHideError(){
        viewModel.initGame()
        verify(errorObserver).onChanged(eq(false))
    }


    @Test
    fun initShowErrorWhenFactoryReturnError(){
        setUpFactoryWithError()
        viewModel.initGame()
        verify(errorObserver).onChanged(eq(true))
        verify(loadingObserver).onChanged(eq(false))
    }

    @Test
    fun initShouldHideErrorAndLoadingWhenFactoryReturnSuccess(){
        setUpFactoryWithSuccessGame(game)
        viewModel.initGame()
        verify(errorObserver, times(2)).onChanged(eq(false))
        verify(loadingObserver).onChanged(eq(false))
    }

    @Test
    fun initShouldShowScoreWhenFactoryReturnSuccess(){
        val score = mock<Score>()
        whenever(game.score).thenReturn(score)
        setUpFactoryWithSuccessGame(game)
        viewModel.initGame()
        verify(scoreObserver).onChanged(eq(score))
    }

    @Test
    fun initShouldShowFirstQuestionWhenFactoryReturnSuccess(){
        val question = mock<Question>()
        whenever(game.nextQuestion()).thenReturn(question)
        setUpFactoryWithSuccessGame(game)
        viewModel.initGame()
        verify(questionObserver).onChanged(eq(question))
    }

    @Test
    fun nextQuestionShouldShowNextQuestion(){
        val question1 = mock<Question>()
        val question2 = mock<Question>()
        whenever(game.nextQuestion()).thenReturn(question1).thenReturn(question2)
        setUpFactoryWithSuccessGame(game)
        viewModel.initGame()
        viewModel.nextQuestion()
        verify(questionObserver).onChanged(eq(question2))
    }

    private fun setUpFactoryWithSuccessGame(game:Game){
        doAnswer {
            val callback:CocktailsGameFactory.Callback = it.getArgument(0)
            callback.onSuccess(game)
        }.whenever(factory).buildGame(any())
    }

    private fun setUpFactoryWithError(){
        doAnswer {
            val callback:CocktailsGameFactory.Callback = it.getArgument(0)
            callback.onError()
        }.whenever(factory).buildGame(any())
    }

}