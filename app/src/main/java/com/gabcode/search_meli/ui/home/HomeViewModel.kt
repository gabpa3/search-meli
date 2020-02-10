package com.gabcode.search_meli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.Paging
import com.gabcode.core.domain.usecase.SearchDataUseCase
import com.gabcode.search_meli.ui.base.BaseViewModel
import com.gabcode.search_meli.ui.data.model.SearchResultUi
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchDataUseCase: SearchDataUseCase
) : BaseViewModel(){

    private val paging = MutableLiveData<Paging>()

    private val mSearchData = MutableLiveData<SearchResultUi>()
    val searchData: LiveData<SearchResultUi> = mSearchData

    private val mLoadingPagingData = MutableLiveData<Boolean>()
    val loadingPagingData: LiveData<Boolean> = mLoadingPagingData

    fun fetchItems(query: String) {
        mLoadingData.value = true
        viewModelScope.launch {
            searchDataUseCase.invoke(query).let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.let {
                            paging.value = it.paging
                            mSearchData.value = SearchResultUi(it.paging.total, it.paging.offset, it.query, it.results.toMutableList())
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

    fun fetchMoreItems() {
        mSearchData.value?.let { searchResultUi ->
            mLoadingPagingData.value = true
            val offset = searchResultUi.offset + 1
            viewModelScope.launch {
                searchDataUseCase.invoke(searchResultUi.query, offset).let { result ->
                    when (result) {
                        is Result.Success -> {
                            result.data.let {
                                paging.value = it.paging
                                mSearchData.value = SearchResultUi(it.paging.total, it.paging.offset, it.query, addNextElements(it.results, searchResultUi.items))
                            }
                        }
                        is Result.Error -> {
                            mFailureMessage.value = result.message
                        }
                    }
                }
                mLoadingPagingData.value = false
            }
        }
    }

    private fun addNextElements(elements: List<Item>, actualItems: MutableList<Item>): MutableList<Item> {
        actualItems.addAll(elements)
        return actualItems
    }

}
