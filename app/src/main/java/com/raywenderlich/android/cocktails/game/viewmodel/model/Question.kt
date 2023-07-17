package com.raywenderlich.android.cocktails.game.viewmodel.model

class Question(correctOption: String, inCorrectOption: String) {



    var answeredOption: String? = null
        private set

    fun answer(option: String): Boolean {
        answeredOption = option
        return true
    }
}
