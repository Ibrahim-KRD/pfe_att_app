package com.example.pfe_att_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.pfe_att_app.domain.entities.Contact
import java.util.concurrent.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = IGNORE)
    suspend fun AddContact(contact: Contact)

    @Update
    suspend fun Update(contact: Contact)
    @Delete
    suspend fun Delete(contact: Contact)
    @Query("Select * from contact")
     fun getContacts():LiveData<List<Contact>>
}