package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*
import kotlin.time.times

class GameMockitoTest {

    @Test
    fun whenAnsweringShouldDelegateQuestion(){
       val question = mock<Question>()
        val score = mock<Score>()
       val game = Game(listOf(question),score)
       game.answer(question,"OPTION")
        verify(question, times(1)).answer(eq("OPTION"))

    }

    @Test
    fun whenAnswerCorrectlyShouldIncrementCurrentScore(){
        val question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(true)
        val score = mock<Score>()
        val game = Game(listOf(question),score)
        game.answer(question,"OPTION")
        verify(score).incrementScore()
    }

    @Test
    fun whenAnswerInCorrectlyShouldNotIncrementCurrentScore(){
        val question = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("")).thenReturn(false)
        val game = Game(listOf(question),score)
        game.answer(question,"OPTION")
        verify(score, never()).incrementScore()
    }

    @Test
    fun whenAnswerInCorrectlyShouldIncrementSequence(){
        val question = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("")).thenReturn(false)
        val game = Game(listOf(question),score)
        game.answer(question,"OPTION")
        Assert.assertEquals(game.inCorrectAnswerSequence,1)
    }


    @Test
    fun whenAnswerCorrectlyShouldIncrementSequence(){
        val question = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("Correct")).thenReturn(true)
        val game = Game(listOf(question),score)
        game.answer(question,"Correct")
        Assert.assertEquals(game.correctAnswerSequence,1)
    }

    @Test
    fun whenAnswerInCorrectlyShouldNotIncrementCorrectSequence(){
        val question = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("")).thenReturn(false)
        val game = Game(listOf(question),score)
        game.answer(question,"Correct")
        Assert.assertEquals(game.correctAnswerSequence,0)
    }

    @Test
    fun whenAnswerInCorrectlyThreeTimesShouldIncrementSequence(){
        val question = mock<Question>()
        val question1 = mock<Question>()
        val question2 = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("INCORRECT_OPTION")).thenReturn(false)
        whenever(question1.answer("INCORRECT_OPTION")).thenReturn(false)
        whenever(question2.answer("INCORRECT_OPTION")).thenReturn(false)
        val game = Game(listOf(question,question1,question2),score)
        game.answer(question,"INCORRECT_OPTION")
        game.answer(question,"INCORRECT_OPTION")
        game.answer(question,"INCORRECT_OPTION")
        Assert.assertEquals(game.inCorrectAnswerSequence,3)
    }


    @Test
    fun whenAnswerInCorrectlyThreeTimesShouldEndGame(){
        val question = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("")).thenReturn(false)
        val game = Game(listOf(question),score)
        game.answer(question,"OPTION")
        game.answer(question,"OPTION")
        game.answer(question,"OPTION")
        Assert.assertTrue(game.shouldEndGame())
    }

    @Test
    fun whenAnswerCorrectlyThreeTimesShouldGiveDoubleScore(){
        val question = mock<Question>()
        val question1 = mock<Question>()
        val question2 = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("CORRECT_OPTION")).thenReturn(true)
        whenever(question1.answer("CORRECT_OPTION")).thenReturn(true)
        whenever(question2.answer("CORRECT_OPTION")).thenReturn(true)
        val game = Game(listOf(question,question1,question2),score)
        game.answer(question,"CORRECT_OPTION")
        game.answer(question,"CORRECT_OPTION")
        game.answer(question,"CORRECT_OPTION")
        Assert.assertTrue(game.shouldGiveDoubleScore())
    }

}