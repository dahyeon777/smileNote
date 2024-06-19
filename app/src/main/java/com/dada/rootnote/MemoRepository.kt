package com.dada.rootnote

import androidx.lifecycle.LiveData

class MemoRepository(private val memoDao: MemoDAO) {

    fun getAllMemosReverseOrder(): LiveData<List<Memo>> {
        return memoDao.getAllMemosReverseOrder()
    }

    fun insertMemo(memo: Memo) {
        memoDao.insertMemos(memo)
    }

    fun deleteMemoById(memoId: Long) {
        memoDao.deleteMemoById(memoId)
    }
}
