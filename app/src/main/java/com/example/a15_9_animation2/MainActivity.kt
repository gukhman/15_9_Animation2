package com.example.a15_9_animation2

import android.os.Bundle

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWindowInsets(R.id.main)
        setupToolbar(R.id.toolbar, false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProductListFragment())
                .commit()
        }
    }

    fun showCartFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CartFragment())
            .addToBackStack(null)
            .commit()
    }

    fun showReceiptFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ReceiptFragment())
            .addToBackStack(null)
            .commit()
    }
}