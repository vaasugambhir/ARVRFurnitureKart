package com.vaasugambhir.furniturekart.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vaasugambhir.furniturekart.R
import com.vaasugambhir.furniturekart.data.room.ProductEntity
import com.vaasugambhir.furniturekart.ui.interfaces.OnProductClickListener

class ProductAdapter(private val context: Context, private val isOrders: Boolean) :
    ListAdapter<ProductEntity, ProductAdapter.ProductItemHolder>(DIFF_CALLBACK) {

    private lateinit var listener: OnProductClickListener

    fun setOnClickListeners(l: OnProductClickListener) {
        listener = l
    }

    inner class ProductItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindOrders(product: ProductEntity) {
            val image: ImageView = itemView.findViewById(R.id.itemOrder_image)
            val name: TextView = itemView.findViewById(R.id.itemOrder_name)
            val delete: Button = itemView.findViewById(R.id.itemOrder_delete)

            image.setImageResource(product.imageRaw)
            name.text = product.name
            delete.setOnClickListener {
                listener.onDeleteClick(product)
            }
        }

        fun bindItems(product: ProductEntity) {
            val image: ImageView = itemView.findViewById(R.id.itemProduct_image)
            val name: TextView = itemView.findViewById(R.id.itemProduct_name)
            val order: Button = itemView.findViewById(R.id.itemProduct_order)
            val bg: LinearLayout = itemView.findViewById(R.id.LL_background)

            image.setImageResource(product.imageRaw)
            name.text = product.name
            order.setOnClickListener { listener.onOrderClick(product) }
            bg.setOnClickListener { listener.onProductClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemHolder =
        ProductItemHolder(
            LayoutInflater.from(context)
                .inflate(getItemXML(), parent, false)
        )

    private fun getItemXML(): Int {
        return if (isOrders) R.layout.item_order
        else R.layout.item_product
    }

    override fun onBindViewHolder(holder: ProductItemHolder, position: Int) {
        if (isOrders) holder.bindOrders(getItem(position))
        else holder.bindItems(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ProductEntity> =
            object : DiffUtil.ItemCallback<ProductEntity>() {
                override fun areItemsTheSame(
                    oldItem: ProductEntity,
                    newItem: ProductEntity
                ): Boolean {
                    return oldItem.key == newItem.key
                }

                override fun areContentsTheSame(
                    oldItem: ProductEntity,
                    newItem: ProductEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}