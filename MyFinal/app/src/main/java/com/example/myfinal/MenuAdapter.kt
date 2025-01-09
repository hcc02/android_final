package com.example.myfinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private val items: List<MenuItem>,
    private val onItemCountChanged: (MenuItem, Int) -> Unit
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.itemImage)
        val itemName: TextView = view.findViewById(R.id.itemName)
        val itemPrice: TextView = view.findViewById(R.id.itemPrice)
        val itemDescription: TextView = view.findViewById(R.id.itemDescription)
        val countText: TextView = view.findViewById(R.id.countText)
        val addButton: ImageButton = view.findViewById(R.id.addButton)
        val minusButton: ImageButton = view.findViewById(R.id.minusButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        var count = 0

        holder.apply {
            itemImage.setImageResource(item.imageResId)
            itemName.text = item.name
            itemPrice.text = "$${item.price}"
            itemDescription.text = item.description
            countText.text = "0"

            addButton.setOnClickListener {
                count++
                countText.text = count.toString()
                onItemCountChanged(item, 1)
            }

            minusButton.setOnClickListener {
                if (count > 0) {
                    count--
                    countText.text = count.toString()
                    onItemCountChanged(item, -1)
                }
            }
        }
    }

    override fun getItemCount() = items.size
}