package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test


class GameTest(){


    @Test
    fun whenIncrementingScoreShouldIncrementSCore(){
        // Given
        val game = Game(listOf<Question>(), 10)
        // When
        game.incrementScore()
        // Then
        Assert.assertEquals(1,game.score)
    }

    @Test
    fun whenIncrementScoreShouldIncrementHighScore(){
        val game = Game(listOf<Question>(), 10)
        game.incrementScore()
        if(game.hightScore==1){
            print("Success")
        }else{
            throw Exception("Score and High Score Doesn't Match...")
        }
    }

    @Test
    fun whenIncrementScore_belowHighScore_notIncrementHighScore(){
        val game = Game(listOf<Question>(), 10)
        game.incrementScore()
        assertEquals("High Score is Incrementing",10,game.hightScore)
    }

    @Test
    fun whenCreatingGameItShouldHaveAListofQuestions(){
        val question = Question("Correct","Incorrect")
        val game = Game(listOf(question),10)
        Assert.assertSame(question,game.nextQuestion())

    }

    @Test
    fun whenGettingNextQuestionIfReachEndReturnNull(){
        val question = Question("Correct","Incorrect")
        val game = Game(listOf(question),10)
        game.nextQuestion()
        game.nextQuestion()
        Assert.assertNull("Question is not null",game.nextQuestion())

    }



}