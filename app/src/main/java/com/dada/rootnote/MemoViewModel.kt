package com.dada.rootnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    private val memoRepository: MemoRepository

    init {
        val memoDao = AppDatabase.getDatabase(application)?.MemoDAO()
        memoRepository = memoDao?.let { MemoRepository(it) }!!
    }

    fun getAllMemos(): LiveData<List<Memo>> {
        return memoRepository.getAllMemos()
    }

    fun insertMemo(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.insertMemo(memo)
        }
    }

    fun deleteMemoById(memoId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.deleteMemoById(memoId)
        }
    }

}
