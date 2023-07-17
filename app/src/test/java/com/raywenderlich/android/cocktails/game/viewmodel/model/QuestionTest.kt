package com.raywenderlich.android.cocktails.game.viewmodel.model

import org.junit.Assert
import org.junit.Test

class QuestionTest {

    @Test
    fun whenCreatingQuestionShouldNotHaveAnsweredOption(){
        val question = Question("Correct","Incorrect")
        Assert.assertNull(question.answeredOption)
    }

}