package com.bms.pharmacymanagementsystem.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Purchaser @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val amount: Double,

    val date: LocalDate? = LocalDate.now(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    val medicine: Medicine,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    val customer: Customer,

    @OneToMany(mappedBy = "purchaser")
    val sales: Set<Sale>? = HashSet()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Purchaser

        if (id != other.id) return false
        if (amount != other.amount) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + amount.hashCode()
        result = 31 * result + (date?.hashCode() ?: 0)
        return result
    }
}
