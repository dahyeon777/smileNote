package com.dada.rootnote

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.dada.rootnote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val itemList = ArrayList<BoardItem>()
        val boardAdapter = BoardAdapter(itemList)

        itemList.add(BoardItem("오늘의 일기","12/24","오늘은 수박을 먹었다. 정말 맛있었다."))
        itemList.add(BoardItem("물놀이","01/23","오늘은 수영장에 갔다왔다"))
        itemList.add(BoardItem("놀이공원","01/23","오늘은 놀이공원에 갔다왔다"))
        boardAdapter.notifyDataSetChanged()

        binding.rv.adapter = boardAdapter
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.writeBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }
}