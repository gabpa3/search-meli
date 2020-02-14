package com.gabcode.search_meli.ui.search

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabcode.core.extension.handleVisible
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.Constants
import com.gabcode.search_meli.ui.util.ItemListener
import com.gabcode.search_meli.ui.extension.injector
import com.gabcode.search_meli.ui.util.KeyboardUtil
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), ItemListener<String> {

    private val TAG: String = SearchActivity::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SearchViewModel

    private lateinit var searchRecentAdapter: SearchRecentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        injector.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        viewModel.recentSearchData.observe(this, Observer { showData(it) })

        searchback.setOnClickListener { dismiss() }

        setupSearchView()

        setupRecycler()
    }

    override fun onBackPressed() {
       dismiss()
    }

    private fun dismiss() {
        if (searchView.isFocused) {
            KeyboardUtil.hide(searchView)
            searchView.clearFocus()
        }
        finishAfterTransition()
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        searchView.requestFocus()
        KeyboardUtil.show(searchView)
    }

    private fun setupSearchView() {
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    startSearch(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    private fun setupRecycler() {
        recentSearchRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun startSearch(query: String?) {
        if (TextUtils.isEmpty(query)) return

        val intent = Intent()
        intent.putExtra(Constants.QUERY_KEY, query)
        setResult(Activity.RESULT_OK, intent)
        dismiss()
    }

    private fun showData(recentSearches: List<String>) {
        searchRecentAdapter = SearchRecentAdapter(recentSearches, this)
        recentSearchRecyclerView.run {
            handleVisible(true)
            adapter = searchRecentAdapter
        }
    }

    override fun onItemClick(data: String) {
        searchView.setQuery(data, false)
    }

}
