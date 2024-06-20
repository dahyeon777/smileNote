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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = CustomDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // 다이얼로그 바깥을 클릭해도 닫히지 않도록 설정 (선택 사항)
        isCancelable = true

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
