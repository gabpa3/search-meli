package com.gabcode.search_meli.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabcode.core.data.remote.Failure
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.usecase.GetItemDataUseCase
import com.gabcode.search_meli.ui.base.BaseViewModel
import com.gabcode.core.data.remote.Result
import com.gabcode.search_meli.ui.util.RetryListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemDetailViewModel @Inject constructor(
    private val getItemDataUseCase: GetItemDataUseCase
) : BaseViewModel(), RetryListener {

    private val mItemResult = MutableLiveData<Item>()
    val itemResult: LiveData<Item> get() = mItemResult

    // Save itemId in case of network failure
    private var itemId: String? = null

    fun fetchItemInfo(id: String) {
        mLoadingData.value = true
        viewModelScope.launch {
            getItemDataUseCase.invoke(id).let { result ->
                when (result) {
                    is Result.Success -> {
                        mItemResult.value = result.data
                    }
                    is Result.Error -> {
                        if (result.failure is Failure.NetworkFailure) this@ItemDetailViewModel.itemId = id
                        mFailureData.value = result.failure
                    }
                }
            }
            mLoadingData.value = false
        }
    }

    override fun retryLastRequest() {
        itemId?.let { fetchItemInfo(it) }
    }

}