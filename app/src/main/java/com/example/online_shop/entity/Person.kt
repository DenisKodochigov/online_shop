package com.example.online_shop.entity

data class Person(
    var firstName: String? = null,
    var lastName: String? = null,
    var login: String? = null,
    var password: String? = null,
    var email: String? = null,
    var photo: String? = null,
    var balance: Double? = null,
    var payment_metod:String? = null,
) {
    fun cp(item: Person){
        this.firstName = item.firstName
        this.lastName = item.lastName
        this.login = item.login
        this.password = item.password
        this.email = item.email
        this.photo = item.photo
        this.balance = item.balance
        this.payment_metod = item.payment_metod
    }
}