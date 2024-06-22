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
    private var emotion = 0
    @RequiresApi(Build.VERSION_CODES.O)
    private val localDate: LocalDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    private val dateText = localDate.toString()

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        // Intent로부터 데이터 받기
        memoId = intent.getLongExtra("memoId", 0)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        emotion = intent.getIntExtra("emotion",0)

        // 받아온 데이터가 있다면 UI에 설정
        title?.let { binding.titleTextView.setText(it) }
        content?.let { binding.contentTextView.setText(it) }
        date?.let { binding.dateTextWrite.setText(it) }

        // emotion 값에 따라 이미지 설정
        val imageResource = when (emotion) {
            1 -> R.drawable.angry
            2 -> R.drawable.normal
            3 -> R.drawable.happy
            4 -> R.drawable.bad
            5 -> R.drawable.sad
            else -> R.drawable.plus
        }
        binding.emotionBtn.setImageResource(imageResource)

        // 저장 버튼 클릭 시
        binding.saveBtn.setOnClickListener {
            saveOrUpdateMemo(time)
        }

        // 감정 버튼 클릭 시
        binding.emotionBtn.setOnClickListener {
            val customDialog = CustomDialog("제목", "내용")
            customDialog.setOnEmotionSelectedListener(this) // WriteActivity 자체를 listener로 설정
            customDialog.show(supportFragmentManager, "CustomDialog")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveOrUpdateMemo(originalTime: String?) {
        // 만약 originalTime이 null이 아니면 그 값을 사용하고, null이면 현재 시간을 사용
        val timeText = originalTime ?: run {
            val currentLocalTime: LocalTime = LocalTime.now()
            val hour = currentLocalTime.hour
            val minute = currentLocalTime.minute
            String.format("%02d:%02d", hour, minute)
        }

        val titleText = binding.titleTextView.text.toString()
        val contentText = binding.contentTextView.text.toString()

        val memo = Memo(
            title = titleText, content = contentText, date = binding.dateTextWrite.text.toString()
            , time = timeText, emotion = emotion)

        val db = AppDatabase.getDatabase(applicationContext)
        val memoDao = db?.MemoDAO()

        if (memoId == 0L) {
            // 새 메모 저장
            GlobalScope.launch(Dispatchers.IO) {
                memoDao?.insertMemos(memo)
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
