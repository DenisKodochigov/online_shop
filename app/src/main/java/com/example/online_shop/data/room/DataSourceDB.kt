package com.example.online_shop.data.room

import android.util.Log
import com.example.online_shop.App
import com.example.online_shop.data.room.tables.PersonDB
import com.example.online_shop.entity.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DataSourceDB  @Inject constructor(){

    private val dataDao = ProviderDao().getDataDao(App.context)

    fun checkPerson(firstName:String, lastName:String, password: String): Boolean {
        return dataDao.checkPerson(firstName, lastName, password)
    }

    fun addPerson(firstName:String, lastName:String, password: String, email: String): Long {
        val personDB = PersonDB(
                person = Person(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                password = password
        ))
        return dataDao.insert(personDB)
    }
    fun savePerson(person: Person) {
        val personDB = person.firstName?.let { firstName ->
            person.password?.let { password ->
                dataDao.getPerson(firstName, password)
            }
        }
        if (personDB != null) {
            personDB.person?.cp(person)
            dataDao.update(personDB)
        }
    }
    fun loginPerson(person: Person): Person {
        val personDB = person.firstName?.let { firstName ->
            person.password?.let { password ->
                dataDao.getPerson(firstName, password)
            }
        }
        Log.d("KDS", "loginPersonP.personDB $personDB")
        return personDB?.toPerson() ?: Person()
    }
}