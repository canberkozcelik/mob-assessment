package com.mobiquity.assessment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.assessment.R
import com.mobiquity.assessment.databinding.ItemProductBinding
import com.mobiquity.assessment.extension.setSafeOnClickListener
import com.mobiquity.assessment.network.data.CategoryResponse
import com.mobiquity.assessment.ui.product.ProductDetailFragment

class ProductListAdapter constructor(private val items: List<CategoryResponse.Product?>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemProductBinding>(
            inflater,
            R.layout.item_product,
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = items[position]
        item?.let {
            holder.binding.apply {
                product = it
                root.setSafeOnClickListener { _ ->
                    val productDetailFragment = ProductDetailFragment.newInstance(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
}