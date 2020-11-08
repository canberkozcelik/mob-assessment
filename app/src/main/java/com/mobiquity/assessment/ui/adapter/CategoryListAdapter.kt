package com.mobiquity.assessment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.assessment.R
import com.mobiquity.assessment.databinding.ItemCategoryBinding
import com.mobiquity.assessment.network.data.CategoryResponse

class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    private var items: MutableList<CategoryResponse> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCategoryBinding>(
            inflater,
            R.layout.item_category,
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = items[position]
        item.let {
            holder.binding.apply {
                category = it
                it.products?.let { products ->
                    adapter = ProductListAdapter(products)
                }
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addCategoryList(categoryList: List<CategoryResponse>) {
        items = categoryList.toMutableList()
        notifyDataSetChanged()
        // if there would be pagination
        // val previous = items.size
        // items.addAll(categoryList)
        // notifyItemRangeChanged(previous, categoryList.size)
    }

    class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}
