package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Entity
data class Freight(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val productType: String,

    @Column(nullable = false)
    val productPrice: Long,

    @Column(nullable = false)
    val productWeight: Float,

    @Column(nullable = false)
    val senderName: String,

    @Column
    val clearanceNumber: String,

    @Column(nullable = false)
    val remark: String,

    @Column(nullable = false)
    val quantity: Int,

    @Column(nullable = false)
    val senderAddress: String,

    @Column(nullable = false)
    val senderNumber: String,

    @Column(nullable = false)
    val receiverName: String,

    @Column(nullable = false)
    val receiverAddress: String,

    @Column(nullable = false)
    val receiverNumber: Int,

    @Column
    val receiverCountry : String,

    @Column
    val receiverCity : String,

    @Column
    val receiverState : String,

    @Column
    val zipcodeReceiver : Int,

    @Column
    val shippingStatus: String="before",

    @Column
    val additionalDoc: String,

    @OneToOne(mappedBy = "freight", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val contract: Contract? = null
)