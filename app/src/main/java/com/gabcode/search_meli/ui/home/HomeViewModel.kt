package com.gabcode.search_meli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.Paging
import com.gabcode.core.domain.usecase.SearchDataUseCase
import com.gabcode.search_meli.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchDataUseCase: SearchDataUseCase
) : BaseViewModel(){

    private val paging = MutableLiveData<Paging>()

    private val mSearchData = MutableLiveData<SearchResultUi>()
    val searchData: LiveData<SearchResultUi> = mSearchData

    fun fetchItems(query: String) {
        mLoadingData.value = true
        viewModelScope.launch {
            searchDataUseCase.invoke(query).let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.let {
                            paging.value = it.paging
                            mSearchData.value = SearchResultUi(it.paging.total, it.results)
                        }
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

data class SearchResultUi(
    val total: Int,
    val items: List<Item>
)