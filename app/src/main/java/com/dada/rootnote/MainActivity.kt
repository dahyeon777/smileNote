package com.dada.rootnote


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dada.rootnote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var boardAdapter: BoardAdapter
    private lateinit var memoViewModel: MemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ViewModel 초기화
        memoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)

        // RecyclerView 설정
        val itemList = ArrayList<BoardItem>()
        boardAdapter = BoardAdapter(itemList) { clickedItem ->
            // RecyclerView의 항목을 클릭했을 때의 동작
            val intent = Intent(this, WriteActivity::class.java)
            intent.putExtra("memoId", clickedItem.id) // 메모 ID 전달
            intent.putExtra("title", clickedItem.title)
            intent.putExtra("content", clickedItem.content)
            startActivity(intent)
        }
        binding.rv.adapter = boardAdapter
        binding.rv.layoutManager = LinearLayoutManager(this)

        // ViewModel에서 데이터 변경 감지
        memoViewModel.getAllMemos().observe(this, Observer { memos ->
            itemList.clear()
            memos?.let {
                for (memo in it) {
                    itemList.add(BoardItem(memo.id, memo.title, memo.date, memo.content, memo.time)) // ID 추가
                }
                boardAdapter.notifyDataSetChanged()
            }
        })

        // 작성 버튼 클릭 시 WriteActivity로 이동
        binding.writeBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }
}
