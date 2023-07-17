package com.raywenderlich.android.cocktails.game.viewmodel.model

import android.util.Log

class Game(val questions: List<Question>, highScore: Int) {

    var score =0
        private set
    var hightScore = highScore
        private set

    private var questionIndex = -1

    fun incrementScore() {
        score++
        if(score>hightScore){
            hightScore++
        }
    }

    fun nextQuestion(): Question? {
        if(questionIndex+1 <questions.size) {
            questionIndex++
            return questions[questionIndex]
        }else{
            return null
        }
    }

}