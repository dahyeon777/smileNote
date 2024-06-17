package com.dada.rootnote

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDAO {

    @Query("SELECT * FROM memo")
    fun getAllMemos(): LiveData<List<Memo>>

    @Insert
    fun insertMemos(vararg memo: Memo)
}
