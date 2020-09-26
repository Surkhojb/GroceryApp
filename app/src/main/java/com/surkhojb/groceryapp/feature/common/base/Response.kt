package com.surkhojb.groceryapp.feature.common.base

data class Response<out T>(val data: T?, val errorMessage: String? = null ){
    companion object {
        fun <T> success(data: T?,errorMessage: String? = null): Response<T> {
            return Response(data,errorMessage)
        }

        fun <T> error(data: T? = null, message: String): Response<T> {
            return Response(data, message)
        }
    }
}