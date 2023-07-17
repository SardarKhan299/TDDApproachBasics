package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Test


class GameTest(){

    @Test
    fun whenIncrementScoreShouldIncrementHighScore(){
        val game = Game()
        game.incrementScore()
        if(game.hightScore==1){
            print("Success")
        }else{
            throw Exception("Score and High Score Doesn't Match...")
        }
    }

}