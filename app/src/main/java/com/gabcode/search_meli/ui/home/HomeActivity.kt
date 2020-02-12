package com.gabcode.search_meli.ui.home

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gabcode.core.domain.model.Item
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.Constants
import com.gabcode.search_meli.ui.data.model.SearchResultUi
import com.gabcode.search_meli.ui.detail.ItemDetailActivity
import com.gabcode.search_meli.ui.extension.injector
import com.gabcode.search_meli.ui.search.SearchActivity
import com.gabcode.search_meli.ui.search.SearchResultAdapter
import com.gabcode.search_meli.ui.util.ItemListener
import com.gabcode.search_meli.ui.util.SpaceItemDecorator
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), ItemListener<Item> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: HomeViewModel

    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        injector.inject(this)

        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        setupObservers()

        setupRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        toolbar.inflateMenu(R.menu.menu_home)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                navigateToSearchActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_CODE_SEARCH &&
            resultCode == Activity.RESULT_OK && data != null) {

            with(data) {
                extras?.let {
                    val query = it.getString(Constants.QUERY_KEY)
                    if (query != null) viewModel.fetchItems(query)
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.searchData.observe(this, Observer { showData(it) })
        viewModel.loadingData.observe(this, Observer { loadingData(it) })

        viewModel.newDataPage.observe(this, Observer { addNextPage(it) })
        viewModel.loadingPagingData.observe(this, Observer { loadingPagingData(it) })
    }

    private fun setupRecycler() {
        searchResultRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecorator())
            addOnScrollListener(object: EndlessPagingScrollListener(layoutManager as StaggeredGridLayoutManager){
                override fun loadMore() {
                    viewModel.fetchMoreItems()
                }

                override fun isLoading(): Boolean {
                    return progressPagingLayout.visibility == View.VISIBLE
                }
            })
        }
    }

    private fun showData(result: SearchResultUi) {
        group.visibility = View.VISIBLE
        searchResultAdapter = SearchResultAdapter(result.items, this)
        val total = result.total.toString()
        totalTx.text = String.format(resources.getString(R.string.home_total_result), total)
        searchResultRecyclerView.adapter = searchResultAdapter
    }

    private fun addNextPage(newItems: List<Item>) {
        searchResultAdapter.addItems(newItems)
    }

    private fun navigateToSearchActivity() {
        val searchMenuView = toolbar.findViewById<View>(R.id.menu_search)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this@HomeActivity, searchMenuView, getString(R.string.transition_search_back)
        ).toBundle()
        startActivityForResult(
            Intent(this@HomeActivity, SearchActivity::class.java),
            Constants.REQUEST_CODE_SEARCH, options)
    }

    override fun onItemClick(data: Item) {
        val intent = Intent(this@HomeActivity, ItemDetailActivity::class.java)
        intent.putExtra(Constants.ITEM_ID_KEY, data.id)
        startActivity(intent)
    }

    private fun loadingData(value: Boolean) {
        landingLayout.visibility = View.GONE
        progressLayout.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun loadingPagingData(value: Boolean) {
        progressPagingLayout.visibility = if (value) View.VISIBLE else View.GONE
    }

}
