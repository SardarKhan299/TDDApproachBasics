package com.raywenderlich.android.cocktails.game.viewmodel.model

import android.util.Log

class Game(questions: List<Question>, highScore: Int) {

    var score =0
        private set
    var hightScore = highScore
        private set

    fun incrementScore() {
        score++
        if(score>hightScore){
            hightScore++
        }
    }

    fun nextQuestion() {

    }

}