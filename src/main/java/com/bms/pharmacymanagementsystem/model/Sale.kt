package com.bms.pharmacymanagementsystem.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Sale @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val count: Int,

    val date: LocalDate? = LocalDate.now(),

    val totalAmount: Double,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id")
    val customer: Customer,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "pharmacist_id")
    val pharmacist: Pharmacist,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "medicine_id")
    val medicine: Medicine,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "purchaser_id")
    val purchaser: Purchaser
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sale

        if (id != other.id) return false
        if (count != other.count) return false
        if (date != other.date) return false
        if (totalAmount != other.totalAmount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + count
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + totalAmount.hashCode()
        return result
    }
}