package com.gabcode.search_meli.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gabcode.core.data.remote.Failure
import com.gabcode.core.extension.toast
import com.gabcode.search_meli.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    protected fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.NetworkFailure -> handleVisibleNetworkFailureView()
            is Failure.ServerFailure -> handleVisibleServerFailureView(failure.message)
            is Failure.GenericFailure -> handleVisibleGenericFailure()
            is Failure.NoFoundDataFailure -> handleVisibleNoDataFoundView()
        }
    }

    abstract fun setupObservers()

    abstract fun loadingData(value: Boolean)

    abstract fun handleVisibleNetworkFailureView()

    abstract fun handleVisibleNoDataFoundView()

    abstract fun hideAllSupportView()

    private fun handleVisibleServerFailureView(message: String?) {
        toast(message ?: getString(R.string.error_server))
    }

    private fun handleVisibleGenericFailure() {
        toast(R.string.error_generic)
    }

    internal fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}