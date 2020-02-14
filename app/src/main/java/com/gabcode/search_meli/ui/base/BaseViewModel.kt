package com.gabcode.search_meli.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabcode.core.data.remote.Failure

open class BaseViewModel : ViewModel() {

    protected val mLoadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean>
        get() = mLoadingData

    protected val mFailureData = MutableLiveData<Failure>()
    val failureData: LiveData<Failure>
        get() = mFailureData
}