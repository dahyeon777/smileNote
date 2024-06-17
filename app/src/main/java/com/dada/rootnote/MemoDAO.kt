package com.dada.rootnote

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemoDAO {

    @Query("SELECT * FROM memo")
    fun getAllMemos(): LiveData<List<Memo>>

    @Insert
    fun insertMemos(vararg memo: Memo)

    @Update
    fun updateMemos(memo: Memo)

    @Query("SELECT * FROM memo WHERE id = :memoId")
    fun getMemoById(memoId: Long): LiveData<Memo?>
}
