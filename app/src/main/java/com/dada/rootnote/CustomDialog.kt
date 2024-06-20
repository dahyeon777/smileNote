package com.dada.rootnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dada.rootnote.databinding.CustomDialogBinding

class CustomDialog(private val title: String, private val content: String) : DialogFragment() {

    private var _binding: CustomDialogBinding? = null
    private val binding get() = _binding!!

    private var listener: OnEmotionSelectedListener? = null

    fun setOnEmotionSelectedListener(listener: OnEmotionSelectedListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CustomDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // 이미지 버튼 클릭 이벤트 설정
        binding.angry.setOnClickListener {
            listener?.onEmotionSelected(1)
            dismiss()
        }

        binding.normal.setOnClickListener {
            listener?.onEmotionSelected(2)
            dismiss()
        }

        binding.happy.setOnClickListener {
            listener?.onEmotionSelected(3)
            dismiss()
        }

        binding.bad.setOnClickListener {
            listener?.onEmotionSelected(4)
            dismiss()
        }

        binding.sad.setOnClickListener {
            listener?.onEmotionSelected(5)
            dismiss()
        }

        // 다이얼로그 바깥을 클릭해도 닫히도록 설정
        isCancelable = true

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
