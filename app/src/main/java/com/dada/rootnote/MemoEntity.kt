package com.dada.rootnote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntitiy(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var title : String = "",
    var content : String = "",
    var date : String = ""
)
