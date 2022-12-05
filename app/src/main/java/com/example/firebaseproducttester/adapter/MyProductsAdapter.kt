package com.example.firebaseproducttester.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.models.ProductsModel
import com.example.firebaseproducttester.ui.activities.ui.fragments.OrdersFragment

class MyProductsAdapter(
    val context: Context, private val list: ArrayList<ProductsModel>
): RecyclerView.Adapter<MyProductsAdapter.MyProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProductsViewHolder {
        return MyProductsViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.shopping_cart,parent,false))
    }

    override fun onBindViewHolder(holder: MyProductsViewHolder, position: Int) {
        val ProductsModel = list[position]
        Glide.with(context)
            .load(list[position].image)
            .into(holder.imageView)
        holder.txtName.text = ProductsModel.title
        holder.txtDescription.text = ProductsModel.description
        holder.txtPrice.text = ProductsModel.price
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageView:ImageView = itemView.findViewById(R.id.imageView_cart)
        var txtName:TextView = itemView.findViewById(R.id.textView_cart_title)
        var txtDescription:TextView =itemView.findViewById(R.id.textView_cart_description)
        var txtPrice:TextView = itemView.findViewById(R.id.textView_cart_price)


    }

}
