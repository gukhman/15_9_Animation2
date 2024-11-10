package com.example.a15_9_animation2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ProductListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = listOf(
            Product("Видеокарта NVIDIA GeForce RTX 5090", 15700.0, R.drawable.rtx_5090),
            Product("Процессор Ryzen Threadripper PRO 7995WX", 12200.0, R.drawable.threadripper_pro),
        )

        val adapter = ProductAdapter(products) { product ->
            showAddToCartDialog(product)
        }

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        view.findViewById<FloatingActionButton>(R.id.fabCart).setOnClickListener {
            (activity as MainActivity).showCartFragment()
        }
    }

    private fun showAddToCartDialog(product: Product) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Добавить в корзину?")
            .setMessage("Вы хотите добавить ${product.name} в корзину?")
            .setPositiveButton("В корзину") { _, _ ->
                Cart.addProduct(product)
                Snackbar.make(requireView(), "${product.name} добавлен в корзину", Snackbar.LENGTH_SHORT).show()
            }
            .setNegativeButton("Отмена", null)
            .create()
        dialog.show()
    }
}