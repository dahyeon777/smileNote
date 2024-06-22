package com.dada.rootnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    private val memoRepository: MemoRepository
    private val memoDao: MemoDAO  // memoDao를 여기에 선언합니다.

    // 각각의 감정 수를 담을 LiveData
    val countEmotion1: LiveData<Int>
    val countEmotion2: LiveData<Int>
    val countEmotion3: LiveData<Int>
    val countEmotion4: LiveData<Int>
    val countEmotion5: LiveData<Int>

    init {
        val memoDao = AppDatabase.getDatabase(application)?.MemoDAO()
        memoRepository = memoDao?.let { MemoRepository(it) }!!
        this.memoDao = memoDao  // memoDao를 할당합니다.

        // 감정 수를 조회하는 LiveData를 초기화합니다.
        countEmotion1 = memoDao.countEmotion1()
        countEmotion2 = memoDao.countEmotion2()
        countEmotion3 = memoDao.countEmotion3()
        countEmotion4 = memoDao.countEmotion4()
        countEmotion5 = memoDao.countEmotion5()
    }

    // 모든 메모를 역순으로 가져오는 함수
    fun getAllMemosReverseOrder(): LiveData<List<Memo>> {
        return memoRepository.getAllMemosReverseOrder()
    }

    // 메모를 삽입하는 함수
    fun insertMemo(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.insertMemo(memo)
        }
    }

    // 메모를 ID로 삭제하는 함수
    fun deleteMemoById(memoId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.deleteMemoById(memoId)
        }
    }
}