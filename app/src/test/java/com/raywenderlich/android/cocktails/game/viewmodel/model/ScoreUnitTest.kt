package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Test

class ScoreUnitTest {

    @Test
    fun whenIncrementScoreShouldIncrementIt(){
        val score = Score(0)
        score.incrementScore()
        Assert.assertEquals("Current Score should be 1",1,score.current)
    }

    @Test
    fun whenIncrementScoreShouldIncrementHighScore(){
        val score = Score(0)
        score.incrementScore()
        Assert.assertEquals("Current Score should be 1",1,score.highest)
    }

    @Test
    fun whenIncrementScoreBelowHighScoreShouldNotIncrementHighScore(){
        val score = Score(10)
        score.incrementScore()
        Assert.assertEquals("Current Score should be 1",10,score.highest)
    }

}