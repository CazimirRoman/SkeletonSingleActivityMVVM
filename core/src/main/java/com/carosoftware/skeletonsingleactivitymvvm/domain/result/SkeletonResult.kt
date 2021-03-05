package com.carosoftware.skeletonsingleactivitymvvm.domain.result

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class SkeletonResult<out R> {

    data class Success<out T>(val data: T) : SkeletonResult<T>()
    data class Error(val exception: Exception) : SkeletonResult<Nothing>()
    object Loading : SkeletonResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [SkeletonResult] is of type [Success] & holds non-null [Success.data].
 */
val SkeletonResult<*>.succeeded
    get() = this is com.carosoftware.skeletonsingleactivitymvvm.domain.result.SkeletonResult.Success && data != null