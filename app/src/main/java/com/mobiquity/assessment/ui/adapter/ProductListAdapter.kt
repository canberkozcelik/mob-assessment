package com.mobiquity.assessment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.assessment.R
import com.mobiquity.assessment.databinding.ItemProductBinding
import com.mobiquity.assessment.extension.setSafeOnClickListener
import com.mobiquity.assessment.network.data.CategoryResponse

class ProductListAdapter constructor(
    private val items: List<CategoryResponse.Product?>,
    private val productSelectedListener: OnProductSelectedListener
) :
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
                    productSelectedListener.selected(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnProductSelectedListener {
        fun selected(item: CategoryResponse.Product)
    }
}