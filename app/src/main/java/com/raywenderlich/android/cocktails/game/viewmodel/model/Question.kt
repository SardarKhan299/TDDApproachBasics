package com.raywenderlich.android.cocktails.game.viewmodel.model

class Question(correctOption: String, inCorrectOption: String) {
    fun answer(option: String) {
        answeredOption = option
    }

    var answeredOption: String? = null
        private set
}
