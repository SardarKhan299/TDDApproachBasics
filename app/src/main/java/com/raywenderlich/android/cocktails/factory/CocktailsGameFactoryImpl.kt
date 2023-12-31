package com.raywenderlich.android.cocktails.factory

import com.raywenderlich.android.cocktails.common.network.Cocktail
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository
import com.raywenderlich.android.cocktails.common.repository.RepositoryCallback
import com.raywenderlich.android.cocktails.game.viewmodel.model.Game
import com.raywenderlich.android.cocktails.game.viewmodel.model.Question
import com.raywenderlich.android.cocktails.game.viewmodel.model.Score

class CocktailsGameFactoryImpl (private val repository: CocktailsRepository): CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.Callback) {
        repository.getAlcoholic(object : RepositoryCallback<List<Cocktail>,String>{
            override fun onSuccess(cocktaiList: List<Cocktail>) {
                val questions = buildQuestions(cocktaiList)
                val score = Score(repository.getHighScore())
                val game = Game(questions,score)
                callback.onSuccess(game)
            }

            override fun onError(e: String) {
                callback.onError()
            }

        })
    }

    private fun buildQuestions(cocktaiList: List<Cocktail>): List<Question> {
        return cocktaiList.map { coctail->
            val otherCocktail = cocktaiList.shuffled().first{it != coctail}
            Question(coctail.strDrink,otherCocktail.strDrink,coctail.strDrinkThumb)
        }
    }
}