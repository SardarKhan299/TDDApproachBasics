package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*

class GameMockitoTest {

    @Test
    fun whenAnsweringShouldDelegateQuestion(){
       val question = mock<Question>()
       val game = Game(listOf(question),10)
       game.answer(question,"OPTION")
        verify(question, times(1)).answer(eq("OPTION"))

    }

    @Test
    fun whenAnswerCorrectlyShouldIncrementCurrentScore(){
        val question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(true)
        val game = Game(listOf(question),10)
        game.answer(question,"OPTION")
        Assert.assertEquals(1,game.currentScore)
    }

    @Test
    fun whenAnswerInCorrectlyShouldNotIncrementCurrentScore(){
        val question = mock<Question>()
        whenever(question.answer("")).thenReturn(false)
        val game = Game(listOf(question),10)
        game.answer(question,"OPTION")
        Assert.assertEquals(0,game.currentScore)
    }

}