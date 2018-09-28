package com.vsantander.vehicleschallenge.domain.usecase.base

import io.reactivex.Single

abstract class SingleUseCase<in Params, T>
    : RxUseCase<Params, Single<T>>()