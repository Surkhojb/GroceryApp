package com.surkhojb.groceryapp.feature.common.base

sealed class Response<out T>{
    data class success<out T>(val data: T?): Response<T>()
    data class error<out T>(val data: T?,val errorMessage: String?): Response<T>()
    object loading: Response<Nothing>()
}