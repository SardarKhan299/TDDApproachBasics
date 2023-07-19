package com.raywenderlich.android.cocktails.factory

import com.raywenderlich.android.cocktails.common.repository.CocktailsRepository

class CocktailsGameFactoryImpl (private val repository: CocktailsRepository): CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.Callback) {

    }
}