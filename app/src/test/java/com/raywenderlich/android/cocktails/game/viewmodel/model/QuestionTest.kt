package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QuestionTest {

    private lateinit var question:Question

    @Before
    fun setup(){
        question = Question("Correct","Incorrect")
    }

    @Test
    fun whenCreatingQuestionShouldNotHaveAnsweredOption(){
        Assert.assertNull(question.answeredOption)
    }

    @Test
    fun whenAnsweringQuestionShouldHaveAnsweredOption(){
        question.answer("Correct")
        assertEquals("Answered Option is not There","Correct",question.answeredOption)
    }

    @Test
    fun whenAnswerWithCorrectOptionShouldReturnTrue(){
        val result = question.answer("Correct")
        assertTrue("Option is incorrect",result)
    }

    @Test
    fun whenAnswerWithInCorrectOptionShouldReturnTrue(){
        val result = question.answer("Incorrect")
        assertFalse("Option is correct",result)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun whenAnswerWithInvalidOptionShouldThrowException(){
        val result = question.answer("Invalid")
    }

    @Test
    fun whenCreatingQuestionShouldReturnOptionWithCustomSort(){
        val options = question.getOptions()
        assertEquals(listOf("Correct","Incorrect"),options)
    }

}