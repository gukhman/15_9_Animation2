package com.example.a15_9_animation2

object Cart {
    private val products = mutableListOf<Product>()

    fun getProducts(): MutableList<Product> {
        return products
    }

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(product: Product) {
        products.remove(product)
    }
}