package com.raywenderlich.android.cocktails.game.viewmodel.model

import android.util.Log

class Game(val questions: List<Question>, highScore: Int) {



    private var score  = Score(highScore)

    val currentScore :Int
        get() = score.current

    val highestScore :Int
        get()= score.highest

    private var questionIndex = -1

    fun incrementScore() {
        score.incrementScore()
    }

    fun nextQuestion(): Question? {
        if(questionIndex+1 <questions.size) {
            questionIndex++
            return questions[questionIndex]
        }else{
            return null
        }
    }

    fun answer(question: Question, option: String) {
        val result = question.answer(option)
        if(result)
            score.incrementScore()
    }

}