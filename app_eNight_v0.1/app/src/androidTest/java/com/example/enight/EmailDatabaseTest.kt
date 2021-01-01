package com.example.enight

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.enight.dataBase.email.Email
import com.example.enight.dataBase.EmailDatabase
import com.example.enight.dataBase.email.EmailDatabaseDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class EmailDatabaseTest {

    private lateinit var emailDao: EmailDatabaseDao
    private lateinit var db: EmailDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, EmailDatabase::class.java).allowMainThreadQueries().build()
        emailDao = db.emailDatabaseDao
    }



    @Test
    @Throws(Exception::class)
    suspend fun insertTest() {
        val mail = Email()
        emailDao.insert(mail)
        val toMail = emailDao.getToMail()
        assertEquals(toMail?.mail,"" )
    }

    @Test
    @Throws(Exception::class)
    suspend fun getTest() {
        val mail = Email(3,"hello",System.currentTimeMillis().toString())
        emailDao.insert(mail)
        val toMail = emailDao.getToMail()
        assertEquals(toMail?.mailId,3 )
    }

   @Test
    @Throws(Exception::class)
    suspend fun getAllTest() {
        val mail = Email()
        emailDao.insert(mail)
        val toMail = emailDao.getAll()
        //assertTrue(toMail.value?.contains(mail)!!)
    }

    @Test
    @Throws(Exception::class)
    suspend fun deleteTest() {
        val mail = Email()
        emailDao.insert(mail)
        val toMail = emailDao.clear()
        assertEquals(emailDao.getToMail(),null)
    }

    @Test
    @Throws(Exception::class)
    suspend fun updateTest() {
        var mail = Email(1,"hello",System.currentTimeMillis().toString())
        emailDao.insert(mail)
        var toMail = emailDao.getToMail()
        assertEquals(toMail?.mail,"hello")

        mail = Email(1,"test",System.currentTimeMillis().toString())
        emailDao.update(mail)
        toMail = emailDao.getToMail()
        assertEquals(toMail?.mail,"test")
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}