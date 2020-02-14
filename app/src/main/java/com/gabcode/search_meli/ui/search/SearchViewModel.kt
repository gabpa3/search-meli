package com.gabcode.search_meli.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabcode.core.domain.usecase.SearchDataUseCase
import com.gabcode.search_meli.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.Paging
import com.gabcode.core.domain.usecase.GetRecentSearchDataUseCase
import javax.inject.Inject

class SearchViewModel
    @Inject constructor(private val getRecentSearchDataUseCase: GetRecentSearchDataUseCase
) : BaseViewModel(){

    private val mRecentSearchData = MutableLiveData<List<String>>()
    val recentSearchData: LiveData<List<String>> get() =  mRecentSearchData

    init {
        fetchRecentSearches()
    }

    private fun fetchRecentSearches() {
        mLoadingData.value = true
        viewModelScope.launch {
            getRecentSearchDataUseCase.invoke().let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.let {
                            if (it.values.isEmpty()) {
                                mLoadingData.value = false
                                return@launch
                            }
                            mRecentSearchData.value = it.values.map { item -> item.title }
                        }
                    }
                    is Result.Error -> {
                        mFailureData.value = result.failure
                    }
                }
            }
            mLoadingData.value = false
        }
    }
}