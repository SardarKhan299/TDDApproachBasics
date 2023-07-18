package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GameMockitoTest {

    @Test
    fun whenAnsweringShouldDelegateQuestion(){
       val question = mock<Question>()
       val game = Game(listOf(question),10)
       game.answer(question,"OPTION")
        verify(question, times(1)).answer(eq("OPTION"))

    }
}