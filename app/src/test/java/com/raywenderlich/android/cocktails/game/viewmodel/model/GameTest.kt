package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock


class GameTest(){



    @Test
    fun whenCreatingGameItShouldHaveAListofQuestions(){
        val score = mock<Score>()
        val question = Question("Correct","Incorrect")
        val game = Game(listOf(question),score)
        Assert.assertSame(question,game.nextQuestion())

    }

    @Test
    fun whenGettingNextQuestionIfReachEndReturnNull(){
        val question = Question("Correct","Incorrect")
        val score = mock<Score>()
        val game = Game(listOf(question),score)
        game.nextQuestion()
        game.nextQuestion()
        Assert.assertNull("Question is not null",game.nextQuestion())

    }



}