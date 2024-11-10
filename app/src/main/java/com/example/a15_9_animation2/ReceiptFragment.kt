package com.example.a15_9_animation2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ReceiptFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_receipt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receiptTextView = view.findViewById<TextView>(R.id.receiptTextView)
        val products = Cart.getProducts()
        val receiptText = StringBuilder()

        products.forEach { product ->
            receiptText.append("${product.name}: ${product.price}\n")
        }
        receiptText.append("Total: ${products.sumOf { it.price }}")

        receiptTextView.text = receiptText.toString()
    }
}