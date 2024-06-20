package com.dada.rootnote

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChartActivity : AppCompatActivity() {

    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private lateinit var progressBar3: ProgressBar
    private lateinit var progressBar4: ProgressBar
    private lateinit var progressBar5: ProgressBar

    private lateinit var percentage1: TextView
    private lateinit var percentage2: TextView
    private lateinit var percentage3: TextView
    private lateinit var percentage4: TextView
    private lateinit var percentage5: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        // ProgressBar와 TextView 초기화
        progressBar1 = findViewById(R.id.progressBar1)
        progressBar2 = findViewById(R.id.progressBar2)
        progressBar3 = findViewById(R.id.progressBar3)
        progressBar4 = findViewById(R.id.progressBar4)
        progressBar5 = findViewById(R.id.progressBar5)

        percentage1 = findViewById(R.id.percentage1)
        percentage2 = findViewById(R.id.percentage2)
        percentage3 = findViewById(R.id.percentage3)
        percentage4 = findViewById(R.id.percentage4)
        percentage5 = findViewById(R.id.percentage5)

        // 각 ProgressBar에 값 설정
        progressBar1.progress = 50 // 예시 값, 원하는 값으로 설정
        progressBar2.progress = 20
        progressBar3.progress = 80
        progressBar4.progress = 35
        progressBar5.progress = 65

        // 각 퍼센티지 텍스트뷰에 값 설정
        percentage1.text = "50%" // 예시 값, 원하는 값으로 설정
        percentage2.text = "20%"
        percentage3.text = "80%"
        percentage4.text = "35%"
        percentage5.text = "65%"
    }
}
