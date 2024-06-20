package com.dada.rootnote

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dada.rootnote.databinding.ActivityWriteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

class WriteActivity : AppCompatActivity(), OnEmotionSelectedListener {

    private lateinit var binding: ActivityWriteBinding
    private var memoId: Long = 0
    private var emotion = 1

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        // Intent로부터 데이터 받기
        memoId = intent.getLongExtra("memoId", 0)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        // 받아온 데이터가 있다면 UI에 설정
        title?.let { binding.titleTextView.setText(it) }
        content?.let { binding.contentTextView.setText(it) }

        // 저장 버튼 클릭 시
        binding.saveBtn.setOnClickListener {
            saveOrUpdateMemo()
        }

        // 감정 버튼 클릭 시
        binding.emotionBtn.setOnClickListener {
            val customDialog = CustomDialog("제목", "내용")
            customDialog.setOnEmotionSelectedListener(this) // WriteActivity 자체를 listener로 설정
            customDialog.show(supportFragmentManager, "CustomDialog")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveOrUpdateMemo() {
        val currentLocalTime: LocalTime = LocalTime.now()
        val hour = currentLocalTime.hour
        val minute = currentLocalTime.minute
        val timeText = String.format("%02d:%02d", hour, minute)
        val localDate: LocalDate = LocalDate.now()
        val dateText = localDate.toString()


        val titleText = binding.titleTextView.text.toString()
        val contentText = binding.contentTextView.text.toString()

        val memo = Memo(
            title = titleText, content = contentText, date = dateText
            , time = timeText, emotion = emotion)

        val db = AppDatabase.getDatabase(applicationContext)
        val memoDao = db?.MemoDAO()

        if (memoId == 0L) {
            // 새 메모 저장
            GlobalScope.launch(Dispatchers.IO) {
                val newMemoId = memoDao?.insertMemos(memo) // 새로 추가된 메모의 ID를 받아옴
            }
        } else {
            // 기존 메모 업데이트
            memo.id = memoId
            GlobalScope.launch(Dispatchers.IO) {
                memoDao?.updateMemos(memo)
            }
        }

        finish()
    }

    override fun onEmotionSelected(emotionNumber: Int) {
        when (emotionNumber) {
            1 -> {
                binding.emotionBtn.setImageResource(R.drawable.angry)
                emotion = 1
            }
            2 -> {
                binding.emotionBtn.setImageResource(R.drawable.normal)
                emotion = 2
            }
            3 -> {
                binding.emotionBtn.setImageResource(R.drawable.happy)
                emotion = 3
            }
            4 -> {
                binding.emotionBtn.setImageResource(R.drawable.bad)
                emotion = 4
            }
            5 -> {
                binding.emotionBtn.setImageResource(R.drawable.sad)
                emotion = 5
            }
            else -> {
                binding.emotionBtn.setImageResource(R.drawable.plus)
                emotion = 0
            }
        }
    }
}
