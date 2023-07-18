package com.raywenderlich.android.cocktails.game.viewmodel.model

class Score(highScore:Int) {
    var current = 0
        private set
    var highest = highScore
        private set
    fun incrementScore(){
        current++
        if(current>highest){
            highest = current
        }
    }
}