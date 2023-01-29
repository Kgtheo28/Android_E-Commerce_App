package com.example.firebaseproducttester.models

data class ProductsModel (
    var key: String?=null,
    var title: String?=null,
    var description: String?=null,
    var image: String?=null,
    var price: String?=null,

    val productName: String?= null,
    val sellingPrice: Int?= null,
    val shippingCost: Int?= null,

)