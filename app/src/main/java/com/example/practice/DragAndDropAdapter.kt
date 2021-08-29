package com.example.practice

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_drag_and_drop.view.*
import java.util.*

class DragAndDropAdapter(private val startDragListener: OnStartDragListener) :
    RecyclerView.Adapter<DragAndDropAdapter.ItemViewHolder>(),
    ItemMoveCallBackListener.Listener {
    private var users = emptyList<String>().toMutableList()
    fun setUsers(newUsers: List<String>) {
        users.addAll(newUsers)
    }
    override fun getItemCount(): Int {
        return users.size
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.itemView.aaa.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.startDragListener.onStartDrag(holder)
            }
            return@setOnTouchListener true
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_drag_and_drop, parent, false)
        return ItemViewHolder(itemView)
    }
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.textView.text = text
        }
    }
    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(users, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(users, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }
    override fun onRowSelected(itemViewHolder: ItemViewHolder) {
    }

    override fun onRowClear(itemViewHolder: ItemViewHolder) {
    }
}