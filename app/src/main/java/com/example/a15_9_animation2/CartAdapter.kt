package com.example.a15_9_animation2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val products: MutableList<Product>,
    private val onDeleteClicked: (Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productImage: ImageView = itemView.findViewById(R.id.productImage)

        fun bind(product: Product) {
            productName.text = product.name
            productPrice.text = "${product.price} ₽"
            productImage.setImageResource(product.imageRes)

            itemView.setOnClickListener {
                // При нажатии на элемент вызываем диалог
                showDeleteDialog(product)
            }
        }

        private fun showDeleteDialog(product: Product) {
            val dialog = AlertDialog.Builder(itemView.context)
                .setTitle("Удалить из корзины?")
                .setMessage("Вы уверены, что хотите удалить этот товар?")
                .setPositiveButton("Удалить") { _, _ ->
                    // Удаляем товар из корзины
                    onDeleteClicked(product)
                }
                .setNegativeButton("Отмена", null)
                .create()
            dialog.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun removeItem(product: Product) {
        val position = products.indexOf(product)
        if (position >= 0) {
            products.removeAt(position)
            notifyItemRemoved(position) // Уведомляем адаптер, что элемент был удален
        }
    }
}