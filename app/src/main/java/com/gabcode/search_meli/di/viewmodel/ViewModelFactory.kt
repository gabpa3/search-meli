package com.gabcode.search_meli.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST", "TooGenericExceptionThrown", "SimpleRedundantLet")
@Singleton
class ViewModelProviderFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries
            .firstOrNull { modelClass.isAssignableFrom(it.key) }
            ?.let { it.value }
        ?: throw IllegalArgumentException("$UNKNOWN_MODEL_EXCEPTION $modelClass")

        return creator.get() as? T ?: throw RuntimeException(EXCEPTION_MESSAGE)
    }

    companion object {
        private const val EXCEPTION_MESSAGE = "Can not get view model from factory"
        private const val UNKNOWN_MODEL_EXCEPTION = "Unknown model class"
    }
}