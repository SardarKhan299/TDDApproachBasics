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
    fun whenAnswerInCorrectlyThreeTimesShouldIncrementSequence(){
        val question = mock<Question>()
        val score = mock<Score>()
        whenever(question.answer("")).thenReturn(false)
        val game = Game(listOf(question),score)
        game.answer(question,"OPTION")
        game.answer(question,"OPTION")
        game.answer(question,"OPTION")
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

}