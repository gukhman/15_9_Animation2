package com.example.a15_9_animation2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CartFragment : Fragment() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var productList: MutableList<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setupToolbar(R.id.toolbar, true)
        }
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем список товаров из корзины
        productList = Cart.getProducts().toMutableList() // Используем изменяемый список

        // Инициализируем адаптер
        cartAdapter = CartAdapter(productList) { product ->
            // Обрабатываем удаление товара из корзины
            removeProductFromCart(product)
        }

        // Настроим RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cartAdapter

        // Обработка кнопки перехода к чеку
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            (activity as MainActivity).showReceiptFragment()
        }
    }

    private fun removeProductFromCart(product: Product) {
        // Удаляем товар из корзины
        Cart.removeProduct(product)

        // Обновляем RecyclerView
        cartAdapter.removeItem(product)
    }
}