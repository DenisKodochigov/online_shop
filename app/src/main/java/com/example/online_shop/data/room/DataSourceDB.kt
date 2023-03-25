package com.example.online_shop.data.room

import android.util.Log
import com.example.online_shop.data.room.tables.PersonDB
import com.example.online_shop.entity.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DataSourceDB @Inject constructor(private val dataDao: DataDao){

    fun checkPerson(person: Person): Boolean {
        return person.lastName?.let { lastname ->
            person.email?.let { email ->
                person.firstName?.let { firstname ->
                    dataDao.checkPerson(firstname, lastname, email)
                }
            }
        } ?: false
    }

    fun addPerson(person: Person): Long {
        val id = dataDao.insert(PersonDB( person = person))
        return id
    }
    fun savePerson(person: Person) {
        val personDB = person.firstName?.let { firstName ->
            person.password?.let { password ->
                dataDao.getPerson(firstName, password)
            }
        }
        if (personDB != null) dataDao.update(personDB)
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