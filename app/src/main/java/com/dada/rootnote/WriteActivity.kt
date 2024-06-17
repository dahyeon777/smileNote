package com.dada.rootnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dada.rootnote.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        // 예를 들어 UI에서 텍스트뷰에서 가져온 값을 변수에 저장하는 방법입니다.
        val titleText = binding.titleTextView.text.toString()
        val contentText = binding.contentTextView.text.toString()
        val dateText = binding.dateTextView.text.toString()

    // Memo 객체 생성
        val memo = Memo(title = titleText, content = contentText, date = dateText)

     // 데이터베이스에 Memo 객체 삽입
        val db = AppDatabase.getDatabase(applicationContext)
        val memoDao = db?.MemoDAO()

    // 백그라운드 스레드나 코루틴을 사용하여 데이터베이스 작업 실행하는 것이 좋습니다.
    // 여기서는 간단히 메인 스레드에서 실행하는 예시를 보여드립니다.
        memoDao?.insertMemos(memo)


    }
}