package com.example.online_shop.data.room

import androidx.room.*
import com.example.online_shop.data.room.tables.PersonDB

/* Data access object to query the database. */
@Dao
interface DataDao {
//table FILMS
    //Insert new record to the table films

    @Insert
    fun insert(person: PersonDB): Long
    //Updating a record in the table films
    @Update
    fun update(person: PersonDB)

    //Clearing the table films
    @Query("DELETE FROM person")
    fun nukeTable()

    //Select PersonDB bi id
    @Query("SELECT * FROM person WHERE idPerson = :id")
    fun getPerson(id: Int): PersonDB?
    //Select PersonDB bi id

    //Select all entries from person
    @Query("SELECT * FROM person ORDER BY idPerson DESC")
    fun getPersons(): List<PersonDB>?

    //Select all entries from person
    @Query("SELECT 1 FROM person WHERE firstName = :firstName AND lastName = :lastName AND password= :password")
    fun checkPerson(firstName:String, lastName:String, password: String): Boolean

    @Query("SELECT * FROM person WHERE firstName = :firstName AND password = :password")
    fun getPerson(firstName:String, password:String): PersonDB?
}
