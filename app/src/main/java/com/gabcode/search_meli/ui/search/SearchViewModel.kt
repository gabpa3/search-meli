package com.gabcode.search_meli.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabcode.core.domain.usecase.SearchDataUseCase
import com.gabcode.search_meli.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.Paging
import javax.inject.Inject

class SearchViewModel
    @Inject constructor(private val searchDataUseCase: SearchDataUseCase
) : BaseViewModel(){

    val paging = MutableLiveData<Paging>()
    val searchData = MutableLiveData<List<Item>>()

    fun fetchItems(query: String) {
        mLoadingData.value = true
        viewModelScope.launch {
            val result = searchDataUseCase.invoke(query)
            when (result) {
                is Result.Success -> {
                    result.data.let {
                        paging.value = it.paging
                        searchData.value = it.results
                    }
                }
                is Result.Error -> {
                    mFailureMessage.value = result.message
                }
            }
            mLoadingData.value = false
        }
    }



}