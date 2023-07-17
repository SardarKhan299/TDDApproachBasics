package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionTest {

    @Test
    fun whenCreatingQuestionShouldNotHaveAnsweredOption(){
        val question = Question("Correct","Incorrect")
        Assert.assertNull(question.answeredOption)
    }

    @Test
    fun whenAnsweringQuestionShouldHaveAnsweredOption(){
        val question = Question("CORRECT","INCORRECT")
        question.answer("OPTION")
        assertEquals("Answered Option is not There","OPTION",question.answeredOption)
    }

}