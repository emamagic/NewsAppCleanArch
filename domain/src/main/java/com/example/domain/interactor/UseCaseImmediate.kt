package com.example.domain.interactor

abstract class UseCaseImmediate<out R> {

    protected abstract fun buildUseCaseImmediate() : R

    fun execute(): R = buildUseCaseImmediate()
}