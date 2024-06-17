package com.dada.rootnote

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dada.rootnote.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var boardAdapter: BoardAdapter
    private lateinit var memoViewModel: MemoViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        memoViewModel = MemoViewModel(application)

        val itemList = ArrayList<BoardItem>()
        boardAdapter = BoardAdapter(itemList)
        binding.rv.adapter = boardAdapter
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.writeBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }

        memoViewModel.getAllMemos().observe(this, Observer { memos ->
            itemList.clear()
            memos?.let {
                for (memo in it) {
                    itemList.add(BoardItem(memo.title, memo.date, memo.content))
                }
                boardAdapter.notifyDataSetChanged()
            }
        })
    }
}
