package com.dada.rootnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.dada.rootnote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val itemList = ArrayList<BoardItem>()
        val boardAdapter = BoardAdapter(itemList)
        val db = AppDatabase.getDatabase(applicationContext)

        // 데이터베이스에서 Memo 데이터를 가져오기 위한 DAO 인스턴스
        val memoDao = db?.MemoDAO()

        // Memo 데이터를 가져와서 itemList에 추가
        memoDao?.let {
            val memos: List<Memo> = it.getAllMemos() // MemoDao에서 모든 Memo 데이터를 가져옴

            for (memo in memos) {
                itemList.add(BoardItem(memo.title, memo.date, memo.content))
            }
        }


        boardAdapter.notifyDataSetChanged()

        binding.rv.adapter = boardAdapter
        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.writeBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }
}