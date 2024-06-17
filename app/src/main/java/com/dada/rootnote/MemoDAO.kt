package com.dada.rootnote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemoDAO {
    @Query("SELECT * FROM memo")
    fun getAllMemos(): List<Memo>

    @Insert
    fun insertMemos(memo: Memo)

    @Update
    fun updateMemos(memo: Memo)

    @Delete
    fun deleteMemos(memo: Memo)

}