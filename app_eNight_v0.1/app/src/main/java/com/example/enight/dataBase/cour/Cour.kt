package com.example.enight.dataBase.cour

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * this is the table of the courses
 */
@Entity(tableName = "cour")
data class Cour(

    /**
     * this is the primary key with the name of the course
     */
    @PrimaryKey
    var courId: String = "null",

    /**
     * this variable represent credit number of the course
     */
    @ColumnInfo(name = "nbCredit")
    var nbCredit: Long = 0L,

    /**
     * this is the notification if the course is success
     */
    @ColumnInfo(name = "valided")
    var valided: Boolean = false
)