package com.raywenderlich.android.cocktails.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository
import com.raywenderlich.android.cocktails.factory.CocktailsGameFactory
import com.raywenderlich.android.cocktails.game.viewmodel.model.Game
import com.raywenderlich.android.cocktails.game.viewmodel.model.Question
import com.raywenderlich.android.cocktails.game.viewmodel.model.Score

class CocktailsGameViewModel(val repository: CocktailsRepository, val factory: CocktailsGameFactory):ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Boolean>()
    private val questionLiveData = MutableLiveData<Question>()
    private val scoreLiveData = MutableLiveData<Score>()
    fun getLoading(): LiveData<Boolean> = loadingLiveData
    fun getError(): LiveData<Boolean> = errorLiveData
    fun getQuestion(): LiveData<Question> = questionLiveData
    fun getScore(): LiveData<Score> = scoreLiveData
    private var game: Game? = null
    fun initGame() {
        loadingLiveData.value = true
        errorLiveData.value = false
        factory.buildGame(object : CocktailsGameFactory.Callback{
            override fun onSuccess(game: Game) {
                loadingLiveData.value = false
                errorLiveData.value = false
                scoreLiveData.value = game.score
                this@CocktailsGameViewModel.game = game
                nextQuestion()
            }

            override fun onError() {
                loadingLiveData.value = false
                errorLiveData.value = true
            }

        })
    }

    fun nextQuestion() {
        game?.let{
            questionLiveData.value = it.nextQuestion()
        }
    }

    fun answerQuestion(question: Question, option: String) {
        game?.let {
            it.answer(question,option)
            repository.saveHighScore(it.score.highest)
            scoreLiveData.value = it.score
            questionLiveData.value = question
        }
    }


}
