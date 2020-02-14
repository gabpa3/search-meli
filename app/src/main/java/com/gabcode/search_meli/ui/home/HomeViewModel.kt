package com.gabcode.search_meli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabcode.core.data.remote.Failure
import com.gabcode.core.data.remote.Result
import com.gabcode.core.domain.model.Item
import com.gabcode.core.domain.model.Paging
import com.gabcode.core.domain.usecase.SearchDataUseCase
import com.gabcode.search_meli.ui.base.BaseViewModel
import com.gabcode.search_meli.ui.data.model.SearchResultUi
import com.gabcode.search_meli.ui.util.RetryListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchDataUseCase: SearchDataUseCase
) : BaseViewModel(), RetryListener {

    private val paging = MutableLiveData<Paging>()

    private val mSearchData = MutableLiveData<SearchResultUi>()
    val searchData: LiveData<SearchResultUi> get() = mSearchData

    private val mLoadingPagingData = MutableLiveData<Boolean>()
    val loadingPagingData: LiveData<Boolean> get() = mLoadingPagingData

    private val mNewDataPage = MutableLiveData<List<Item>>()
    val newDataPage get() = mNewDataPage

    private var searchResultUi: SearchResultUi? = null

    // Save last query in case of network failure
    private var lastQuery: String? = null

    fun fetchItems(query: String) {
        mLoadingData.value = true
        viewModelScope.launch {
            searchDataUseCase.invoke(query).let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.let {
                            if (it.results.isEmpty()) {
                                mLoadingData.value = false
                                mFailureData.value = Failure.NoFoundDataFailure
                                return@launch
                            }

                            paging.value = it.paging
                            searchResultUi = SearchResultUi(
                                it.paging.total,
                                it.paging.offset,
                                it.query,
                                it.results.toMutableList()
                            )
                            mSearchData.value = searchResultUi
                        }
                    }
                    is Result.Error -> {
                        if (result.failure is Failure.NetworkFailure) this@HomeViewModel.lastQuery = query
                        mFailureData.value = result.failure
                    }
                }
            }
            mLoadingData.value = false
        }
    }

    fun fetchMoreItems() {
        searchResultUi?.let { searchResultUi ->
            mLoadingPagingData.value = true
            viewModelScope.launch {
                searchDataUseCase.invoke(searchResultUi.query, searchResultUi.offset + 1).let { result ->
                    when (result) {
                        is Result.Success -> {
                            result.data.let {
                                paging.value = it.paging
                                searchResultUi.offset = it.paging.offset
                                searchResultUi.items.addAll(it.results)
                                mNewDataPage.value = it.results
                            }
                        }
                        is Result.Error -> {
                            mFailureData.value = result.failure
                        }
                    }
                }
                mLoadingPagingData.value = false
            }
        }
    }

    override fun retryLastRequest() {
        if (searchResultUi == null && lastQuery != null)
            fetchItems(lastQuery!!)
        else
            fetchMoreItems()
    }
}
