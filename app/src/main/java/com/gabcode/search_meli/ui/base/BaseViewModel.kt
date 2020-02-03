package com.gabcode.search_meli.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val mLoadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean>
        get() = mLoadingData

    protected val mFailureMessage = MutableLiveData<String>()
    val failureMessage: LiveData<String>
        get() = mFailureMessage
}