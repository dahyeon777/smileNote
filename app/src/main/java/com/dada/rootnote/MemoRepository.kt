package com.dada.rootnote

import androidx.lifecycle.LiveData

class MemoRepository(private val memoDao: MemoDAO) {

    fun getAllMemos(): LiveData<List<Memo>> {
        return memoDao.getAllMemos()
    }

    fun insertMemo(memo: Memo) {
        memoDao.insertMemos(memo)
    }
}
