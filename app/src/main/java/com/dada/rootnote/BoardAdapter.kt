package com.dada.rootnote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(
    private val itemList: List<BoardItem>,
    private val onItemClick: (BoardItem) -> Unit,
    private val onItemLongClick: (BoardItem) -> Unit
) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycle_view, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = itemList[position]

        holder.title.text = item.title
        holder.date.text = item.date
        holder.content.text = item.content
        holder.time.text = item.time

        // 짧게 클릭한 경우의 이벤트 처리
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        // 길게 클릭한 경우의 이벤트 처리
        holder.itemView.setOnLongClickListener {
            onItemLongClick(item)
            true // true를 반환하여 이벤트 처리 완료를 나타냄
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_title)
        val date: TextView = itemView.findViewById(R.id.item_date)
        val content: TextView = itemView.findViewById(R.id.item_content)
        val time: TextView = itemView.findViewById(R.id.item_time)
    }
}
