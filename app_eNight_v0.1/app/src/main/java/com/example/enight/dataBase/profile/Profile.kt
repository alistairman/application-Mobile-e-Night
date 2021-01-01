package com.example.enight.dataBase.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="profile")
data class Profile(
    /**
     * this variable represent the id of the data
     */
    @PrimaryKey(autoGenerate = true)
    var userId : Long = 0L,

    /**
     * this variable represent the mail of data
     */
    @ColumnInfo(name = "email")
    var mail: String = "",

    /**
     * this variable represent the date when data is used
     */
    @ColumnInfo(name = "first_Name")
    val firstName: String = "",

    /**
     * this variable represent the time when data is used
     */
    @ColumnInfo(name = "last_name")
    val lastName : String = ""
)
