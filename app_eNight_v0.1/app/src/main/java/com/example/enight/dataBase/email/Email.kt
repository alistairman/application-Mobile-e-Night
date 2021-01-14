package com.example.enight.dataBase.email

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * this class is use to save different login of the app
 * the login_mail is the name of the table in database
 */
@Entity(tableName="login_mail")
data class Email (

    /**
     * this variable represent the id of the data
     */
    @PrimaryKey(autoGenerate = true)
    var mailId : Long = 0L,

    /**
     * this variable represent the email address of login
     */
    @ColumnInfo(name = "log_mail")
    var mail: String = "",

    /**
     * this variable represent the last date when data is used
     */
    @ColumnInfo(name = "log_date")
    var date: String = "",

    /**
     * this variable represent the last time when data is used
     */
    @ColumnInfo(name = "log_start")
    var time : String = ""
)