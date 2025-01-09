package com.example.myfinal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var cartTextView: TextView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private var totalPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化視圖
        menuRecyclerView = findViewById(R.id.menuRecyclerView)
        cartTextView = findViewById(R.id.cartTextView)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)
        checkoutButton = findViewById(R.id.checkoutButton)

        // 設置菜單列表
        val menuItems = listOf(
            MenuItem("三明治", 50, "超級無敵必殺三明治", R.drawable.sandwich),
            MenuItem("吐司", 40, "普通吐司我不愛", R.drawable.toast),
            MenuItem("鐵板麵", 60, "小孩愛吃已購買", R.drawable.noodle),
            MenuItem("蛋餅", 35, "就是蛋餅", R.drawable.egg),
            MenuItem("奶茶", 15, "早餐店奶茶你懂的", R.drawable.milk_tea)
        )

        val adapter = MenuAdapter(menuItems) { item, count ->
            updateCart(item, count)
        }

        menuRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        // 結帳按鈕點擊事件
        checkoutButton.setOnClickListener {
            if (totalPrice > 0) {
                // TODO: 實現結帳流程
            }
        }
    }

    private fun updateCart(item: MenuItem, count: Int) {
        totalPrice += item.price * count
        totalPriceTextView.text = "總計: $${totalPrice}"

        // 更新購物車顯示
        val currentCart = cartTextView.text.toString()
        cartTextView.text = if (count > 0) {
            "$currentCart\n${item.name} x $count"
        } else {
            currentCart.replace("${item.name} x ${-count}\n", "")
        }
    }
}