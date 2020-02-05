package com.gabcode.search_meli.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabcode.core.domain.model.Item
import com.gabcode.search_meli.R
import com.gabcode.search_meli.di.viewmodel.ViewModelProviderFactory
import com.gabcode.search_meli.ui.extension.injector
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    private val TAG: String = SearchActivity::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        injector.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        viewModel.searchData.observe(this@SearchActivity, Observer { showData(it) })

        startSearch()
    }

    private fun showData(items: List<Item>) {
        Log.d(TAG, items[0].title)
        Log.d(TAG, items[1].title)
    }

    private fun startSearch() {
        viewModel.fetchItems("zapatos nike")
    }
}
