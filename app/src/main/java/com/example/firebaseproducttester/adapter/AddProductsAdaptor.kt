package com.example.firebaseproducttester.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.models.ProductsModel

class AddProductsAdaptor(private val productList: ArrayList<ProductsModel>): RecyclerView.Adapter<AddProductsAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_layout_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddProductsAdaptor.ViewHolder, position: Int){
        val currentPoduct = productList[position]
        holder.tvProductname.text = currentPoduct.productName
    }
    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvProductname: TextView = itemView.findViewById(R.id.textView_Name)
    }

}