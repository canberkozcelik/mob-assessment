package com.mobiquity.assessment.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.assessment.network.data.CategoryResponse
import com.mobiquity.assessment.ui.adapter.CategoryListAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("adapterCategoryList")
    fun bindAdapterCategoryList(view: RecyclerView, categoryList: List<CategoryResponse>?) {
        if (categoryList.isNullOrEmpty()) {
            return
        }
        (view.adapter as? CategoryListAdapter)?.addCategoryList(categoryList)
    }
}
