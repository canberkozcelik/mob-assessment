package com.mobiquity.assessment.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.mobiquity.assessment.R
import com.mobiquity.assessment.databinding.FragmentProductDetailBinding
import com.mobiquity.assessment.extension.screenHeight
import com.mobiquity.assessment.network.data.CategoryResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : SuperBottomSheetFragment() {

    lateinit var binding: FragmentProductDetailBinding

    companion object {

        fun newInstance(item: CategoryResponse.Product): ProductDetailFragment {
            val args = Bundle()
            args.putParcelable("PRODUCT", item)
            val fragment = ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_detail,
            container,
            false
        )
        binding.apply {
            lifecycleOwner = this@ProductDetailFragment
            product = arguments?.getParcelable("PRODUCT") as? CategoryResponse.Product
        }

        return binding.root
    }

    override fun getPeekHeight(): Int {
        return context.screenHeight / 2
    }
}
