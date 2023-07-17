package com.raywenderlich.android.cocktails.game.viewmodel.model

class Game(highScore: Int) {

    var score =0
        private set
    var hightScore = 0
        private set

    fun incrementScore() {
        score++
        if(score>hightScore){
            hightScore++
        }
    }

}