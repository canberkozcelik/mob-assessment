package com.mobiquity.assessment.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.mobiquity.assessment.R
import com.mobiquity.assessment.base.DataBindingActivity
import com.mobiquity.assessment.databinding.ActivityMainBinding
import com.mobiquity.assessment.network.data.CategoryResponse
import com.mobiquity.assessment.ui.adapter.CategoryListAdapter
import com.mobiquity.assessment.ui.adapter.ProductListAdapter
import com.mobiquity.assessment.ui.product.ProductDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    @VisibleForTesting val mainViewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = CategoryListAdapter(object: ProductListAdapter.OnProductSelectedListener {
                override fun selected(item: CategoryResponse.Product) {
                    val productDetailFragment = ProductDetailFragment.newInstance(item)
                    productDetailFragment.show(supportFragmentManager, "ProductDetailFragment")
                }
            })
            viewModel = mainViewModel
        }
    }
}