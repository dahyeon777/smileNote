package com.dada.rootnote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dada.rootnote.databinding.ActivityChartBinding

class ChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChartBinding
    private lateinit var viewModel: MemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chart)

        // MemoViewModel 인스턴스 생성
        viewModel = ViewModelProvider(this).get(MemoViewModel::class.java)

        // 각 ProgressBar와 TextView에 MemoViewModel의 LiveData를 연결하여 값 설정
        viewModel.countEmotion1.observe(this, Observer { count ->
            binding.progressBar1.progress = count
            binding.percentage1.text = "$count"
        })

        viewModel.countEmotion2.observe(this, Observer { count ->
            binding.progressBar2.progress = count
            binding.percentage2.text = "$count"
        })

        viewModel.countEmotion3.observe(this, Observer { count ->
            binding.progressBar3.progress = count
            binding.percentage3.text = "$count"
        })

        viewModel.countEmotion4.observe(this, Observer { count ->
            binding.progressBar4.progress = count
            binding.percentage4.text = "$count"
        })

        viewModel.countEmotion5.observe(this, Observer { count ->
            binding.progressBar5.progress = count
            binding.percentage5.text = "$count"
        })
    }
}
