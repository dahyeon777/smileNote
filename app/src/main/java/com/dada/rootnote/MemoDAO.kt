package com.dada.rootnote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao

interface MemoDAO {

    @Insert(onConflict = REPLACE)
    fun insert(memo : MemoEntitiy)

    @Query("SELECT * FROM memo")
    fun getAll() : List<MemoEntitiy>

    @Delete
    fun delete(memo : MemoEntitiy)

}