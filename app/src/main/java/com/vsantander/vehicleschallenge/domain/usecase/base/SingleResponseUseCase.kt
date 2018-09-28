package com.vsantander.vehicleschallenge.domain.usecase.base

import io.reactivex.Single

abstract class SingleResponseUseCase<T>
    : RxUseCase<RxUseCase.NoRequestValues, Single<T>>()