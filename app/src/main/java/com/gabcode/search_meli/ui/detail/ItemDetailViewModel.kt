package com.gabcode.search_meli.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.usecase.GetItemDataUseCase
import com.gabcode.search_meli.ui.base.BaseViewModel
import com.gabcode.core.data.remote.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemDetailViewModel @Inject constructor(
    private val getItemDataUseCase: GetItemDataUseCase
) : BaseViewModel() {

    val itemResult = MutableLiveData<Item>()

    fun fetchItemInfo(id: String) {
        mLoadingData.value = true
        viewModelScope.launch {
            getItemDataUseCase.invoke(id).let { result ->
                when (result) {
                    is Result.Success -> {
                        itemResult.value = result.data
                    }
                    is Result.Error -> {
                        mFailureMessage.value = result.message
                    }
                }
            }
            mLoadingData.value = false
        }

    }

}