package com.raywenderlich.android.cocktails.game.viewmodel.model

class Game(val questions: List<Question>, val score:Score = Score(0)) {


    private var questionIndex = -1
    var inCorrectAnswerSequence = 0

    fun nextQuestion(): Question? {
        if(questionIndex+1 <questions.size) {
            questionIndex++
            return questions[questionIndex]
        }else{
            return null
        }
    }

    fun answer(question: Question, option: String) {
        val result = question.answer(option)
        if(result) {
            score.incrementScore()
            inCorrectAnswerSequence = 0
        }else{
            inCorrectAnswerSequence++
        }
    }

    fun shouldEndGame():Boolean {
        return inCorrectAnswerSequence >= 3
    }

}