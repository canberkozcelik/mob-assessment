package com.mobiquity.assessment

import com.mobiquity.assessment.network.data.CategoryResponse


object MockUtil {

    fun mockCategories() = listOf(mockCategory())

    fun mockCategory() = CategoryResponse(
        id = "36802",
        name = "Food",
        description = "",
        products = mockProducts()
    )

    fun mockProducts() = listOf(mockProduct())

    fun mockProduct() = CategoryResponse.Product(
        id = "1",
        categoryId = "36802",
        name = "Bread",
        url = "/Bread.jpg",
        description = "",
        salePrice = mockSalePrice()
    )

    fun mockSalePrice() = CategoryResponse.Product.SalePrice(
        amount = "0.81",
        currency = "EUR"
    )
}