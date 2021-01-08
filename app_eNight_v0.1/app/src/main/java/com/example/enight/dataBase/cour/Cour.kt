package com.example.enight.dataBase.cour

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cour")
data class Cour(
    /**
     * this variable represent the id of the data
     */
    @PrimaryKey(autoGenerate = true)
    var etuNo : Long = 0L,

    /**
     * this variable represent the mail of data
     */
    @ColumnInfo(name = "cour")
    var cour: String = "",

    /**
     * this variable represent the mail of data
     */
    @ColumnInfo(name = "nbCredit")
    var nbCredit: Long = 0L,

    /**
     * this variable represent the date when data is used
     */
    @ColumnInfo(name = "valided")
    var valided: Boolean = false
)