package com.raywenderlich.android.cocktails.game.viewmodel.model

class Question(val correctOption: String, val inCorrectOption: String,val imageUrl:String?=null) {

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

    fun getOptions(sort:(List<String>)->List<String> = {it.shuffled()}): List<String> {
        return sort(listOf(correctOption,inCorrectOption))
    }


}
