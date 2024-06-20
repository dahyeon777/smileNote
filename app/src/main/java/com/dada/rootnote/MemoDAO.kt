package com.dada.rootnote

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemoDAO {

    @Query("SELECT * FROM memo ORDER BY id DESC")
    fun getAllMemosReverseOrder(): LiveData<List<Memo>>

    @Insert
    fun insertMemos(vararg memo: Memo)

    @Update
    fun updateMemos(memo: Memo)

    @Query("SELECT * FROM memo WHERE id = :memoId")
    fun getMemoById(memoId: Long): LiveData<Memo?>

    @Query("DELETE FROM memo WHERE id = :memoId")
    fun deleteMemoById(memoId: Long)

    // 각각의 emotion 값에 대한 갯수를 세는 쿼리
    @Query("SELECT COUNT(*) FROM memo WHERE emotion = 1")
    fun countEmotion1(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM memo WHERE emotion = 2")
    fun countEmotion2(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM memo WHERE emotion = 3")
    fun countEmotion3(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM memo WHERE emotion = 4")
    fun countEmotion4(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM memo WHERE emotion = 5")
    fun countEmotion5(): LiveData<Int>


}
