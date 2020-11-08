package com.mobiquity.assessment.ui.product

import android.os.Bundle
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.mobiquity.assessment.network.data.CategoryResponse

class ProductDetailFragment : SuperBottomSheetFragment() {

    companion object {

        fun newInstance(item: CategoryResponse.Product): ProductDetailFragment {
            val args = Bundle()
            args.putParcelable("PRODUCT", item)
            val fragment = ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
