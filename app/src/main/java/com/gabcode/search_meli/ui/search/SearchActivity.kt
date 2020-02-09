package com.gabcode.search_meli.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gabcode.core.domain.model.Item
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.Constants
import com.gabcode.search_meli.ui.ItemListener
import com.gabcode.search_meli.ui.detail.ItemDetailActivity
import com.gabcode.search_meli.ui.extension.injector
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), ItemListener<Item> {

    private val TAG: String = SearchActivity::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SearchViewModel

    private lateinit var searchRecentAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        injector.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        viewModel.searchData.observe(this, Observer { showData(it) })

        startSearch()

//        setupRecycler()
    }

    private fun setupRecycler() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        searchResultRecyclerView.apply {
            this.layoutManager = layoutManager
//            addOnScrollListener(EndlessPagingScrollListener(layoutManager))
        }
    }

    private fun showData(items: List<Item>) {
        searchRecentAdapter = SearchResultAdapter(items, this)
        searchResultRecyclerView.adapter = searchRecentAdapter
    }

    private fun startSearch() {
        viewModel.fetchItems("zapatos nike")
    }

    override fun onItemClick(data: Item) {
        val intent = Intent(this@SearchActivity, ItemDetailActivity::class.java)
        intent.putExtra(Constants.ITEM_ID_KEY, data.id)
        startActivity(intent)
    }


}
