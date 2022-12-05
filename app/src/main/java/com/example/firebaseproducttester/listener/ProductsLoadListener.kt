package com.example.firebaseproducttester.listener

import com.example.firebaseproducttester.models.ProductsModel

interface ProductsLoadListener {

    fun onProductsLoadSuccess(productModelList:List<ProductsModel>)
    fun onProductsLoadFailed(message:String)
}