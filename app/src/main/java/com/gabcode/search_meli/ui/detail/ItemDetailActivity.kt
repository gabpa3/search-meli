package com.gabcode.search_meli.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabcode.core.domain.model.Item
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.Constants
import com.gabcode.search_meli.ui.extension.injector
import kotlinx.android.synthetic.main.activity_item_detail.*
import javax.inject.Inject

class ItemDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ItemDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        injector.inject(this)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        viewModel =  ViewModelProvider(this, viewModelFactory)[ItemDetailViewModel::class.java]
        viewModel.itemResult.observe(this, Observer { showData(it) })
        viewModel.loadingData.observe(this, Observer { loadingData(it) })

        val itemId = intent.extras?.getString(Constants.ITEM_ID_KEY)
        itemId?.let {
            viewModel.fetchItemInfo(it)
        }
    }

    private fun showData(item: Item) {
        viewPager.adapter = PictureAdapter(item.pictures)
        indicator.setViewPager(viewPager)
        itemConditionTx.text = item.condition.capitalize()
        itemTitleTx.text =  item.title
        itemPriceTx.text = String.format(getString(R.string.price_format),
            item.currency, item.price.toString())
        itemStockTx.text = getString(if (item.availableQuantity > 0)
            R.string.detail_stock_available else R.string.detail_stock_no_available)
    }

    private fun loadingData(value: Boolean) {
        progressLayout.visibility = if (value) View.VISIBLE else View.GONE
    }

}
