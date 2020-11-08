package com.mobiquity.assessment.network.data

import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

class CategoriesResponse : ArrayList<CategoriesResponse.CategoriesResponseItem>() {
    @SuppressLint("ParcelCreator")
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class CategoriesResponseItem(
        @Json(name = "description")
        val description: String?,
        @Json(name = "id")
        val id: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "products")
        val products: List<Product?>?
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Product(
            @Json(name = "categoryId")
            val categoryId: String?,
            @Json(name = "description")
            val description: String?,
            @Json(name = "id")
            val id: String?,
            @Json(name = "name")
            val name: String?,
            @Json(name = "salePrice")
            val salePrice: SalePrice?,
            @Json(name = "url")
            val url: String?
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            @JsonClass(generateAdapter = true)
            data class SalePrice(
                @Json(name = "amount")
                val amount: String?,
                @Json(name = "currency")
                val currency: String?
            ) : Parcelable
        }
    }
}