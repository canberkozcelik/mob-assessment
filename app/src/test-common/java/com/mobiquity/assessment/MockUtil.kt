package com.mobiquity.assessment

import com.mobiquity.assessment.network.data.CategoryResponse


object MockUtil {

    fun mockCategories() = listOf(mockCategory())

    private fun mockCategory() = CategoryResponse(
        id = "36802",
        name = "Food",
        description = "",
        products = mockProducts()
    )

    private fun mockProducts() = listOf(mockProduct())

    private fun mockProduct() = CategoryResponse.Product(
        id = "1",
        categoryId = "36802",
        name = "Bread",
        url = "/Bread.jpg",
        description = "",
        salePrice = mockSalePrice()
    )

    private fun mockSalePrice() = CategoryResponse.Product.SalePrice(
        amount = "0.81",
        currency = "EUR"
    )
}