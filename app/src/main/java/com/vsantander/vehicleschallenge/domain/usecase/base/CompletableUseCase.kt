package com.vsantander.vehicleschallenge.domain.usecase.base

import io.reactivex.Completable

abstract class CompletableUseCase<in T> :
        RxUseCase<T, Completable>()