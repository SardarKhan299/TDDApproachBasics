package com.raywenderlich.android.cocktails.factory

import com.raywenderlich.android.cocktails.common.network.Cocktail
import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository
import com.raywenderlich.android.cocktails.common.repository.RepositoryCallback

class CocktailsGameFactoryImpl (private val repository: CocktailsRepository): CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.Callback) {
        repository.getAlcoholic(object : RepositoryCallback<List<Cocktail>,String>{
            override fun onSuccess(t: List<Cocktail>) {

            }

            override fun onError(e: String) {

            }

        })
    }
}