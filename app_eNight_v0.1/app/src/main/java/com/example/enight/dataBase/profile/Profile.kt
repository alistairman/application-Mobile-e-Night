package com.example.enight.dataBase.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * this table is the profile of users
 */
@Entity(tableName="profile")
data class Profile(
    /**
     * this variable represent the id of the data
     */
    @PrimaryKey(autoGenerate = true)
    var userId : Long = 0L,

    /**
     * this variable represent the email of user
     */
    @ColumnInfo(name = "email")
    var mail: String = "",

    /**
     * this is the first name of the user
     */
    @ColumnInfo(name = "first_Name")
    var firstName: String = "",

    /**
     * this is the last name of the user
     */
    @ColumnInfo(name = "last_name")
    var lastName : String = ""
)
