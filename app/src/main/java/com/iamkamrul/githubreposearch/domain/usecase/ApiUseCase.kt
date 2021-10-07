package com.iamkamrul.githubreposearch.domain.usecase

import androidx.lifecycle.LiveData


interface ApiUseCase<Params, Type> : UseCase {
    fun execute(params: Params? = null): LiveData<Type>
}