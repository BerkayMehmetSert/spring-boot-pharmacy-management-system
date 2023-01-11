package com.bms.pharmacymanagementsystem.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Pharmacist @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val firstName: String,
    val lastName: String,
    val gender: String,
    val age: Int,
    val address: String,
    val email: String,

    @OneToMany(mappedBy = "pharmacist")
    val sales: Set<Sale>? = HashSet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pharmacist

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (gender != other.gender) return false
        if (age != other.age) return false
        if (address != other.address) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + age
        result = 31 * result + address.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }
}
