package com.bms.pharmacymanagementsystem.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Medicine @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val name:String,

    val description: String,

    val price: Double,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "category_id")
    val category: Category,

    @OneToMany(mappedBy = "medicine")
    val purchasers: Set<Purchaser>? = HashSet(),

    @OneToMany(mappedBy = "medicine")
    val sales: Set<Sale>? = HashSet()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Medicine

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}
