package com.gabcode.search_meli.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.extension.injector
import javax.inject.Inject

class ItemDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ItemDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        injector.inject(this)
    }
}
