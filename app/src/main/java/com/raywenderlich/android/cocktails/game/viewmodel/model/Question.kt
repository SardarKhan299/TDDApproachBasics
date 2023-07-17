package com.raywenderlich.android.cocktails.game.viewmodel.model

class Question(private val correctOption: String, val inCorrectOption: String) {

    val isAnsweredCorrectly:Boolean
        get() = correctOption == answeredOption
    var answeredOption: String? = null
        private set

    fun answer(option: String): Boolean {
        if(option!=correctOption && option!=inCorrectOption)
            throw java.lang.IllegalArgumentException("Invalid Option")
        answeredOption = option
        return isAnsweredCorrectly
    }


}
