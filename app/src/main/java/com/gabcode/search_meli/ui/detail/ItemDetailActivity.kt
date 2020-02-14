package com.gabcode.search_meli.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabcode.core.domain.model.Item
import com.gabcode.core.extension.handleVisible
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.Constants
import com.gabcode.search_meli.ui.base.BaseActivity
import com.gabcode.search_meli.ui.extension.injector
import com.gabcode.search_meli.ui.util.formatDecimal
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.layout_no_connection.view.*
import javax.inject.Inject

class ItemDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ItemDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        injector.inject(this)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        viewModel = ViewModelProvider(this, viewModelFactory)[ItemDetailViewModel::class.java]

        noConnectionLayout.retryBtn.setOnClickListener { viewModel.retryLastRequest() }

        setupObservers()

        handleExtra()
    }

    override fun setupObservers() {
        viewModel.itemResult.observe(this, Observer { showData(it) })
        viewModel.loadingData.observe(this, Observer { loadingData(it) })
        viewModel.failureData.observe(this, Observer { handleFailure(it) })
    }

    private fun handleExtra() {
        val itemId = intent.extras?.getString(Constants.ITEM_ID_KEY)
        itemId?.let {
            viewModel.fetchItemInfo(it)
        }
    }

    private fun showData(item: Item) {
        group.handleVisible(true)
        viewPager.adapter = PictureAdapter(item.pictures)
        indicator.setViewPager(viewPager)
        itemConditionTx.text = item.condition.capitalize()
        itemTitleTx.text = item.title
        itemPriceTx.text = String.format(
            getString(R.string.price_format),
            item.currency, formatDecimal(item.price)
        )
        itemStockTx.text = getString(
            if (item.availableQuantity > 0) {
                R.string.detail_stock_available
            } else R.string.detail_stock_no_available
        )
    }

    override fun loadingData(value: Boolean) {
        if (value) hideAllSupportView()
        progressLayout.handleVisible(value)
    }

    override fun handleVisibleNetworkFailureView() {
        group.handleVisible(false)
        noConnectionLayout.handleVisible(true)
    }

    override fun handleVisibleNoDataFoundView() {
        group.handleVisible(false)
        notFoundLayout.handleVisible(true)
    }

    override fun hideAllSupportView() {
        noConnectionLayout.handleVisible(false)
        notFoundLayout.handleVisible(false)
    }
}
