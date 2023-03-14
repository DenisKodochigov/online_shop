package com.example.online_shop.data.room.tables

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.online_shop.entity.Person

@Entity(tableName = "person")
data class PersonDB (
    @PrimaryKey(autoGenerate = true) val idPerson: Int = 0,
    @Embedded var person: Person?
){
    fun toPerson(): Person{

        return Person(
            firstName = this.person?.firstName ?: "",
            lastName = this.person?.lastName ?: "",
            login = this.person?.login ?: "",
            password = this.person?.password ?: "",
            email = this.person?.email ?: "",
            photo = this.person?.photo,
            balance = this.person?.balance ?: 0.0,
            payment_metod = this.person?.payment_metod ?: "",
        )
    }
}