package com.dada.rootnote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
        boardAdapter = BoardAdapter(itemList,
            onItemClick = { clickedItem ->
                // RecyclerView의 항목을 클릭했을 때의 동작
                navigateToWriteActivity(clickedItem)
            },
            onItemLongClick = { longClickedItem ->
                // RecyclerView의 항목을 길게 클릭했을 때의 동작 (삭제 다이얼로그 표시)
                showDeleteConfirmationDialog(longClickedItem)
            }
        )

        binding.rv.adapter = boardAdapter
        binding.rv.layoutManager = LinearLayoutManager(this)

        // ViewModel에서 데이터 변경 감지
        memoViewModel.getAllMemos().observe(this, Observer { memos ->
            itemList.clear()
            memos?.let {
                itemList.addAll(it.map { memo ->
                    BoardItem(memo.id, memo.title, memo.date, memo.content, memo.time)
                })
                boardAdapter.notifyDataSetChanged()
            }
        })

        // 작성 버튼 클릭 시 WriteActivity로 이동
        binding.writeBtn.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    // RecyclerView의 항목을 클릭했을 때의 동작
    private fun navigateToWriteActivity(clickedItem: BoardItem) {
        val intent = Intent(this, WriteActivity::class.java)
        intent.putExtra("memoId", clickedItem.id) // 메모 ID 전달
        intent.putExtra("title", clickedItem.title)
        intent.putExtra("content", clickedItem.content)
        startActivity(intent)
    }

    // 삭제 다이얼로그 표시
    private fun showDeleteConfirmationDialog(item: BoardItem) {
        AlertDialog.Builder(this)
            .setTitle("메모삭제")
            .setMessage("메모를 삭제하시겠습니까?")
            .setPositiveButton("예") { dialog, which ->
                // 삭제 동작 처리
                deleteMemo(item)
            }
            .setNegativeButton("아니오") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    // 메모 삭제 요청
    private fun deleteMemo(item: BoardItem) {
        item.id?.let { memoId ->
            memoViewModel.deleteMemoById(memoId)
        }
    }
}
