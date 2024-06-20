package com.dada.rootnote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

        // 감정 상태에 따라 이미지 설정
        item.emotion?.let { emotion ->
            when (emotion) {
                1 -> holder.emotionImage.setImageResource(R.drawable.angry)
                2 -> holder.emotionImage.setImageResource(R.drawable.normal)
                3 -> holder.emotionImage.setImageResource(R.drawable.happy)
                4 -> holder.emotionImage.setImageResource(R.drawable.bad)
                5 -> holder.emotionImage.setImageResource(R.drawable.sad)
                else -> {
                    // 기본적으로 설정할 이미지가 있는 경우
                    holder.emotionImage.setImageResource(R.drawable.blank)
                }
            }
        }

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
        val emotionImage: ImageView = itemView.findViewById(R.id.item_emotion)
    }


}
